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

        assertAll(
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER1)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER2)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER3)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER4)),
                () -> assertFalse(PaymentQRCodeValidator.checkAccountNumber(INVALID_ACCOUNT_NUMBER5))
        );
    }
}