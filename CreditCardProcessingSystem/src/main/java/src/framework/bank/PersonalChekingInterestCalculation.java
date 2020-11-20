package framework.bank;

public class PersonalChekingInterestCalculation implements InterestCalculation {
    private final double INTEREST = 0.25;

    @Override
    public double getInterest() {
        return INTEREST;
    }
}
