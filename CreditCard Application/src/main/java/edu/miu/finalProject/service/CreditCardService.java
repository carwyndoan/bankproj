package edu.miu.finalProject.service;
import edu.miu.finalProject.domain.Account;
import edu.miu.finalProject.domain.CreditCard;

import java.util.Collection;

public interface CreditCardService {
    Account createCreditCard(String accountNumber, String customerName, String accountType, String email);
    Account getAccount(String accountNumber);
    Collection<CreditCard> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void chargeAccount (String accountNumber, double amount);
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void addInterest();
}
