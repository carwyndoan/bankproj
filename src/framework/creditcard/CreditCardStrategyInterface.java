package framework.creditcard;

import framework.bank.InterestCalculation;

public interface CreditCardStrategyInterface {
    double monthlyInterest();
    double minimumPayment();
}
