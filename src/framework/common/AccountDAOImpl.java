package framework.common;

import java.util.ArrayList;
import java.util.Collection;

public class AccountDAOImpl implements AccountDAO {
	private volatile static AccountDAOImpl uniqueInstance;

	private AccountDAOImpl(){}

	public static AccountDAOImpl getInstance(){
		if(uniqueInstance == null) {
			synchronized (AccountDAOImpl.class){
				if(uniqueInstance == null){
					uniqueInstance = new AccountDAOImpl();
				}
			}
		}
		return uniqueInstance;
	}

	Collection<Account> accountlist = new ArrayList<Account>();

	Collection<Customer> customerlist = new ArrayList<Customer>();

	public void saveAccount(Account account) {
		accountlist.add(account); // add the new
	}

	public void updateAccount(Account account) {
		Account accountexist = loadAccount(account.getAccountNumber());
		if (accountexist != null) {
			accountlist.remove(accountexist); // remove the old
			accountlist.add(account); // add the new
		}
	}

	public Account loadAccount(String accountNumber) {
		for (Account account : accountlist) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}
		return null;
	}

	public Collection<Account> getAccounts() {
		return accountlist;
	}

	public void saveCustomer(Customer customer) {
		customerlist.add(customer); // add the new
	}

	public void updateCustomer(Customer customer) {
		Customer customerexist = loadCustomer(customer.getName() + customer.getZip());
		if (customerexist != null) {
			customerlist.remove(customerexist); // remove the old
			customerlist.add(customerexist); // add the new
		}
	}

	public Customer loadCustomer(String customerzip) {
		for (Customer customer : customerlist) {
			if ((customer.getName() + customer.getZip()).equals(customerzip)) {
				return customer;
			}
		}
		return null;
	}

	public Collection<Customer> getCustomers() {
		return customerlist;
	}

}
