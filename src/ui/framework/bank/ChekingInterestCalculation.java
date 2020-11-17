package ui.framework.bank;

public class ChekingInterestCalculation implements InterestCalculation {
    private final double INTEREST = 0.25;

    @Override
    public double getInterest(double balance) {
        return INTEREST;
    }
}
