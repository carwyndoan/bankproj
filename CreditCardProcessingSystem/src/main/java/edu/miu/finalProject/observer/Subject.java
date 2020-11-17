package edu.miu.finalProject.observer;

import edu.miu.finalProject.Account;
import edu.miu.finalProject.domain.CreditCard;

import java.util.ArrayList;
import java.util.Collection;

public class Subject {

    Collection<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObservable(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObserver(Account account) {
        for (Observer observer : observers) {
            observer.update(account);
        }
    }
}
