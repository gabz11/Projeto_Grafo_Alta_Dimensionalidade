package Pajek;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Leitor {
    public String[] Ler(FileReader fr) throws IOException {
        Scanner sc = new Scanner(fr);
        String str;

        String[] strQuebrado;
        ArrayList<String> arrayBonito = new ArrayList<String>();
        boolean achouLinha = false;

        while (sc.hasNextLine()) {
            str = sc.nextLine();
            if (!str.equals("*Arcs") && !str.equals("*Edges") && achouLinha == false) {
                strQuebrado = str.split(" ", 2);
                arrayBonito.add(strQuebrado[0].strip());
                try {
                    arrayBonito.add(strQuebrado[1].strip());
                } catch (Exception e) {
                    continue;
                }
            } else if (str.equals("*Arcs") || str.equals("*Edges")) {
                arrayBonito.add(str);
                achouLinha = true;
            } else {
                strQuebrado = str.split(" ");
                arrayBonito.add(strQuebrado[0].strip());
                try {
                    arrayBonito.add(strQuebrado[1].strip());
                    arrayBonito.add(strQuebrado[2].strip());
                } catch (Exception e) {
                    continue;
                }
            }
        }

        String[] vetor = arrayBonito.toArray(new String[0]);
        sc.close();
        return vetor;
    }

}
