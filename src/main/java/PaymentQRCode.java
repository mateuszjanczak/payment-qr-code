public class PaymentQRCode {

    protected final String SEPARATOR = "|";

    protected String recipient;
    protected String accountNumber;
    protected double amount;
    protected String title;

    public PaymentQRCode() {
    }

    public PaymentQRCode(String recipient, String accountNumber, double amount, String title) {
        setRecipient(recipient);
        setAccountNumber(accountNumber);
        setAmount(amount);
        setTitle(title);
    }

    public String getRecipient() {
        return recipient;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public String getQRCode() {
        return SEPARATOR + SEPARATOR + getAccountNumber() + SEPARATOR + parseAmountToInteger(this.amount) + SEPARATOR + getRecipient() + SEPARATOR + getTitle() + SEPARATOR + SEPARATOR + SEPARATOR;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = normalizeAmount(amount);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected String parseAmountToInteger(double amount) {

        StringBuilder parsedAmount;

        if ((int) amount == amount) {
            parsedAmount = new StringBuilder((int) amount + "00");
        } else {
            parsedAmount = new StringBuilder(String.valueOf((int) (amount * 100)));
        }

        while(parsedAmount.length() < 6) {
            parsedAmount.insert(0, "0");
        }

        return parsedAmount.toString();
    }

    protected double normalizeAmount(double amount){
        if((int) amount == amount) {
            return amount;
        } else {
            int temp = (int)(amount * 100.0);
            return ((double) temp) / 100.0;
        }
    }
}
