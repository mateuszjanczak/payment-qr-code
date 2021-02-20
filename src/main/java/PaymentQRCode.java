import exceptions.BadAccountNumber;
import exceptions.WrongInputException;

import java.util.Optional;

public class PaymentQRCode {

    protected final String SEPARATOR = "|";

    protected String recipient;
    protected String accountNumber;
    protected double amount;
    protected String title;
    protected String country;
    protected String nip;

    public PaymentQRCode(String recipient, String accountNumber, double amount, String title) throws WrongInputException {
        setRecipient(recipient);
        setAccountNumber(accountNumber);
        setAmount(amount);
        setTitle(title);
    }

    public PaymentQRCode(String recipient, String accountNumber, double amount, String title, String country, String nip) throws WrongInputException {
        setRecipient(recipient);
        setAccountNumber(accountNumber);
        setAmount(amount);
        setTitle(title);
        setCountry(country);
        setNip(nip);
    }

    public String getRecipient() {
        return Optional.of(recipient).orElse("");
    }

    public String getAccountNumber() {
        return Optional.of(accountNumber).orElse("");
    }

    public double getAmount() {
        return amount;
    }

    public String getTitle() {
        return Optional.of(title).orElse("");
    }

    public String getCountry() {
        return Optional.ofNullable(country).orElse("");
    }

    public String getNip() {
        return Optional.ofNullable(nip).orElse("");
    }

    public String getQRCode() {
        return getNip() + SEPARATOR + getCountry() + SEPARATOR + getAccountNumber() + SEPARATOR + PaymentQRCodeUtils.parseAmountToString(this.amount) + SEPARATOR + getRecipient() + SEPARATOR + getTitle() + SEPARATOR + SEPARATOR + SEPARATOR;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setAccountNumber(String accountNumber) throws BadAccountNumber {
        String country = getCountry().isEmpty() ? "PL": getCountry();
        if(!PaymentQRCodeValidator.checkAccountNumber(country + accountNumber)) throw new BadAccountNumber();
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = PaymentQRCodeUtils.normalizeAmount(amount);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
