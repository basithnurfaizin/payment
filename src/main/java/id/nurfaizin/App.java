package id.nurfaizin;

import id.nurfaizin.payment.briva.AuthenticationResponse;
import id.nurfaizin.payment.security.AuthenticationBRIVAServiceImpl;
import id.nurfaizin.payment.security.AuthenticationService;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException, InterruptedException {

        AuthenticationService<AuthenticationResponse> authenticationService = new AuthenticationBRIVAServiceImpl(
                "lz84aaR4sGhKhlBTIdGAOWY6yzzo3XAm",
                "a1oW0szJpfTEcjEF",
                "https://sandbox.partner.api.bri.co.id/oauth/client_credential/accesstoken?grant_type=client_credentials"
        );

    }
}
