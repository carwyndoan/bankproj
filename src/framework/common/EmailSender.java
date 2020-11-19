package framework.common;

import framework.javaMailApi.SendEmailClass;

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
                SendEmailClass.sendMailTo(acc.getCustomer().getEmail(),"The deposit amount is greater than $500", "Deposit Information" );
                System.out.println("The deposit amount is greater than $500");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (entry.getDescription().equals("withdraw")){
            try {
                SendEmailClass.sendMailTo(acc.getCustomer().getEmail(), "The amount is greater than your balance. The withdraw must be less than your amount", "Withdraw Information");
                System.out.println("The amount is greater than your balance. The withdraw must be less than your amount");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (entry.getDescription().equals("interest")){
            try {
                SendEmailClass.sendMailTo(acc.getCustomer().getEmail(), "The new interest is " + entry.getAmount(), "Interest Information" );
                System.out.println("The new interest is " + entry.getAmount());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
