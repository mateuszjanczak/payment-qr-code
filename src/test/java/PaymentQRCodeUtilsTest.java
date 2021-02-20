import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentQRCodeUtilsTest {

    @Test
    void normalizeAmount() {
        double amountInteger = 53;
        double amountFloat1 = 53.50;
        double amountFloat2 = 53.50987;
        double amountFloat3 = 53.5;

        assertAll(
                () -> assertEquals(53, PaymentQRCodeUtils.normalizeAmount(amountInteger)),
                () -> assertEquals(53.50, PaymentQRCodeUtils.normalizeAmount(amountFloat1)),
                () -> assertEquals(53.50, PaymentQRCodeUtils.normalizeAmount(amountFloat2)),
                () -> assertEquals(53.50, PaymentQRCodeUtils.normalizeAmount(amountFloat3))
        );
    }

    @Test
    void parseAmountToString() {
        double amountInteger1 = 53;
        double amountInteger2 = 5300;
        double amountInteger3 = 530000;
        double amountFloat1 = 53.5;
        double amountFloat2 = 53.55;

        assertAll(
                () -> assertEquals("005300", PaymentQRCodeUtils.parseAmountToString(amountInteger1)),
                () -> assertEquals("530000", PaymentQRCodeUtils.parseAmountToString(amountInteger2)),
                () -> assertEquals("53000000", PaymentQRCodeUtils.parseAmountToString(amountInteger3)),
                () -> assertEquals("005350", PaymentQRCodeUtils.parseAmountToString(amountFloat1)),
                () -> assertEquals("005355", PaymentQRCodeUtils.parseAmountToString(amountFloat2))
        );
    }
}