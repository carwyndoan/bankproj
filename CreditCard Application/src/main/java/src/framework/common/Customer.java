package framework.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Customer {
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	private LocalDate birthday;
	private int numofemployees;
	private String email;
	List<Account> accountList;

	public Customer(String name, String street, String city, String state, String zip, String email) {
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		accountList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumofemployees() {
		return numofemployees;
	}

	public void setNumofemployees(int numofemployees) {
		this.numofemployees = numofemployees;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public Account getAccount(String accountNumber) {
		return getAccountList().stream().filter(acc -> acc.getAccountNumber().equals(accountNumber)).findFirst().orElse(null);
	}
}
