package edu.miu.finalProject.service.impl;

import edu.miu.finalProject.domain.Account;
import edu.miu.finalProject.dao.CreditCardDAO;
import edu.miu.finalProject.dao.impl.CreditCardDAOImpl;
import edu.miu.finalProject.domain.*;
import edu.miu.finalProject.observer.EmailSender;
import edu.miu.finalProject.service.CreditCardService;
import edu.miu.finalProject.strategyInterface.BronzeCreditCard;
import edu.miu.finalProject.strategyInterface.GoldCreditCard;
import edu.miu.finalProject.strategyInterface.SilverCreditCard;

import java.util.Collection;

public class CreditCardServiceImpl  implements CreditCardService {
    private CreditCardDAO accountDao;

    public CreditCardServiceImpl() {
        this.accountDao = new CreditCardDAOImpl();
    }

    //createAccount
    public Account createCreditCard(String accountNumber, String customerName, String accountType, String email) {
        CreditCard creditCard1 = new CreditCard(accountNumber, accountType);
        Customer customer = new Customer(customerName);
        customer.setEmail(email);
        creditCard1.setCustomer(customer);
        if (accountType.equalsIgnoreCase("Gold")) {
            creditCard1.setInterest(new GoldCreditCard());
        }
        if (accountType.equalsIgnoreCase("Silver")) {
            creditCard1.setInterest(new SilverCreditCard());
        }
        if (accountType.equalsIgnoreCase("Bronze")) {
            creditCard1.setInterest(new BronzeCreditCard());

        }
        creditCard1.addObserver(new EmailSender());
        accountDao.save(creditCard1);
        return creditCard1;
    }

    public Account getAccount(String accountNumber) {
        return accountDao.load(accountNumber);    }

    public Collection<CreditCard> getAllAccounts() {
        return accountDao.getAccounts();

    }

    //deposit
    public void deposit(String accountNumber, double amount) {
        CreditCard creditCard = accountDao.load(accountNumber);
        creditCard.deposit(amount);
        accountDao.update(creditCard);
    }
    //chargeAccount
    public void chargeAccount(String accountNumber, double amount) {
        CreditCard account = accountDao.load(accountNumber);
        account.withdraw(amount);
        accountDao.update(account);
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {

    }

    //addInterest
    public void addInterest() {
        Collection<CreditCard> accounts = accountDao.getAccounts();
        for (CreditCard account : accounts) {
            account.setInterest();
            accountDao.update(account);
        }

    }


}
