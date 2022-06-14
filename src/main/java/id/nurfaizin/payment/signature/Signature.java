package id.nurfaizin.payment.signature;

public interface Signature {

    String getSignature(String clientSecret, String payload);
}
