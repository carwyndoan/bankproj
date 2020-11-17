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
    public void withdraw(double amount) {
        //TODO: compare with limit
        super.withdraw(amount);
    }

    @Override
    public void calculateInterest() {
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
