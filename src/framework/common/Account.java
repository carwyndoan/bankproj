package framework.common;

import framework.bank.InterestCalculation;
import framework.creditcard.CreditCardStrategyInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

public class Account extends Observable {
	private Customer customer;

	private String accountNumber;

	private List<AccountEntry> entryList = new ArrayList<AccountEntry>();

	private InterestCalculation interestCalculation;

	private CreditCardStrategyInterface ccinterestCalculation;

	private AccountType accountType;

	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
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
		//TODO: check amount and call measureChanges
		if (amount > 500)
			this.measureChanges(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
		//TODO: check amount and call measureChanges
		if (this.getBalance() < amount)
			this.measureChanges(entry);
		else
			entryList.add(entry);
	}

	public void interest(double amount) {
		AccountEntry entry = new AccountEntry(amount, "interest", "", "");
		entryList.add(entry);
		//TODO: check amount and call measureChanges
		measureChanges(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description) {
		AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		
		entryList.add(fromEntry);
		
		toAccount.addEntry(toEntry);
		//TODO: check amount and call measureChanges
	}

	private void addEntry(AccountEntry entry) {
		entryList.add(entry);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	public void setInterestCalculation(InterestCalculation interestCalculation) {
		this.interestCalculation = interestCalculation;
	}

	public CreditCardStrategyInterface getCcinterestCalculation() {
		return ccinterestCalculation;
	}

	public void setCcinterestCalculation(CreditCardStrategyInterface ccinterestCalculation) {
		this.ccinterestCalculation = ccinterestCalculation;
	}

	public void measureChanges(AccountEntry entry) {
		this.setChanged();
		this.notifyObservers(entry);
	}

	public void calculateInterest(){
		//TODO: get interest, calculate amount, call interest
		double interest = interestCalculation.getInterest();
		double totalInterest = this.getBalance() * interest;
		this.interest(totalInterest);
	}

	public String billingReport() {
		//TODO: return record of accumulate billing of account
		return "";
	}
}
