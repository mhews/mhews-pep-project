package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    public static Account registerUser(Account account){
        if (account.getUsername() == null){
            return null;
        }
        if (account.getPassword().length()<4 || account.getUsername().length()==0){
            return null;
        }
        if (AccountDAO.lookupUsername(account.getUsername()) != null){
            return null;
        }
        return (AccountDAO.insertAccount(account));
    }
    public static Account loginUser(Account account){
        return (AccountDAO.searchAccount(account));
    }
}
