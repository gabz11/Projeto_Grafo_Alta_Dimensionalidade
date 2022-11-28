package Pajek;

import Grafo.Grafo;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Exportar {
    public void ExportarGrafo(Grafo grafo, String nome_arquivo) throws IOException {
        String path = "GrafosSalvos/" + nome_arquivo + ".txt";
        try {
            File arquivo = new File(path);

            if (!arquivo.exists())
                arquivo.createNewFile();

            PrintWriter pw = new PrintWriter(arquivo);
            pw.println("*Vertices " + grafo.getVertices());
            for (int i = 0; i < grafo.getV_rot().length; i++) {
                pw.println(i + " \"" + grafo.getV_rot()[i].getRotulo() + "\"");
            }
            if (grafo.getTipo().equals("ndir"))
                pw.println("*Edges");
            if (grafo.getTipo().equals("dir"))
                pw.println("*Arcs");

            List<String> teste = new ArrayList<String>();
            for (int i = 0; i < grafo.getArestas().size(); i++) {
                for (int k = 0; k < grafo.getArestas().get(i).size(); k++) {
                    if (grafo.getTipo().equals("ndir")) {
                        String indice = String.valueOf(i);
                        String verticeDestino = String.valueOf(grafo.getArestas().get(i).get(k).getVertice());
                        String stringFinal = indice + verticeDestino;
                        String stringFinalInvertida = verticeDestino + indice;
                        teste.add(stringFinal);

                        if (!teste.contains(stringFinalInvertida)) {
                            pw.println(i + " " + grafo.getArestas().get(i).get(k).getVertice() + " "
                                    + grafo.getArestas().get(i).get(k).getPeso());
                        }

                    } else
                        pw.println(i + " " + grafo.getArestas().get(i).get(k).getVertice() + " "
                                + grafo.getArestas().get(i).get(k).getPeso());
                }
            }
            pw.close();
        } catch (Exception e) {
            // System.out.println("ERRO: Arquivo já existe.");
        }

    }
}
