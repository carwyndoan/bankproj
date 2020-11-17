package ui.framework.common;

import ui.framework.bank.ChekingInterestCalculation;
import ui.framework.bank.SavingInterestCalculation;
import ui.framework.creditcard.CreditCard;

import java.time.LocalDate;
import java.util.Collection;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO;
	
	public AccountServiceImpl(){
		accountDAO = new AccountDAOImpl();
	}

	/*
	* Create personal account, without num of employees
	 */
	@Override
	public Account createAccount(AccountType accountType, String accountNumber, String customerName, String street, String city, String state, String zip, LocalDate birthday, String email) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName, street, city, state, zip, email);
		customer.setBirthday(birthday);
		account.setCustomer(customer);
		setInterestCalculation(accountType, account);
		accountDAO.saveAccount(account);
		createObservers(account);
		return account;
	}

	/*
	* create company account, without birthday
	 */
	public Account createAccount(AccountType accountType, String accountNumber, String customerName, String street, String city, String state, String zip, int numofEmployees, String email) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName, street, city, state, zip, email);
		customer.setNumofemployees(numofEmployees);
		account.setCustomer(customer);
		setInterestCalculation(accountType, account);
		accountDAO.saveAccount(account);
		createObservers(account);
		return account;
	}

	private void setInterestCalculation(AccountType accountType, Account account){
		switch (accountType){
			case SAVING:
				account.setInterestCalculation(new SavingInterestCalculation());
				break;
			case CHECKING:
				account.setInterestCalculation(new ChekingInterestCalculation());
				break;
		}
	}

	private  void createObservers(Account acc){
		new EmailSender(acc);
	}

	public void deposit(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		
		accountDAO.updateAccount(account);
	}

	public Account getAccount(String accountNumber) {
		return accountDAO.loadAccount(accountNumber);
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
	}

	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
	}
}
