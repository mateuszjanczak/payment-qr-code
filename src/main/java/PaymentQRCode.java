import exceptions.*;

import java.util.Objects;
import java.util.Optional;

public class PaymentQRCode {

    protected final String SEPARATOR = "|";

    protected String recipient;
    protected String accountNumber;
    protected double amount;
    protected String title;
    protected String country;
    protected String nip;

    public PaymentQRCode(String recipient, String accountNumber, double amount, String title, String country) throws WrongInputException {
        setRecipient(recipient);
        setAccountNumber(accountNumber);
        setAmount(amount);
        setTitle(title);
        setCountry(country);
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
        if (!PaymentQRCodeValidator.checkRecipient(recipient)) throw new BadRecipientException();
        this.recipient = recipient;
    }

    public void setAccountNumber(String accountNumber) throws BadAccountNumberException {
        String country = getCountry().isEmpty() ? "PL" : getCountry();
        if (!PaymentQRCodeValidator.checkAccountNumber(country + accountNumber)) throw new BadAccountNumberException();
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = PaymentQRCodeUtils.normalizeAmount(amount);
    }

    public void setTitle(String title) throws BadTitleException {
        if (!PaymentQRCodeValidator.checkTitle(title)) throw new BadTitleException();
        this.title = title;
    }

    public void setCountry(String country) throws BadCountryCodeException {
        if (!PaymentQRCodeValidator.checkCountry(country)) throw new BadCountryCodeException();
        this.country = country;
    }

    public void setNip(String nip) throws BadNipException {
        if (!PaymentQRCodeValidator.checkNip(nip)) throw new BadNipException();
        this.nip = nip;
    }

    public static interface RecipientStep {
        AccountNumberStep withRecipient(String recipient);
    }

    public static interface AccountNumberStep {
        AmountStep withAccountNumber(String accountNumber);
    }

    public static interface AmountStep {
        TitleStep withAmount(double amount);
    }

    public static interface TitleStep {
        CountryStep withTitle(String title);
    }

    public static interface CountryStep {
        NipStep withCountry(String country);
    }

    public static interface NipStep {
        BuildStep withNip(String nip);
        PaymentQRCode build() throws WrongInputException;
    }

    public static interface BuildStep {
        PaymentQRCode build() throws WrongInputException;
    }

    public static class Builder implements RecipientStep, AccountNumberStep, AmountStep, TitleStep, CountryStep, NipStep, BuildStep {
        private String recipient;
        private String accountNumber;
        private double amount;
        private String title;
        private String country;
        private String nip;

        private Builder() {
        }

        public static RecipientStep paymentQRCode() {
            return new Builder();
        }

        @Override
        public AccountNumberStep withRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        @Override
        public AmountStep withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        @Override
        public TitleStep withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public CountryStep withTitle(String title) {
            this.title = title;
            return this;
        }

        @Override
        public NipStep withCountry(String country) {
            this.country = country;
            return this;
        }

        @Override
        public BuildStep withNip(String nip) {
            this.nip = nip;
            return this;
        }

        @Override
        public PaymentQRCode build() throws WrongInputException {
            if(Objects.isNull(this.nip)) {
                return new PaymentQRCode(
                        this.recipient,
                        this.accountNumber,
                        this.amount,
                        this.title,
                        this.country
                );
            } else {
                return new PaymentQRCode(
                        this.recipient,
                        this.accountNumber,
                        this.amount,
                        this.title,
                        this.country,
                        this.nip
                );
            }
        }
    }
}