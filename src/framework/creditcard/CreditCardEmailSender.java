package framework.creditcard;

import framework.common.Account;
import framework.common.AccountEntry;
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
        Account acc = (Account)o;
        AccountEntry entry = (AccountEntry) arg;
        if (entry.getDescription().equals("withdraw")) {
            try {
                SendEmailClass.sendMailTo(acc.getCustomer().getEmail());
                System.out.println("The amount is greater than 400$.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
