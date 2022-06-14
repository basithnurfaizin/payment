package id.nurfaizin.payment.security;

import java.io.IOException;

public interface AuthenticationService<T> {

    T authenticate() throws IOException, InterruptedException;
}
