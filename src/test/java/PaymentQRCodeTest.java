import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentQRCodeTest {

    @Test
    void paymentQRCodeTestConstructor(){
        PaymentQRCode paymentQRCode = new PaymentQRCode(
                "Jan Kowalski",
                "96102005401003791781779627",
                53.50,
                "Rachunek za obiad"
        );

        assertEquals("Jan Kowalski", paymentQRCode.getRecipient());
        assertEquals("96102005401003791781779627", paymentQRCode.getAccountNumber());
        assertEquals(53.50, paymentQRCode.getAmount());
        assertEquals("Rachunek za obiad", paymentQRCode.getTitle());
    }

    @Test
    void normalizeAmount() {
        PaymentQRCode integer = new PaymentQRCode();
        integer.setAmount(53);

        PaymentQRCode float1 = new PaymentQRCode();
        float1.setAmount(53.50);

        PaymentQRCode float2 = new PaymentQRCode();
        float2.setAmount(53.50987);

        PaymentQRCode float3 = new PaymentQRCode();
        float3.setAmount(53.5);

        assertEquals(53, integer.getAmount());
        assertEquals(53.50, float1.getAmount());
        assertEquals(53.50, float2.getAmount());
        assertEquals(53.50, float3.getAmount());
    }

    @Test
    void getQRCode() {
        PaymentQRCode paymentQRCode = new PaymentQRCode(
                "Jan Kowalski",
                "96102005401003791781779627",
                53.50,
                "Rachunek za obiad"
        );

        assertEquals("||96102005401003791781779627|005350|Jan Kowalski|Rachunek za obiad|||", paymentQRCode.getQRCode());
    }

    @Test
    void parseAmountToInteger() {
        PaymentQRCode paymentQRCode = new PaymentQRCode();

        double amountInteger1 = 53;
        double amountInteger2 = 5300;
        double amountInteger3 = 530000;
        double amountFloat1 = 53.5;
        double amountFloat2 = 53.55;

        assertEquals("005300", paymentQRCode.parseAmountToInteger(amountInteger1));
        assertEquals("530000", paymentQRCode.parseAmountToInteger(amountInteger2));
        assertEquals("53000000", paymentQRCode.parseAmountToInteger(amountInteger3));
        assertEquals("005350", paymentQRCode.parseAmountToInteger(amountFloat1));
        assertEquals("005355", paymentQRCode.parseAmountToInteger(amountFloat2));
    }
}