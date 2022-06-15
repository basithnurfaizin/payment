package io.github.basithnurfaizin.payment.briva;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.basithnurfaizin.payment.briva.dto.*;
import io.github.basithnurfaizin.payment.enums.MessageConstant;
import io.github.basithnurfaizin.payment.exception.BRIVAException;
import io.github.basithnurfaizin.payment.security.AuthenticationBRIVAServiceImpl;
import io.github.basithnurfaizin.payment.security.AuthenticationResponse;
import io.github.basithnurfaizin.payment.signature.SignatureBRIVA;
import io.github.basithnurfaizin.payment.util.HttpClientConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class BRIVAServiceImpl implements BRIVAService {

    private final AuthenticationBRIVAServiceImpl authenticationBRIVAService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String RELATIVE_URL;

    private final String BASE_URL;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public BRIVAServiceImpl(AuthenticationBRIVAServiceImpl authenticationBRIVAService,
                            String RELATIVE_URL,
                            String base_url) {
        this.authenticationBRIVAService = authenticationBRIVAService;
        this.RELATIVE_URL = RELATIVE_URL;
        BASE_URL = base_url;
    }

    @Override
    public BaseResponseBRIVA<VirtualAccountResponse> createVirtualAccount(VirtualAccountRequest request) throws JsonProcessingException, InterruptedException {

        String requestString = objectMapper.writeValueAsString(request);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedTime =  sdf.format(timestamp);

        try {
            AuthenticationResponse authenticate = authenticationBRIVAService.authenticate();
            String accessToken = authenticate.getAccessToken();
            String payload = "path="+ RELATIVE_URL +"&verb=POST&token=Bearer "+accessToken+"&timestamp="+formattedTime+"&body="+requestString;

            SignatureBRIVA signatureBRIVA = new SignatureBRIVA();

            String signature = signatureBRIVA.getSignature(authenticationBRIVAService.getClientSecret(), payload);

            String httpRequest = BASE_URL + RELATIVE_URL;


            HttpRequest requestCreateVirtualAccount = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(requestString))
                    .uri(URI.create(httpRequest))
                    .header("Content-Type", "application/json")
                    .header("BRI-Signature", signature)
                    .header("BRI-Timestamp", formattedTime)
                    .header("Authorization", "Bearer "+accessToken)
                    .build();

            HttpResponse<String> response = HttpClientConfig.httpClient
                    .send(requestCreateVirtualAccount, HttpResponse.BodyHandlers.ofString());

            BaseResponseBRIVA<VirtualAccountResponse> responseBRIVA = objectMapper.readValue(
                    response.body(),
                    new TypeReference<>() {
                    }
            );

            if (!responseBRIVA.getStatus() && responseBRIVA.getResponseCode().equalsIgnoreCase("13")) {
                throw new BRIVAException(MessageConstant.VIRTUAL_ACCOUNT_EXIST);
            }

            return responseBRIVA;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BaseResponseBRIVA<VirtualAccountResponse> getVirtualAccountInfo(String institutionCode,
                                                                           Long accountNumber,
                                                                           String customerCode) throws IOException, InterruptedException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        AuthenticationResponse authenticate = authenticationBRIVAService.authenticate();

        String formattedTime =  sdf.format(timestamp);

        String path = RELATIVE_URL+"/"+institutionCode+"/"+accountNumber+"/"+customerCode;

        return convertStringToObject(authenticate, formattedTime, path);
    }


    @Override
    public String getStatusPayment(String institutionCode, Long accountNumber, String customerCode) throws IOException, InterruptedException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        AuthenticationResponse authenticate = authenticationBRIVAService.authenticate();

        String formattedTime =  sdf.format(timestamp);

        String path = RELATIVE_URL+"/status"+"/"+institutionCode+"/"+accountNumber+"/"+customerCode;

        BaseResponseBRIVA<VirtualAccountResponse> responseBRIVA = convertStringToObject(authenticate, formattedTime, path);

        return responseBRIVA.getData().getStatus();
    }

    @Override
    public BaseResponseBRIVA<StatusResponse> updateStatusPayment(StatusRequest request) throws IOException, InterruptedException {


        String requestString = objectMapper.writeValueAsString(request);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedTime =  sdf.format(timestamp);

        AuthenticationResponse authenticate = authenticationBRIVAService.authenticate();


        String path = RELATIVE_URL+"/status";

        String payload = "path="+ path +"&verb=PUT&token=Bearer "+authenticate.getAccessToken()+"&timestamp="+formattedTime+"&body="+requestString;

        SignatureBRIVA signatureBRIVA = new SignatureBRIVA();

        String signature = signatureBRIVA.getSignature(authenticationBRIVAService.getClientSecret(), payload);

        String httpRequest = BASE_URL + RELATIVE_URL;


        HttpRequest updateStatus = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(requestString))
                .uri(URI.create(httpRequest))
                .header("Content-Type", "application/json")
                .header("BRI-Signature", signature)
                .header("BRI-Timestamp", formattedTime)
                .header("Authorization", "Bearer "+authenticate.getAccessToken())
                .build();


        HttpResponse<String> response = HttpClientConfig.httpClient
                .send(updateStatus, HttpResponse.BodyHandlers.ofString());

        BaseResponseBRIVA<StatusResponse> responseStatus = objectMapper.readValue(
                response.body(),
                new TypeReference<>() {
                }
        );

        if (!responseStatus.getStatus() && responseStatus.getResponseCode().equalsIgnoreCase("14")) {
            throw new BRIVAException(MessageConstant.VIRTUAL_ACCOUNT_NOT_FOUND);
        }

        return responseStatus;
    }

    private BaseResponseBRIVA<VirtualAccountResponse> convertStringToObject(AuthenticationResponse authenticate, String formattedTime, String path) throws IOException, InterruptedException {
        HttpResponse<String> response = encryptPayload(authenticate, formattedTime, path);

        BaseResponseBRIVA<VirtualAccountResponse> responseBRIVA = objectMapper.readValue(
                response.body(),
                new TypeReference<>() {
                }
        );

        if (!responseBRIVA.getStatus() && responseBRIVA.getResponseCode().equalsIgnoreCase("14")) {
            throw new BRIVAException(MessageConstant.VIRTUAL_ACCOUNT_NOT_FOUND);
        }

        return responseBRIVA;
    }


    private  HttpResponse<String> encryptPayload(AuthenticationResponse authenticate, String formattedTime, String path) throws IOException, InterruptedException {
        String payload = "path="+path+"&verb=GET&token=Bearer "+authenticate.getAccessToken()+"&timestamp="+formattedTime+"&body=";

        SignatureBRIVA signatureBRIVA = new SignatureBRIVA();

        String signature = signatureBRIVA.getSignature(authenticationBRIVAService.getClientSecret(), payload);

        String url = BASE_URL + path;


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("BRI-Signature", signature)
                .header("BRI-Timestamp", formattedTime)
                .header("Authorization", "Bearer " + authenticate.getAccessToken())
                .build();

        return HttpClientConfig.httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

}
