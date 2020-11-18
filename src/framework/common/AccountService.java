package framework.common;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface AccountService {
    Account createAccount(String accountNumber, String customerName, String street, String city, String state, String zip, String email);
    Account createCreditCard(String accountNumber, String customerName, String street, String city, String state, String zip, String email);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void calculateInterest();
    List<String> printBankStatement();
}
