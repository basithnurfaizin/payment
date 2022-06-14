package id.nurfaizin.payment.briva;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.nurfaizin.payment.briva.dto.BaseResponseBRIVA;
import id.nurfaizin.payment.briva.dto.VirtualAccountDTO;
import id.nurfaizin.payment.security.AuthenticationBRIVAServiceImpl;
import id.nurfaizin.payment.security.AuthenticationResponse;
import id.nurfaizin.payment.signature.SignatureBRIVA;
import id.nurfaizin.payment.util.HttpClientConfig;

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
    public BaseResponseBRIVA<VirtualAccountDTO> createVirtualAccount(VirtualAccountDTO request) throws JsonProcessingException, InterruptedException {

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


            return objectMapper.readValue(
                    response.body(),
                    new TypeReference<>() {
                    }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
