package framework.creditcard;

import framework.common.Account;

import java.time.LocalDate;

public class CreditCard extends Account {

    private LocalDate expiryDate;

    private double limit;

    public CreditCard(String accountNumber) {
        super(accountNumber);
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public void calculateInterest() {
        //TODO: get interest, calculate amount, call interest
        double interest = getCcinterestCalculation().monthlyInterest();
        double totalInterest = this.getBalance() * interest;
        this.interest(totalInterest);
    }

    @Override
    public String billingReport() {
        //TODO: return record of accumulate billing of account
        return "";
    }
}
