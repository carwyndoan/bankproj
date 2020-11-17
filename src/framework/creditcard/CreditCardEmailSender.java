package framework.creditcard;

import framework.common.Account;
import framework.javaMailApi.SendEmailClass;

import java.util.Observable;
import java.util.Observer;

public class CreditCardEmailSender implements Observer {
    private  Observable ob;
    public CreditCardEmailSender(Observable ob){
        this.ob = ob;
        this.ob.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Account account = (Account) o;
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
