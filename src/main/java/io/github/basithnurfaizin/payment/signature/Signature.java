package io.github.basithnurfaizin.payment.signature;

public interface Signature {

    String getSignature(String clientSecret, String payload);
}
