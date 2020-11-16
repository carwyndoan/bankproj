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
    }
}
