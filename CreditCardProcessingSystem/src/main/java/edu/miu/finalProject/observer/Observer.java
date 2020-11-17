package edu.miu.finalProject.observer;

import edu.miu.finalProject.Account;
import edu.miu.finalProject.domain.CreditCard;

public interface Observer {
    public void update(Account account);
}
