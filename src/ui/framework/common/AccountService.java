package ui.framework.common;

import ui.framework.common.Account;

import java.time.LocalDate;
import java.util.Collection;

public interface AccountService {
    //Account createAccount(String accountNumber, String customerName);
    Account createPersonalAccount(AccountType accountType, String accountNumber, String customerName, String street, String city, String state, String zip, LocalDate birthdate, String email);
    Account createCompanyAccount(AccountType accountType, String accountNumber, String customerName, String street, String city, String state, String zip, int numOfEmployees, String email);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
}
