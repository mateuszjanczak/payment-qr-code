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
                "Rachunek za obiad",
                "PL"
        );

        assertAll(
                () -> assertEquals("Jan Kowalski", paymentQRCode.getRecipient()),
                () -> assertEquals("92124012340001567890123456", paymentQRCode.getAccountNumber()),
                () -> assertEquals(53.50, paymentQRCode.getAmount()),
                () -> assertEquals("Rachunek za obiad", paymentQRCode.getTitle()),
                () -> assertEquals("PL", paymentQRCode.getCountry())
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
    void getQRCodeTest() throws WrongInputException {
        PaymentQRCode paymentQRCode = new PaymentQRCode(
                "Jan Kowalski",
                "92124012340001567890123456",
                53.50,
                "Rachunek za obiad",
                "PL"
        );

        assertEquals("|PL|92124012340001567890123456|005350|Jan Kowalski|Rachunek za obiad|||", paymentQRCode.getQRCodeSubject());
    }

    @Test
    void builderPersonTest() throws WrongInputException {
        PaymentQRCode paymentQRCode = PaymentQRCode.Builder
                .paymentQRCode()
                .withRecipient("Jan Kowalski")
                .withAccountNumber("92124012340001567890123456")
                .withAmount(53.50)
                .withTitle("Rachunek za obiad")
                .withCountry("PL")
                .build();

        assertEquals("|PL|92124012340001567890123456|005350|Jan Kowalski|Rachunek za obiad|||", paymentQRCode.getQRCodeSubject());
    }

    @Test
    void builderCompanyTest() throws WrongInputException {
        PaymentQRCode paymentQRCode = PaymentQRCode.Builder
                .paymentQRCode()
                .withRecipient("Jan Kowalski")
                .withAccountNumber("92124012340001567890123456")
                .withAmount(53.50)
                .withTitle("Rachunek za obiad")
                .withCountry("PL")
                .withNip("1234567890")
                .build();

        assertEquals("1234567890|PL|92124012340001567890123456|005350|Jan Kowalski|Rachunek za obiad|||", paymentQRCode.getQRCodeSubject());
    }
}