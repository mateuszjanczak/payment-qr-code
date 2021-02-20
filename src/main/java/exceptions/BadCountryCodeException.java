package exceptions;

public class BadCountryCodeException extends WrongInputException {

    private static final String message = "Bad country code";

    public BadCountryCodeException() {
        super(message);
    }
}
