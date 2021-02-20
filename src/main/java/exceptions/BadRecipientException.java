package exceptions;

public class BadRecipientException extends WrongInputException {

    private static final String message = "Bad recipient";

    public BadRecipientException() {
        super(message);
    }
}