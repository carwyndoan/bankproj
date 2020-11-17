package edu.miu.finalProject.observer;

import edu.miu.finalProject.Account;

public class EmailSender implements Observer {
    @Override
    public void update(Account account) {
        String message = "account number: " + account.getAccountNumber() + " balance: " + account.getBalance();
        sendEmail(message);
    }

    void sendEmail(String message) {
        System.out.println("EmailSender: " + message);
        System.out.println("---------------");
    }
}
