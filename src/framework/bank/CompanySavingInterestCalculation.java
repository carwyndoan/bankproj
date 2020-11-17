package framework.bank;

public class CompanySavingInterestCalculation implements InterestCalculation {
    private final double INTEREST = 0.6;

    @Override
    public double getInterest() {
        return INTEREST;
    }
}
