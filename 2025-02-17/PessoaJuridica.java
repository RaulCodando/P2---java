package Atividade3;

public class PessoaJuridica extends Pessoa{
    private int numeroDeFuncionarios;

    public PessoaJuridica(String nome, double rendaAnual, int numeroDeFuncionarios){
        super(nome, rendaAnual);
        this.numeroDeFuncionarios = numeroDeFuncionarios;
    }

    @Override
    public double calcImposto() {
        double imposto = 0;

        if(numeroDeFuncionarios > 10){
            imposto = rendaAnual * 0.14;
        }
        else{
            imposto = rendaAnual * 0.16;
        }

        return imposto;
    }
}
