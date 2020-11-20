package framework.common;

import framework.javaMailApi.SendEmailClass;

import java.util.Observable;
import java.util.Observer;

public class EmailSender implements Observer {
    private Observable ob;

    public EmailSender(Observable ob) {
        this.ob = ob;
        this.ob.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Account acc = (Account) o;
        String message = (String) arg;
        try {
            SendEmailClass.sendMailTo(acc.getCustomer().getEmail(), message, "The Account changed");
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
