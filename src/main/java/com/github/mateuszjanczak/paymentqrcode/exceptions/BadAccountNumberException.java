package com.github.mateuszjanczak.paymentqrcode.exceptions;

public class BadAccountNumberException extends WrongInputException {

    private static final String message = "Bad account number";

    public BadAccountNumberException() {
        super(message);
    }
}
