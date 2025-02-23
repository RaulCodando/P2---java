package Atividade3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReceitaFederal receitaFederal = new ReceitaFederal();

        System.out.println("Enter the number of tax payers");

        int n = scanner.nextInt();
        scanner.nextLine();

        for(int i = 1; i <= n; i++){
            System.out.printf("Tax payer #%d data:\n", i);
            System.out.println("Individual or company? (i/c) ");
            String pessoaTipo = scanner.nextLine();
            System.out.println("Name: ");
            String nome = scanner.nextLine();
            System.out.println("Anual income: ");
            double rendaAnual = scanner.nextDouble();
            scanner.nextLine();

            if(pessoaTipo.contains("i")){
                System.out.println("Health expenditures: ");
                double gastosComSaude = scanner.nextDouble();
                scanner.nextLine();
                receitaFederal.adicionarPessoa(new PessoaFisica(nome, rendaAnual, gastosComSaude));
            }
            else{
                System.out.println("Number of employees: ");
                int numeroDeFuncionarios = scanner.nextInt();
                scanner.nextLine();
                receitaFederal.adicionarPessoa(new PessoaJuridica(nome, rendaAnual, numeroDeFuncionarios));
            }
        }

        System.out.println("\nTAXES PAID:");

        for(int i = 0; i < n; i++){
            System.out.printf("%s: $ %f\n", receitaFederal.getPessoa(i).nome, receitaFederal.getPessoa(i).calcImposto());
        }

        System.out.printf("TOTAL IN TAXES: %f", receitaFederal.totalArrecadado());
    }
}
