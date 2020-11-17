package edu.miu.finalProject.strategyInterface;

import edu.miu.finalProject.domain.CreditCard;

public class SilverCreditCard implements StrategyInterface {
    @Override
    public double computeInterest(double balance) {
//        if (balance > 1000) {
//            return 0.025 * balance;
//        } else {
//            return 0.015 * balance;
//        }
        return 0.06*balance;
    }

}
