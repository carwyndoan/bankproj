package framework.bank;

public class PersonalSavingInterestCalculation implements InterestCalculation {
    private final double INTEREST = 0.6;

    @Override
    public double getInterest() {
        return INTEREST;
    }
}
