import exceptions.*;

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

    public String getQRCode() {
        return getNip() + SEPARATOR + getCountry() + SEPARATOR + getAccountNumber() + SEPARATOR + PaymentQRCodeUtils.parseAmountToString(this.amount) + SEPARATOR + getRecipient() + SEPARATOR + getTitle() + SEPARATOR + SEPARATOR + SEPARATOR;
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

    public void setRecipient(String recipient) throws BadRecipientException {
        if(!PaymentQRCodeValidator.checkRecipient(recipient)) throw new BadRecipientException();
        this.recipient = recipient;
    }

    public void setAccountNumber(String accountNumber) throws BadAccountNumberException {
        String country = getCountry().isEmpty() ? "PL": getCountry();
        if(!PaymentQRCodeValidator.checkAccountNumber(country + accountNumber)) throw new BadAccountNumberException();
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = PaymentQRCodeUtils.normalizeAmount(amount);
    }

    public void setTitle(String title) throws BadTitleException {
        if(!PaymentQRCodeValidator.checkTitle(title)) throw new BadTitleException();
        this.title = title;
    }

    public void setCountry(String country) throws BadCountryCodeException {
        if(!PaymentQRCodeValidator.checkCountry(country)) throw new BadCountryCodeException();
        this.country = country;
    }

    public void setNip(String nip) throws BadNipException {
        if(!PaymentQRCodeValidator.checkNip(nip)) throw new BadNipException();
        this.nip = nip;
    }
}
