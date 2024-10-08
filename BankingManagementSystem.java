import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import banking.Bank;
import banking.BankAccount;


public class BankingManagementSystem extends JFrame {
    private Bank bank;
    private JTextArea outputArea;
    private JTextField accountNumberField;
    private JTextField holderField;
    private JTextField depositField;
    private JTextField withdrawField;
    private JTextField transferFromField;
    private JTextField transferToField;
    private JTextField transferAmountField;

    public BankingManagementSystem() {
        bank = new Bank();

        setTitle("Banking Management System");
        setSize(780, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        accountNumberField = new JTextField(15);
        holderField = new JTextField(15);
        depositField = new JTextField(15);
        withdrawField = new JTextField(15);
        transferFromField = new JTextField(15);
        transferToField = new JTextField(20);
        transferAmountField = new JTextField(15);
        outputArea = new JTextArea(10, 60);
        outputArea.setEditable(false);

        JButton createAccountButton = new JButton("Create Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer Funds");
        JButton listAccountsButton = new JButton("List Accounts");

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                String holder = holderField.getText();
                double initialBalance = 0;

                if(accountNumber.length() == 0){
                    outputArea.setText("Enter account number to create Account");
                }
                else if(holder.length() == 0){
                    outputArea.setText("Enter holder name to create Account");
                }
                else if(depositField.getText().isEmpty()){
                    outputArea.setText("Deposit money to create Account");
                }
                else{
                    initialBalance = Double.parseDouble(depositField.getText());
                }

                if(initialBalance != 0){
                    if ( bank.addAccount(new BankAccount(accountNumber, holder, initialBalance))) {
                        outputArea.setText("Account created for " + holder + " with balance: " + initialBalance);
                    }
                    else{
                        outputArea.setText("Account Already Exist for " + accountNumber);
                    }
                }   
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(depositField.getText());
                BankAccount account = bank.getAccount(accountNumber);
                if (account != null) {
                    account.deposit(amount);
                    outputArea.setText("Deposited: " + amount + " to account " + accountNumber);
                } else {
                    outputArea.setText("Account not found!");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(withdrawField.getText());
                BankAccount account = bank.getAccount(accountNumber);
                if (account != null && account.withdraw(amount)) {
                    outputArea.setText("Withdrew: " + amount + " from account " + accountNumber);
                } else {
                    outputArea.setText("Withdrawal failed! Check account balance or number.");
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromAccount = transferFromField.getText();
                String toAccount = transferToField.getText();
                double amount = Double.parseDouble(transferAmountField.getText());
                if (bank.transferFunds(fromAccount, toAccount, amount)) {
                    outputArea.setText("Transferred: " + amount + " from " + fromAccount + " to " + toAccount);
                } else {
                    outputArea.setText("Transfer failed! Check account numbers or balance.");
                }
            }
        });

        listAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(bank.listAccounts());
            }
        });

        add(new JLabel("Account Number:"));
        add(accountNumberField);
        add(new JLabel("Holder Name:"));
        add(holderField);
        add(new JLabel("Initial Deposit:"));
        add(depositField);
        add(createAccountButton);
        add(new JLabel("Deposit Amount:"));
        add(depositField);
        add(depositButton);
        add(new JLabel("Withdraw Amount:"));
        add(withdrawField);
        add(withdrawButton);
        add(new JLabel("Transfer From Account:"));
        add(transferFromField);
        add(new JLabel("Transfer To Account:"));
        add(transferToField);
        add(new JLabel("Transfer Amount:"));
        add(transferAmountField);
        add(transferButton);
        add(listAccountsButton);
        add(new JScrollPane(outputArea));
        add(new JLabel("Note: To create account ->"));
        add(new JLabel("1: Enter account Number |"));
        add(new JLabel("2: Enter holder name |"));
        add(new JLabel("3: Enter deposit amount"));
        setVisible(true);
    }

    public static void main(String[] args) {
        new BankingManagementSystem();
    }
}
