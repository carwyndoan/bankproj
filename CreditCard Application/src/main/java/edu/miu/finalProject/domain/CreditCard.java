package edu.miu.finalProject.domain;

public class CreditCard extends Account {

    public CreditCard(String accountNumber, String accountType) {
        super(accountNumber,accountType);
    }

//    public double getBalance() {
//        return balance;
//    }

//    public void setBalance(double balance) {
//        this.balance = balance;
//    }

//    public void setInterest() {
//        System.out.println("Account Number: " + super.getAccountNumber() + " Account Type: " + getAccountType() + " Account Balance: " + getBalance() + " Interest: " + super.getInterest().computeInterest(getBalance()));
//        AccountEntry entry = new AccountEntry(super.getInterest().computeInterest(getBalance()), "interest", "","");
//        super.getEntryList().add(entry);
//        System.out.println("New Balance after adding interest: " + getBalance());
//        System.out.println("-----------------------------------------------------------") ;
//    }
}
