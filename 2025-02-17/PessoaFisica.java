package Atividade3;

public class PessoaFisica extends Pessoa{
    private double gastosComSaude;

    public PessoaFisica(String nome, double rendaAnual, double gastosComSaude){
        super(nome, rendaAnual);
        this.gastosComSaude = gastosComSaude;
    }

    @Override
    public double calcImposto() {
        double imposto = 0;

        if(rendaAnual < 20000){
            imposto = rendaAnual * 0.15;
        }
        else{
            imposto = rendaAnual * 0.25;
        }

        imposto -= 0.5 * gastosComSaude;

        return imposto;
    }
}
