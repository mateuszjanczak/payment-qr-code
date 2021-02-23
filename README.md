## Payment QR Code ![CircleCI](https://circleci.com/gh/mateuszjanczak/payment-qr-code.svg?style=svg&circle-token=d4addbd70fd7d6ee51387795587db6a1505da19e)
Library for formatting data for bank transfers for qr code generators.

### Installation
```xml
<dependency>
    <groupId>com.github.mateuszjanczak</groupId>
    <artifactId>payment-qr-code</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Usage
```java
PaymentQRCode paymentQRCode = PaymentQRCode.Builder
        .paymentQRCode()
        .withRecipient("John Smith")
        .withAccountNumber("92124012340001567890123456")
        .withAmount(53.50)
        .withTitle("Bill for dinner")
        .withCountry("PL")
        .build();

String subject = paymentQRCode.getQRCodeSubject();
```

### Footnotes
Library implements [Rekomendacja Związku Banków Polskich dotycząca kodu dwuwymiarowego („2D”), umożliwiającego realizację polecenia przelewu oraz aktywację usług bankowych na rynku polskim - wersja 1.0](https://zbp.pl/getmedia/1d7fef90-d193-4a2d-a1c3-ffdf1b0e0649/2013-12-03_-_Rekomendacja_-_Standard_2D)

### Example libraries for generating QR codes
[zxing](https://github.com/zxing/zxing)

### Contributing
Feel free to contribute

### License
Payment-QR-Code is open-sourced software licensed under the MIT license
