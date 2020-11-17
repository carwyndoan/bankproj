package framework.creditcard;

public class SilverCreditCard implements CreditCardStrategyInterface {

    @Override
    public double monthlyInterest() {
        return 0;
    }

    @Override
    public double minimumPayment() {
        return 0;
    }
}
