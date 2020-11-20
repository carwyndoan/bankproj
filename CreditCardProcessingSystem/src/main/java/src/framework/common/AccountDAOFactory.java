package framework.common;

public class AccountDAOFactory {

    private AccountDAOFactory()
    {
        // private constructor
    }

    public static AccountDAO getInstance()
    {
        return AccountDAOImpl.getInstance();
    }
}
