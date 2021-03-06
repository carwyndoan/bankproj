package framework.common;

import java.util.Collection;

public interface AccountDAO {
	void saveAccount(Account account);
	void updateAccount(Account account);
	Account loadAccount(String accountnumber);
	Collection<Account> getAccounts();
	void saveCustomer(Customer customer);
	void updateCustomer(Customer customer);
	Customer loadCustomer(String customerzip);
	Collection<Customer> getCustomers();
}
