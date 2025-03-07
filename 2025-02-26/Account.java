package Atividade4;

public class Account {
    private int number;
    private String holder;
    private double balance;
    private double withdrawLimit;

    public Account(int number, String holder, double balance, double withdrawLimit){
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount) throws InvalidWithdrawException{
        if(amount > balance){
            throw new InvalidWithdrawException("Not enough balance");
        }
        else if(amount > withdrawLimit){
            throw new InvalidWithdrawException("Withdraw value exceeds withdraw limit");
        }
        else{
            balance -= amount;
        }
    }

    public double getBalance(){
        return balance;
    }
}
