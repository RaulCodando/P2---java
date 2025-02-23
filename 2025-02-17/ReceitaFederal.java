package Atividade3;

import java.util.ArrayList;

public class ReceitaFederal {
    private ArrayList<Pessoa> lista = new ArrayList<Pessoa>();

    public void adicionarPessoa(Pessoa pessoa){
        lista.add(pessoa);
    }

    public double totalArrecadado(){
        double total = 0;

        for(int i = 0; i < lista.size(); i++){
            total += lista.get(i).calcImposto();
        }

        return total;
    }

    public Pessoa getPessoa(int i){
        return lista.get(i);
    }
}
