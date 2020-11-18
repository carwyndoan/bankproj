package framework.common;

import framework.bank.PersonalChekingInterestCalculation;
import framework.bank.PersonalSavingInterestCalculation;
import framework.creditcard.BronzeCreditCard;
import framework.creditcard.CreditCard;
import framework.creditcard.GoldCreditCard;
import framework.creditcard.SilverCreditCard;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

	private AccountDAO accountDAO;
	
	public AccountServiceImpl(){
		accountDAO = AccountDAOFactory.getInstance();
		generateSample(accountDAO);
	}

	private void generateSample(AccountDAO accountDAO){
		if(getAllAccounts().size() <= 0) {
			Account account = createAccount("1-111", "Duc Phuoc Doan", "4th", "Fairfield", "IA", "52557", "ddoan@miu.edu");
			account.setAccountType(AccountType.CHECKING);
			account.setInterestCalculation(new PersonalChekingInterestCalculation());
			Customer customer = account.getCustomer();

			account = createCreditCard("9-1111", "Duc Phuoc Doan", "4th", "Fairfield", "IA", "52557", "ddoan@miu.edu");
			account.setAccountType(AccountType.GOLD);
			account.setCcinterestCalculation(new GoldCreditCard());
			account.setCustomer(customer);

			account = createCreditCard("8-1111", "Duc Phuoc Doan", "4th", "Fairfield", "IA", "52557", "ddoan@miu.edu");
			account.setAccountType(AccountType.SILVER);
			account.setCcinterestCalculation(new SilverCreditCard());
			account.setCustomer(customer);

			account = createCreditCard("7-1111", "Duc Phuoc Doan", "4th", "Fairfield", "IA", "52557", "ddoan@miu.edu");
			account.setAccountType(AccountType.BRONZE);
			account.setCcinterestCalculation(new BronzeCreditCard());
			account.setCustomer(customer);

			///
			account = createAccount("2-111", "Duy Thai Nguyen", "4th", "Fairfield", "IA", "52557", "ddoan@miu.edu");
			account.setAccountType(AccountType.SAVING);
			account.setInterestCalculation(new PersonalSavingInterestCalculation());
			customer = account.getCustomer();

			account = createCreditCard("9-1112", "Duy Thai Nguyen", "4th", "Fairfield", "IA", "52557", "ddoan@miu.edu");
			account.setAccountType(AccountType.GOLD);
			account.setCcinterestCalculation(new GoldCreditCard());
			account.setCustomer(customer);

			account = createCreditCard("8-1112", "Duy Thai Nguyen", "4th", "Fairfield", "IA", "52557", "ddoan@miu.edu");
			account.setAccountType(AccountType.SILVER);
			account.setCcinterestCalculation(new SilverCreditCard());
			account.setCustomer(customer);

			account = createCreditCard("7-1112", "Duy Thai Nguyen", "4th", "Fairfield", "IA", "52557", "ddoan@miu.edu");
			account.setAccountType(AccountType.BRONZE);
			account.setCcinterestCalculation(new BronzeCreditCard());
			account.setCustomer(customer);
		}
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

	@Override
	public Account createCreditCard(String accountNumber, String customerName, String street, String city, String state, String zip, String email) {
		Account account = new CreditCard(accountNumber);
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
		getAllAccounts().stream().
				filter(account -> account.getInterestCalculation() != null)
				.collect(Collectors.toList()).forEach(Account::calculateInterest);
	}

	@Override
	public void calculateCCInterest() {
		getAllAccounts().stream().
				filter(account -> account.getCcinterestCalculation() != null)
				.collect(Collectors.toList()).forEach(Account::calculateInterest);
	}
}
