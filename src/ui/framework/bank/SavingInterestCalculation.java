package ui.framework.bank;

public class SavingInterestCalculation implements InterestCalculation {
    private final double INTEREST = 0.6;

    @Override
    public double getInterest() {
        return INTEREST;
    }
}
