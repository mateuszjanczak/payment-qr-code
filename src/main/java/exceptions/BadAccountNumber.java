package exceptions;

public class BadAccountNumber extends WrongInputException {

    private static final String message = "Bad account number";

    public BadAccountNumber() {
        super(message);
    }
}
