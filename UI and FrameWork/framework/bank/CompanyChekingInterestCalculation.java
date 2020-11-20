package framework.bank;

public class CompanyChekingInterestCalculation implements InterestCalculation {
    private final double INTEREST = 0.35;

    @Override
    public double getInterest() {
        return INTEREST;
    }
}
