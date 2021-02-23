package com.github.mateuszjanczak.paymentqrcode;

import com.github.mateuszjanczak.paymentqrcode.exceptions.*;

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
        setCountry(country);
        setAccountNumber(accountNumber);
        setAmount(amount);
        setTitle(title);
    }

    public PaymentQRCode(String recipient, String accountNumber, double amount, String title, String country, String nip) throws WrongInputException {
        setRecipient(recipient);
        setCountry(country);
        setAccountNumber(accountNumber);
        setAmount(amount);
        setTitle(title);
        setNip(nip);
    }

    public String getQRCodeSubject() {
        return getNip() + SEPARATOR + getCountry() + SEPARATOR + getAccountNumber() + SEPARATOR + PaymentQRCodeUtils.parseAmountToString(this.amount) + SEPARATOR + getRecipient() + SEPARATOR + getTitle() + SEPARATOR + SEPARATOR + SEPARATOR;
    }

    public String getRecipient() {
        return Optional.of(recipient).orElse("");
    }

    public void setRecipient(String recipient) throws BadRecipientException {
        if (!PaymentQRCodeValidator.checkRecipient(recipient)) throw new BadRecipientException();
        this.recipient = recipient;
    }

    public String getAccountNumber() {
        return Optional.of(accountNumber).orElse("");
    }

    public void setAccountNumber(String accountNumber) throws BadAccountNumberException {
        if (!PaymentQRCodeValidator.checkAccountNumber(getCountry() + accountNumber))
            throw new BadAccountNumberException();
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = PaymentQRCodeUtils.normalizeAmount(amount);
    }

    public String getTitle() {
        return Optional.of(title).orElse("");
    }

    public void setTitle(String title) throws BadTitleException {
        if (!PaymentQRCodeValidator.checkTitle(title)) throw new BadTitleException();
        this.title = title;
    }

    public String getCountry() {
        return Optional.ofNullable(country).orElse("");
    }

    public void setCountry(String country) throws BadCountryCodeException {
        if (!PaymentQRCodeValidator.checkCountry(country)) throw new BadCountryCodeException();
        this.country = country;
    }

    public String getNip() {
        return Optional.ofNullable(nip).orElse("");
    }

    public void setNip(String nip) throws BadNipException {
        if (!PaymentQRCodeValidator.checkNip(nip)) throw new BadNipException();
        this.nip = nip;
    }

    public interface RecipientStep {
        AccountNumberStep withRecipient(String recipient);
    }

    public interface AccountNumberStep {
        AmountStep withAccountNumber(String accountNumber);
    }

    public interface AmountStep {
        TitleStep withAmount(double amount);
    }

    public interface TitleStep {
        CountryStep withTitle(String title);
    }

    public interface CountryStep {
        NipStep withCountry(String country);
    }

    public interface NipStep {
        BuildStep withNip(String nip);

        PaymentQRCode build() throws WrongInputException;
    }

    public interface BuildStep {
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
            if (Objects.isNull(this.nip)) {
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