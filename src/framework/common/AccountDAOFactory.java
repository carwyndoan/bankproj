package framework.common;

public class AccountDAOFactory {

    private AccountDAOFactory()
    {
        // private constructor
    }

    // Inner class to provide instance of class
    private static class BillPughSingleton
    {
        private static final AccountDAO INSTANCE = new AccountDAOImpl();
    }

    public static AccountDAO getInstance()
    {
        return BillPughSingleton.INSTANCE;
    }
}
