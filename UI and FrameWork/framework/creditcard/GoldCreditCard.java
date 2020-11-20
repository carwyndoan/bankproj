package framework.creditcard;

public class GoldCreditCard implements CreditCardStrategyInterface {

    @Override
    public double monthlyInterest() {
        return 0.06;
    }

    @Override
    public double minimumPayment() {
        return 0.1;
    }
}
