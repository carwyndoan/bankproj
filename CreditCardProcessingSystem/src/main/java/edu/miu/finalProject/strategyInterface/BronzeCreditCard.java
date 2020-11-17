package edu.miu.finalProject.strategyInterface;

public class BronzeCreditCard  implements StrategyInterface {

    @Override
    public double computeInterest(double balance) {
//        if (balance > 1000) {
//            return 0.025 * balance;
//        } else {
//            return 0.015 * balance;
//        }
        return 0.1*balance;
    }
}
