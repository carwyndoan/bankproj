package edu.miu.finalProject.observer;

import com.sun.tools.doclets.internal.toolkit.util.ClassUseMapper;
import edu.miu.finalProject.domain.Account;
import edu.miu.finalProject.domain.Customer;
import edu.miu.finalProject.javaMailApi.SendEmailClass;

public class EmailSender implements Observer {
    @Override
    public void update(Account account){
        String email = account.getCustomer().getEmail();
        String message = "account number: " + account.getAccountNumber() + " balance: " + account.getBalance();
        sendEmail(message, email);
    }

    void sendEmail(String message, String email){
        try {
            SendEmailClass.sendMailTo(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EmailSender: " + message);
        System.out.println("---------------");
    }
}
