package Pajek;

import Grafo.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pajek {
    public Grafo Pajek(String path) throws IOException {
        Grafo grafo = null;
        Leitor leitor = new Leitor();
        String[] dados_pajek;
        FileReader fr = new FileReader(path);
        dados_pajek = leitor.Ler(fr);

        boolean criar_grafo = false;
        boolean criar_vertices = false;
        boolean criar_adj = false;
        boolean trava = false;
        int op_dir = 0; // 0 nao direcionados | 1 direcionados
        int vert_parse = 0;
        int i1 = -1, i2 = -1;
        double p = Double.POSITIVE_INFINITY;
        String rot = null;
        int cont_vert = 0;

        for (String s : dados_pajek) {
            if (s.equals("*Vertices"))
                criar_grafo = true;
            if (s.equals("*Edges")) {
                if (cont_vert == grafo.getVertices()) {
                    criar_vertices = false;
                    criar_grafo = false;
                    criar_adj = true;
                    trava = true;
                    op_dir = 0;
                    grafo.setTipo("ndir");
                    grafo.setTipoAresta("double");
                } else {
                    System.out.println("ERRO:Formatação");
                    return grafo;
                }
            }
            if (s.equals("*Arcs")) {
                if (cont_vert == grafo.getVertices()) {
                    criar_vertices = false;
                    criar_grafo = false;
                    criar_adj = true;
                    trava = true;
                    op_dir = 1;
                    grafo.setTipo("dir");
                    grafo.setTipoAresta("double");
                } else {
                    System.out.println("ERRO: Formatação");
                    return grafo;
                }
            }
            if (criar_grafo && !s.equals("*Vertices")) {
                vert_parse = Integer.parseInt(s);
                System.out.printf("Total de vertices: %s", vert_parse);
                System.out.println();

                grafo = new Grafo(vert_parse);

                criar_grafo = false;
                criar_vertices = true;
                trava = true;
            }
            if (criar_vertices && !trava) {
                if (s.startsWith("\"") && s.endsWith("\"")) {
                    if (s.length() != 2)
                        rot = s.substring(1, s.length() - 1);
                    else
                        rot = "";
                } else
                    i1 = Integer.parseInt(s);

            }
            if (criar_adj && !trava) {
                if (i1 == -1 && i2 == -1)
                    i1 = Integer.parseInt(s);
                else if (i1 != -1 && i2 == -1)
                    i2 = Integer.parseInt(s);
                else if (i1 != -1)
                    p = Double.parseDouble(s);
            }
            if (i1 != -1 && i2 != -1 && p != Double.POSITIVE_INFINITY) {
                grafo.cria_adjacencia(i1, i2, p);
                i1 = -1;
                i2 = -1;
                p = Double.POSITIVE_INFINITY;
            }
            if (i1 != -1 && rot != null && grafo != null) {
                grafo.seta_informacao(i1, rot);
                cont_vert++;
                i1 = -1;
                rot = null;
            }
            trava = false;
        }

        return grafo;
    }

    public Grafo PajekDataSet(String path) throws IOException {
        Grafo g = null;
        FileReader fr = new FileReader(path);
        String[] csv = {};
        String linha = "";
        ArrayList<String> vertices = new ArrayList<>();
        ArrayList<String> artistas = new ArrayList<>();
        ArrayList<String> album = new ArrayList<>();
        ArrayList<Double> danceability = new ArrayList<>();
        ArrayList<Double> energy = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(fr);
            br.readLine(); // Tira a primeira linha
            while ((linha = br.readLine()) != null) {
                csv = linha.split(",");
                int popularidade = Integer.parseInt(csv[5]);
                if (popularidade >= 70) { // Popularidade para limitar vértices
                    artistas.add(csv[2]);
                    album.add(csv[3]);
                    vertices.add(csv[4]);
                    danceability.add(Double.parseDouble(csv[8])); // "Danceability" serve como metrica
                    energy.add(Double.parseDouble(csv[9])); // "Energy" da track serve como metrica
                }
            }

            g = new Grafo(vertices.size());
            g.setTipo("ndir"); // Grafo é não direcionado
            g.setTipoAresta("double"); // Grafo usa peso double
            for (int i = 0; i < g.getVertices(); i++) {
                g.seta_informacao(i, vertices.get(i)); // Inicia os rótulos
                g.getV_rot()[i].setArtista(artistas.get(i)); // Guarda o nome do artista
                g.getV_rot()[i].setAlbum(album.get(i)); // Guarda o nome do album
                g.getV_rot()[i].setDanceability(danceability.get(i)); // Coeficente de danceability
                g.getV_rot()[i].setEnergy(energy.get(i));
            }
            System.out.println("Construindo dataset.");
            DecimalFormat df = new DecimalFormat("0.0000");

            for (int i = 0; i < g.getVertices(); i++) {
                for (int k = 0; k < g.getVertices(); k++) {
                    if (i != k && (g.getV_rot()[i].getDanceability() +
                            g.getV_rot()[k].getDanceability() +
                            g.getV_rot()[i].getEnergy() +
                            g.getV_rot()[k].getEnergy()) / 4 > 0.6) {
                        double peso = (g.getV_rot()[i].getDanceability() + g.getV_rot()[k].getDanceability()
                                + g.getV_rot()[i].getEnergy() + g.getV_rot()[k].getEnergy()) / 4;
                        g.cria_adjacencia(i, k, Double.parseDouble(df.format(peso)));
                    }
                }
            }
            System.out.println("Dataset construido.");
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            return g;
        }
    }
}
