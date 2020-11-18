package framework.creditcard;

public class SilverCreditCard implements CreditCardStrategyInterface {

    @Override
    public double monthlyInterest() {
        return 0.08;
    }

    @Override
    public double minimumPayment() {
        return 0.12;
    }
}
