class PaymentQRCodeUtils {
    protected static double normalizeAmount(double amount){
        int temp = (int)(amount * 100.0);
        return ((double) temp) / 100.0;
    }

    protected static String parseAmountToString(double amount) {

        StringBuilder parsedAmount = new StringBuilder(String.valueOf((int) (amount * 100)));

        while(parsedAmount.length() < 6) {
            parsedAmount.insert(0, "0");
        }

        return parsedAmount.toString();
    }
}
