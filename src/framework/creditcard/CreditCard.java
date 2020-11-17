package framework.creditcard;

import framework.common.Account;

import java.time.LocalDate;

public abstract class CreditCard extends Account {

    private LocalDate expiryDate;
    public CreditCard(String accountNumber) {
        super(accountNumber);
    }

    abstract void monthlyInterest();
    abstract void minimumPayment();

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
