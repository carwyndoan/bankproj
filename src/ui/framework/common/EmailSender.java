package ui.framework.common;

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
        //TODO send email
        if (o instanceof Account){
            Account acc = (Account)o;
            AccountEntry entry = (AccountEntry) arg;
            if (entry.getDescription().equals("deposit"))
                System.out.println("The deposit amount is greater than $500");
            else if (entry.getDescription().equals("withdraw"))
                System.out.println("The amount is greater than your balance. The withdraw must be less than your amount");
            else if (entry.getDescription().equals("interest"))
                System.out.println("The new interest is "+ entry.getAmount());
        }
    }
}
