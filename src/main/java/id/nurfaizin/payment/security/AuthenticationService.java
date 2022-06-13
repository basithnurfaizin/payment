package id.nurfaizin.payment.security;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface AuthenticationService<T> {

    T getToken() throws IOException, InterruptedException;
}
