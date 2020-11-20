package edu.miu.finalProject.strategyInterface;

public class BronzeCreditCard  implements StrategyInterface {

    @Override
    public double computeInterest(double balance) {
        return 0.1*balance;
    }

    @Override
    public double computeMinimumPayment(double balance) {
        return 0.14*balance;
    }
}
