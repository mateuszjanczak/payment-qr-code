import com.github.mateuszjanczak.paymentqrcode.PaymentQRCode;
import com.github.mateuszjanczak.paymentqrcode.exceptions.WrongInputException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {
    private static final String QR_CODE_IMAGE_PATH = "./MyQRCode.png";

    public static void main(String[] args) {
        try {
            PaymentQRCode paymentQRCode = PaymentQRCode.Builder
                    .paymentQRCode()
                    .withRecipient("Jan Kowalski")
                    .withAccountNumber("92124012340001567890123456")
                    .withAmount(53.50)
                    .withTitle("Rachunek za obiad")
                    .withCountry("PL")
                    .build();

            generateQRCodeImage(paymentQRCode.getQRCodeSubject(), 250, 250, QR_CODE_IMAGE_PATH);
        } catch (WrongInputException e) {
            System.out.println("PaymentQRCode : " + e.getMessage());
        } catch (WriterException | IOException e) {
            System.err.println("Writer / IO : " + e.getMessage());
        }
    }

    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
