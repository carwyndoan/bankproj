package edu.miu.finalProject.dao;

import edu.miu.finalProject.domain.CreditCard;

import java.util.Collection;

public interface CreditCardDAO {
    void save(CreditCard creditCard);
    void update(CreditCard creditCard);
    CreditCard load(String accountnumber);
    Collection<CreditCard> getAccounts();
}
