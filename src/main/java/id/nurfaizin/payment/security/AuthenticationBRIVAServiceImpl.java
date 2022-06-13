package id.nurfaizin.payment.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.nurfaizin.payment.briva.AuthenticationResponse;
import id.nurfaizin.payment.util.HttpClientConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationBRIVAServiceImpl implements AuthenticationService<AuthenticationResponse> {

    private final String clientId;

    private final String clientSecret;

    private final String url;

    ObjectMapper objectMapper = new ObjectMapper();

    public AuthenticationBRIVAServiceImpl(String clientId, String clientSecret, String url) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.url = url;
    }

    @Override
    public AuthenticationResponse getToken() throws IOException, InterruptedException {

        Map<Object, Object> map = new HashMap<>();
        map.put("client_id", this.clientId);
        map.put("client_secret", this.clientSecret);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpClientConfig.ofFormData(map))
                .uri(URI.create(this.url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = HttpClientConfig.httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), AuthenticationResponse.class);
    }
}
