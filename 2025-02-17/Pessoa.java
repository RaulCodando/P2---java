package Atividade3;

public abstract class Pessoa {
    protected String nome;
    protected double rendaAnual;

    public String getNome(){
        return nome;
    }

    public Pessoa(String nome, double rendaAnual){
        this.nome = nome;
        this.rendaAnual= rendaAnual;
    }

    public abstract double calcImposto();
}
