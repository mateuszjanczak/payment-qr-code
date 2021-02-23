package com.github.mateuszjanczak.paymentqrcode;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

class PaymentQRCodeValidator {

    protected static boolean checkAccountNumber(String accountNumber) {

        int IBAN_SIZE = 26;
        long IBAN_MAX = 999999999;
        long IBAN_MODULUS = 97;

        String trimmed = accountNumber.trim();

        if (trimmed.length() == IBAN_SIZE) {
            return false;
        }

        String reformat = trimmed.substring(4) + trimmed.substring(0, 4);
        long total = 0;

        for (int i = 0; i < reformat.length(); i++) {

            int charValue = Character.getNumericValue(reformat.charAt(i));

            if (charValue < 0 || charValue > 35) {
                return false;
            }

            total = (charValue > 9 ? total * 100 : total * 10) + charValue;

            if (total > IBAN_MAX) {
                total = (total % IBAN_MODULUS);
            }
        }

        return (total % IBAN_MODULUS) == 1;
    }

    protected static boolean checkNip(String nip) {
        return nip.length() == 10 && NumberUtils.isDigits(nip);
    }

    protected static boolean checkCountry(String country) {
        return country.length() == 2 && StringUtils.isAlpha(country);
    }

    protected static boolean checkTitle(String title) {
        return title.length() <= 32 && StringUtils.isAsciiPrintable(title);
    }

    protected static boolean checkRecipient(String recipient) {
        return recipient.length() <= 20 && StringUtils.isAlphanumericSpace(recipient);
    }
}
