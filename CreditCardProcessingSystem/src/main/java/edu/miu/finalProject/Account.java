package edu.miu.finalProject;

import edu.miu.finalProject.dao.AccountEntry;
import edu.miu.finalProject.domain.Customer;
import edu.miu.finalProject.strategyInterface.StrategyInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Account {

    private Customer customer;

    private String accountNumber;
    private String accountType;

    StrategyInterface interest;

    private List<AccountEntry> entryList = new ArrayList<AccountEntry>();

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account() {
    }

    public Account(String accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        double balance = 0;
        for (AccountEntry entry : entryList) {
            balance += entry.getAmount();
        }
        return balance;
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
        entryList.add(entry);
    }

    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
        entryList.add(entry);
    }

    private void addEntry(AccountEntry entry) {
        entryList.add(entry);
    }

    public void transferFunds(Account toAccount, double amount, String description) {
        AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());
        AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
                toAccount.getCustomer().getName());

        entryList.add(fromEntry);

        toAccount.addEntry(toEntry);
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StrategyInterface getInterest() {
        return interest;
    }

    public void setInterest(StrategyInterface interest) {
        this.interest = interest;
    }

    public Collection<AccountEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<AccountEntry> entryList) {
        this.entryList = entryList;
    }

}
