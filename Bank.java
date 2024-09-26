package banking;   
import java.util.*;

public class Bank {
    private ArrayList<BankAccount> accounts;
    private ArrayList<String> accNumber;
    public Bank() {
        accounts = new ArrayList<>();
        accNumber = new ArrayList<>();
    }

    public boolean addAccount(BankAccount account) {
        if(!accNumber.isEmpty() && accNumber.contains(account.getAccountNumber())){
            return false;
        }
        else{
            accNumber.add(account.getAccountNumber());
            accounts.add(account);
            return true;
        }
    }

    public BankAccount getAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = getAccount(fromAccountNumber);
        BankAccount toAccount = getAccount(toAccountNumber);

        if (fromAccount != null && toAccount != null && fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }

    public String listAccounts() {
        StringBuilder list = new StringBuilder();
        for (BankAccount account : accounts) {
            list.append(account.toString()).append("\n");
        }
        return list.toString();
    }
}
