package framework.creditcard;

public class BronzeCreditCard  implements CreditCardStrategyInterface {

    @Override
    public double monthlyInterest() {
        return 0.1;
    }

    @Override
    public double minimumPayment() {
        return 0.14;
    }
}
