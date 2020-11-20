package framework.bank;

public class CompanySavingInterestCalculation implements InterestCalculation {
    private final double INTEREST = 0.7;

    @Override
    public double getInterest() {
        return INTEREST;
    }
}
