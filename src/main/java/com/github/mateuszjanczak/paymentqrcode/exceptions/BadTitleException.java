package com.github.mateuszjanczak.paymentqrcode.exceptions;

public class BadTitleException extends WrongInputException {

    private static final String message = "Bad title";

    public BadTitleException() {
        super(message);
    }
}
