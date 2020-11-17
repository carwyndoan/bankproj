package framework.common;

//import framework.bank.ChekingInterestCalculation;
//import framework.bank.SavingInterestCalculation;
import java.util.Collection;

public class AccountServiceImpl implements AccountService {

	private AccountDAO accountDAO;
	
	public AccountServiceImpl(){
		accountDAO = AccountDAOFactory.getInstance();
	}

	@Override
	public Account createAccount(String accountNumber, String customerName, String street, String city, String state, String zip, String email) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName, street, city, state, zip, email);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		createObservers(account);
		return account;
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

	@Override
	public void calculateInterest() {
		getAllAccounts().forEach(Account::calculateInterest);
	}
}
