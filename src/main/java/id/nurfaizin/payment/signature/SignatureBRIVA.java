package id.nurfaizin.payment.signature;

import id.nurfaizin.payment.util.HMAC;

import java.util.Base64;

public class SignatureBRIVA implements Signature {

    @Override
    public String getSignature(String clientSecret, String payload) {

        byte[] signature = HMAC.calcHmacSha256(clientSecret, payload);

        return Base64.getEncoder().encodeToString(signature);
    }
}
