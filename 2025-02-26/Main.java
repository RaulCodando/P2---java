package Atividade4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        String holder;
        double balance;
        double withdrawLimit;

        System.out.println("Digite os dados da conta:");
        System.out.print("Número: ");
        number = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Proprietário: ");
        holder = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        balance = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Limite de saque: ");
        withdrawLimit = scanner.nextDouble();
        scanner.nextLine();

        Account account = new Account(number, holder, balance, withdrawLimit);

        System.out.print("\nDigite o valor do saque: ");

        double withDraw = scanner.nextDouble();

        try {
            account.withdraw(withDraw);
            System.out.print("New balance: ");
            System.out.println(account.getBalance());
        }
        catch (InvalidWithdrawException e){
            System.out.print("Withdraw error: ");
            System.out.println(e.getMessage());
        }
    }
}
