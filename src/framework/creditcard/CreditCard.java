package framework.creditcard;

import framework.bank.CompanyChekingInterestCalculation;
import framework.bank.CompanySavingInterestCalculation;
import framework.bank.PersonalChekingInterestCalculation;
import framework.bank.PersonalSavingInterestCalculation;
import framework.common.Account;
import framework.common.AccountEntry;

import java.time.LocalDate;

public class CreditCard extends Account {

    private LocalDate expiryDate;

    private double limit;

    public CreditCard(String accountNumber) {
        super(accountNumber);
        limit = 3500;
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
    }

    public void deposit(double amount) {
        AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
        getEntryList().add(entry);
        if(this.getInterestCalculation() instanceof PersonalChekingInterestCalculation || this.getInterestCalculation() instanceof PersonalSavingInterestCalculation){
            if(amount > 400) {
                this.measureChanges(entry);
                System.out.println("The amount is greater than 400");
            }
        }
        if(this.getInterestCalculation() instanceof CompanyChekingInterestCalculation || this.getInterestCalculation() instanceof CompanySavingInterestCalculation){
            this.measureChanges(entry);
            System.out.println("The company Credit Card has had a deposit.");
        }
//        if (amount > 500)
//            this.measureChanges(entry);
    }


    @Override
    public void withdraw(double amount) {
        AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
        if (this.getBalance() + getLimit() < amount)
            this.measureChanges(entry);
        else if( this.getInterestCalculation() instanceof CompanySavingInterestCalculation || getInterestCalculation() instanceof CompanyChekingInterestCalculation){
            this.measureChanges(entry);
            System.out.println("Company Credit Card has been withdrawn");
        } else if( this.getInterestCalculation() instanceof PersonalSavingInterestCalculation || getInterestCalculation() instanceof PersonalChekingInterestCalculation){
            if(amount > 400)
                this.measureChanges(entry);
            System.out.println("Personal Account has been Withdrawn.");
        }
        getEntryList().add(entry);
    }

    @Override
    public String billingReport() {
        double previousBalance = this.getPreviousBalance();
        double totalCharge = this.getCurrentDebits();
        double totalCredit = this.getCurrentCredits();
        double MI = this.getCcinterestCalculation().monthlyInterest();
        double MP = this.getCcinterestCalculation().minimumPayment();
        double interest = MI * (previousBalance - totalCredit);
        if(interest < 0) interest = 0;
        double newBalance = previousBalance - totalCredit + totalCharge + interest;
        double totalDue = MP * newBalance;
        if(totalDue < 0) totalDue = 0;
        StringBuilder report =new StringBuilder();
        report.append("Name= " +this.getCustomer().getName());
        report.append("\n Address=" +this.getCustomer().getStreet());
        report.append( ", "+super.getCustomer().getCity() );
        report.append(", "+this.getCustomer().getState() );
        report.append( ", "+this.getCustomer().getZip() );
        report.append( "\r\n CC number=" +this.getAccountNumber()) ;
        report.append("\r\n CC type="+ this.getAccountType());
        report.append( "\r\nPrevious balance : $" +previousBalance);
        report.append( "\n Total charge : $ "+totalCharge );
        report.append( "\n Total credit : $ "+totalCredit );
        report.append( "\n New Balance  : $ "+newBalance );
        report.append( "\n Total Amount Due : $ "+totalDue);
        report.append( "\r\n");
        report.append( "\r\n");
        System.out.println(report);
        return report.toString();
    }
}
