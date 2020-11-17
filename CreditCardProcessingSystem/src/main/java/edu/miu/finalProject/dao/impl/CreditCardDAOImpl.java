package edu.miu.finalProject.dao.impl;

import edu.miu.finalProject.dao.CreditCardDAO;
import edu.miu.finalProject.domain.CreditCard;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class CreditCardDAOImpl implements CreditCardDAO {
    Collection<CreditCard> accountlist = new CopyOnWriteArrayList<CreditCard>();

    public void save(CreditCard creditCard) {
        accountlist.add(creditCard); // add the new
    }

    public void update(CreditCard creditCard) {
        CreditCard accountexisted = load(creditCard.getAccountNumber());
        if (accountexisted != null) {
            accountlist.remove(accountexisted); // remove the old
            accountlist.add(creditCard); // add the new
        }
    }

    public CreditCard load(String accountNumber) {
        for (CreditCard account : accountlist) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public Collection<CreditCard> getAccounts() {
        return accountlist;
    }
}
