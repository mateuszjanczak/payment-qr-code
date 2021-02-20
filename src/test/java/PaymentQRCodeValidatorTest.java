import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentQRCodeValidatorTest {

    @Test
    void checkAccountNumberValid() {
        final String VALID_ACCOUNT_NUMBER = "PL89109024027276831641391919";
        assertTrue(PaymentQRCodeValidator.checkAccountNumber(VALID_ACCOUNT_NUMBER));
    }

    @Test
    void checkAccountNumberInvalid() {
        final String INVALID_ACCOUNT_NUMBER1 = "89109024027276831641391919";
        final String INVALID_ACCOUNT_NUMBER2 = "ZZ89109024027276831641391919";
        final String INVALID_ACCOUNT_NUMBER3 = "891090240272.6831641391919";
        final String INVALID_ACCOUNT_NUMBER4 = "PL1234";
        final String INVALID_ACCOUNT_NUMBER5 = "PL1234891090240272768316413919195678";
        final String INVALID_ACCOUNT_NUMBER6 = "!@#$%^&*()_+";

        assertAll(
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER1)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER2)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER3)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER4)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER5)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER6))
        );
    }

    @Test
    void checkNipValid() {
        final String VALID_NIP1 = "1547480906";
        final String VALID_NIP2 = "1222234377";
        final String VALID_NIP3 = "7580309372";

        assertAll(
                () -> assertTrue(PaymentQRCodeValidator.checkNip(VALID_NIP1)),
                () -> assertTrue(PaymentQRCodeValidator.checkNip(VALID_NIP2)),
                () -> assertTrue(PaymentQRCodeValidator.checkNip(VALID_NIP3))
        );
    }

    @Test
    void checkNipInvalid() {
        final String INVALID_NIP1 = "15474";
        final String INVALID_NIP2 = "122223437712345";
        final String INVALID_NIP3 = "75803 9372";
        final String INVALID_NIP4 = "75803A9372";
        final String INVALID_NIP5 = "75804.3721";

        assertAll(
                () -> assertFalse(PaymentQRCodeValidator.checkNip(INVALID_NIP1)),
                () -> assertFalse(PaymentQRCodeValidator.checkNip(INVALID_NIP2)),
                () -> assertFalse(PaymentQRCodeValidator.checkNip(INVALID_NIP3)),
                () -> assertFalse(PaymentQRCodeValidator.checkNip(INVALID_NIP4)),
                () -> assertFalse(PaymentQRCodeValidator.checkNip(INVALID_NIP5))
        );
    }

    @Test
    void checkCountryValid() {
        final String VALID_COUNTRY1 = "PL";
        final String VALID_COUNTRY2 = "UK";
        final String VALID_COUNTRY3 = "DE";

        assertAll(
                () -> assertTrue(PaymentQRCodeValidator.checkCountry(VALID_COUNTRY1)),
                () -> assertTrue(PaymentQRCodeValidator.checkCountry(VALID_COUNTRY2)),
                () -> assertTrue(PaymentQRCodeValidator.checkCountry(VALID_COUNTRY3))
        );
    }

    @Test
    void checkCountryInvalid() {
        final String INVALID_COUNTRY1 = "POL";
        final String INVALID_COUNTRY2 = "12";
        final String INVALID_COUNTRY3 = "!@";
        final String INVALID_COUNTRY4 = "D";

        assertAll(
                () -> assertFalse(PaymentQRCodeValidator.checkCountry(INVALID_COUNTRY1)),
                () -> assertFalse(PaymentQRCodeValidator.checkCountry(INVALID_COUNTRY2)),
                () -> assertFalse(PaymentQRCodeValidator.checkCountry(INVALID_COUNTRY3)),
                () -> assertFalse(PaymentQRCodeValidator.checkCountry(INVALID_COUNTRY4))
        );
    }

    @Test
    void checkTitleValid() {
        final String VALID_TITLE1 = "FV 1234/34/2012";
        final String VALID_TITLE2 = "Payment for coffee";

        assertAll(
                () -> assertTrue(PaymentQRCodeValidator.checkTitle(VALID_TITLE1)),
                () -> assertTrue(PaymentQRCodeValidator.checkTitle(VALID_TITLE2))
        );
    }

    @Test
    void checkTitleInvalid() {
        final String INVALID_TITLE1 = "QWERTYUIOPASDFGHJKLZXCVBNMQWERTY1";
        final String INVALID_TITLE2 = "the \n cat in the hat";

        assertAll(
                () -> assertFalse(PaymentQRCodeValidator.checkTitle(INVALID_TITLE1)),
                () -> assertFalse(PaymentQRCodeValidator.checkTitle(INVALID_TITLE2))
        );
    }

    @Test
    void checkRecipientValid() {
        final String VALID_RECIPIENT1 = "Jan Kowalski";
        final String VALID_RECIPIENT2 = "Adam Nowak";
        final String VALID_RECIPIENT3 = "Company 90";

        assertAll(
                () -> assertTrue(PaymentQRCodeValidator.checkRecipient(VALID_RECIPIENT1)),
                () -> assertTrue(PaymentQRCodeValidator.checkRecipient(VALID_RECIPIENT2)),
                () -> assertTrue(PaymentQRCodeValidator.checkRecipient(VALID_RECIPIENT3))
        );
    }

    @Test
    void checkRecipientInvalid() {
        final String INVALID_RECIPIENT1 = "ABC !@#$%^&*()_";
        final String INVALID_RECIPIENT2 = "QWERTYUIOPASDFGHJKLZXCVBNM";

        assertAll(
                () -> assertFalse(PaymentQRCodeValidator.checkRecipient(INVALID_RECIPIENT1)),
                () -> assertFalse(PaymentQRCodeValidator.checkRecipient(INVALID_RECIPIENT2))
        );
    }
}