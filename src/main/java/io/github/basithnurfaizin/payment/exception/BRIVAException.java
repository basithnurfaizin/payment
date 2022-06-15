package io.github.basithnurfaizin.payment.exception;

import io.github.basithnurfaizin.payment.enums.MessageConstant;

public class BRIVAException extends RuntimeException {

    private static final long serialVersionUID = -3311473650198478976L;

    private final MessageConstant messageConstant;

    public BRIVAException(MessageConstant messageConstant) {
        this.messageConstant = messageConstant;
    }

    public MessageConstant getMessageConstant() {
        return messageConstant;
    }
}
