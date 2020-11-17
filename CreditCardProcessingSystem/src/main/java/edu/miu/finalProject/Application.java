package edu.miu.finalProject;

import edu.miu.finalProject.dao.AccountEntry;
import edu.miu.finalProject.domain.Customer;
import edu.miu.finalProject.service.CreditCardService;
import edu.miu.finalProject.service.impl.CreditCardServiceImpl;

public class Application {
    public static void main(String[] args) {
        CreditCardService accountService = new CreditCardServiceImpl();

        // create 2 accounts;
        accountService.createCreditCard("4253891", "Frank Brown", "gold");
        accountService.createCreditCard("4253892", "John Doe", "silver");
        accountService.createCreditCard("4253893", "John Doe", "bronze");

        // use account 1;
        accountService.deposit("4253891", 100);
        accountService.deposit("4253892", 100);
        accountService.deposit("4253893", 100);

//        accountService.chargeAccount("4253892", 250);
        //use account 2;
//        accountService.deposit("4253893", 12450);
        accountService.transferFunds("4253892", "4253891", 100, "payment of invoice 10232");
        // show balances
        accountService.addInterest();


        for (Account account : accountService.getAllAccounts()) {
            Customer customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountNumber());
            System.out.println("Account Holder: " + customer.getName());

            System.out.println("-Date-------------------------"
                    + "-Description------------------"
                    + "-Amount-------------");



            for (AccountEntry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n",
                        entry.getDate().toString(),
                        entry.getDescription(),
                        entry.getAmount());
            }

            System.out.println("----------------------------------------" + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
        }


    }
    }

