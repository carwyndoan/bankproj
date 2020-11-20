package framework.common;

import framework.bank.InterestCalculation;
import framework.creditcard.CreditCardStrategyInterface;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
        return getTotalCredits() - getTotalDebits() + getTotalInterest();
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
        entryList.add(entry);
        String mess = "";
        if (getCustomer().getNumofemployees() > 0){ //deposit company account
        	mess = "Your deposit is " + amount;
        	this.measureChanges(mess);
        } else if ((getCustomer().getBirthday() != null) && (amount > 500)) {//deposit personal account
        	mess = "Your deposit is "+ amount;
			this.measureChanges(mess);
		}
    }

    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(amount, "withdraw", "", "");
        String mess = "";
        if (this.getBalance() < amount) {
        	mess = "The withdrawal must be less than your balance";
            this.measureChanges(mess);
            return;
        }
        if (getCustomer().getBirthday() != null && amount > 500){//withdraw personal acc over 500$
			mess = "The withdrawal is "+ amount;
            measureChanges(mess);
        }
        entryList.add(entry);
    }

    public void interest(double amount) {
        AccountEntry entry = new AccountEntry(amount, "interest", "", "");
        entryList.add(entry);
        //TODO: check amount and call measureChanges
		String mess = "Your interest of this month is " + amount;
        measureChanges(mess);
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

	public void measureChanges(String message) {
		this.setChanged();
		this.notifyObservers(message);
	}

    public InterestCalculation getInterestCalculation() {
        return interestCalculation;
    }

    public double getTotalCredits() {
        return this.getEntryList().stream()
                .filter(entry -> entry.getDescription().equals("deposit"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    public double getTotalDebits() {
        return this.getEntryList().stream()
                .filter(entry -> entry.getDescription().equals("withdraw"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    public double getCurrentCredits() {
        LocalDate day = LocalDate.now();
        day = day.minusDays(day.getDayOfMonth() - 1);
        Date firstDate = Date.from(day.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        day = day.plusMonths(1).minusDays(day.getDayOfMonth());
        Date lastDate = Date.from(day.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return this.getEntryList().stream()
                .filter(entry -> entry.getDate().before(lastDate))
                .filter(entry -> entry.getDate().after(firstDate))
                .filter(entry -> entry.getDescription().equals("deposit"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    public double getCurrentDebits() {
        LocalDate day = LocalDate.now();
        day = day.minusDays(day.getDayOfMonth() - 1);
        Date firstDate = Date.from(day.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        day = day.plusMonths(1).minusDays(day.getDayOfMonth());
        Date lastDate = Date.from(day.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return this.getEntryList().stream()
                .filter(entry -> entry.getDate().before(lastDate))
                .filter(entry -> entry.getDate().after(firstDate))
                .filter(entry -> entry.getDescription().equals("withdraw"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    private double getTotalInterest() {
        return this.getEntryList().stream()
                .filter(entry -> entry.getDescription().equals("interest"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    private double getCurrentInterest() {
        LocalDate day = LocalDate.now();
        day = day.minusDays(day.getDayOfMonth() - 1);
        Date firstDate = Date.from(day.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        day = day.plusMonths(1).minusDays(day.getDayOfMonth());
        Date lastDate = Date.from(day.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return this.getEntryList().stream()
                .filter(entry -> entry.getDate().before(lastDate))
                .filter(entry -> entry.getDate().after(firstDate))
                .filter(entry -> entry.getDescription().equals("interest"))
                .mapToDouble(AccountEntry::getAmount).sum();
    }

    public double getPreviousBalance() {
        LocalDate firstDay = LocalDate.now();
        firstDay = firstDay.minusDays(firstDay.getDayOfMonth() - 1);
        Date firstDate = Date.from(firstDay.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

        double deposit = this.getEntryList().stream()
                .filter(entry -> entry.getDate().before(firstDate))
                .filter(entry -> entry.getDescription().equals("deposit"))
                .mapToDouble(AccountEntry::getAmount).sum();

        double withdraw = this.getEntryList().stream()
                .filter(entry -> entry.getDate().before(firstDate))
                .filter(entry -> entry.getDescription().equals("withdraw"))
                .mapToDouble(AccountEntry::getAmount).sum();

        return deposit - withdraw;
    }

    public void calculateInterest() {
        double interest = interestCalculation.getInterest();
        double totalInterest = this.getBalance() * interest;
        this.interest(totalInterest);
    }

    public String billingReport() {
        StringBuilder report = new StringBuilder();
        report.append("Name= " + getCustomer().getName());
        report.append("\n Address=" + getCustomer().getStreet());
        report.append(", " + getCustomer().getCity());
        report.append(", " + getCustomer().getState());
        report.append(", " + getCustomer().getZip());
        report.append("\r\n Account number=" + getAccountNumber());
        report.append("\r\n Account type=" + getAccountType());
        report.append("\r\nPrevious balance : $" + getPreviousBalance());
        report.append("\n Total debit : $ " + getCurrentDebits());
        report.append("\n Total credit : $ " + getCurrentCredits());
        report.append("\n Total interest : $ " + getCurrentInterest());
        report.append("\n New Balance  : $ " + getBalance());
        report.append("\r\n");
        report.append("\r\n");
        System.out.println(report);
        return report.toString();
    }
}
