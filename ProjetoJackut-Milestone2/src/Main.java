import br.ufal.ic.p2.jackut.sistema.Facade;

import easyaccept.EasyAccept;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String[]> argsList = new ArrayList<>();
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us1_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us1_2.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us2_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us2_2.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us3_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us3_2.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us4_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us4_2.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us5_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us5_2.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us6_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us6_2.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us7_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us7_2.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us8_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us8_2.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us9_1.txt"});
        argsList.add(new String[]{"br.ufal.ic.p2.jackut.sistema.Facade", "tests/us9_2.txt"});

        for(String[] arg: argsList){
            EasyAccept.main(arg);
        }
    }
}