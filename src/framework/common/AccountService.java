package framework.common;

import java.time.LocalDate;
import java.util.Collection;

public interface AccountService {
    //Account createAccount(String accountNumber, String customerName);
    Account createAccount(String accountNumber, String customerName, String street, String city, String state, String zip, String email);
//    Account createAccount(AccountType accountType, String accountNumber, String customerName, String street, String city, String state, String zip, LocalDate birthday, String email);
//    Account createAccount(AccountType accountType, String accountNumber, String customerName, String street, String city, String state, String zip, int numofEmployees, String email);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void calculateInterest();
}
