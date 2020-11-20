package edu.miu.finalProject.strategyInterface;

import edu.miu.finalProject.domain.CreditCard;

public class SilverCreditCard implements StrategyInterface {
    @Override
    public double computeInterest(double balance) {

        return 0.06*balance;
    }

    @Override
    public double computeMinimumPayment(double balance) {
        return 0.12*balance;
    }

}
