package ui.framework.creditcard;

import ui.framework.common.Account;

public abstract class CreditCard extends Account {

    public CreditCard(String accountNumber) {
        super(accountNumber);
    }

    abstract void monthlyInterest();
    abstract void minimumPayment();
}
