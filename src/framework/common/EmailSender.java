package framework.common;

import framework.javaMailApi.SendEmailClass;
//import framework.javaMailApi.SendEmailPrivate;

import java.util.Observable;
import java.util.Observer;

public class EmailSender implements Observer {
    private  Observable ob;
    public EmailSender(Observable ob){
        this.ob = ob;
        this.ob.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        Account acc = (Account)o;
        AccountEntry entry = (AccountEntry) arg;
        if (entry.getDescription().equals("deposit")) {
            try {
//                SendEmailPrivate.sendMailTo(acc.getCustomer().getEmail(),"The deposit amount is greater than $500" );
                System.out.println("The deposit amount is greater than $500");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (entry.getDescription().equals("withdraw")){
            try {
//                SendEmailPrivate.sendMailTo(acc.getCustomer().getEmail(), "The amount is greater than your balance. The withdraw must be less than your amount");
                System.out.println("The amount is greater than your balance. The withdraw must be less than your amount");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (entry.getDescription().equals("interest")){
            try {
//                SendEmailPrivate.sendMailTo(acc.getCustomer().getEmail(), "The new interest is " + entry.getAmount() );
                System.out.println("The new interest is " + entry.getAmount());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
