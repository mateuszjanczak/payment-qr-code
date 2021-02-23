package com.github.mateuszjanczak.paymentqrcode.exceptions;

public class BadNipException extends WrongInputException {

    private static final String message = "Bad NIP number";

    public BadNipException() {
        super(message);
    }
}
