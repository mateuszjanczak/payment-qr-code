import exceptions.WrongInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentQRCodeTest {

    @Test
    void paymentQRCodeTestConstructorPerson() throws WrongInputException {
        PaymentQRCode paymentQRCode = new PaymentQRCode(
                "Jan Kowalski",
                "92124012340001567890123456",
                53.50,
                "Rachunek za obiad"
        );

        assertAll(
                () -> assertEquals("Jan Kowalski", paymentQRCode.getRecipient()),
                () -> assertEquals("92124012340001567890123456", paymentQRCode.getAccountNumber()),
                () -> assertEquals(53.50, paymentQRCode.getAmount()),
                () -> assertEquals("Rachunek za obiad", paymentQRCode.getTitle())
        );
    }

    @Test
    void paymentQRCodeTestConstructorCompany() throws WrongInputException {
        PaymentQRCode paymentQRCode = new PaymentQRCode(
                "Jan Kowalski",
                "92124012340001567890123456",
                53.50,
                "Rachunek za obiad",
                "PL",
                "1234567890"
        );

        assertAll(
                () -> assertEquals("Jan Kowalski", paymentQRCode.getRecipient()),
                () -> assertEquals("92124012340001567890123456", paymentQRCode.getAccountNumber()),
                () -> assertEquals(53.50, paymentQRCode.getAmount()),
                () -> assertEquals("Rachunek za obiad", paymentQRCode.getTitle()),
                () -> assertEquals("PL", paymentQRCode.getCountry()),
                () -> assertEquals("1234567890", paymentQRCode.getNip())
        );
    }

    @Test
    void getQRCode() throws WrongInputException {
        PaymentQRCode paymentQRCode = new PaymentQRCode(
                "Jan Kowalski",
                "92124012340001567890123456",
                53.50,
                "Rachunek za obiad"
        );

        assertEquals("||92124012340001567890123456|005350|Jan Kowalski|Rachunek za obiad|||", paymentQRCode.getQRCode());
    }
}