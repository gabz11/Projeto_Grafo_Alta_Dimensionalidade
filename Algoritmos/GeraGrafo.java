package Algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Grafo.*;

public class GeraGrafo {
    // Gerador de grafo aleatório
    public Grafo grafoAleatorio(int vertices, int adj, String opCon, String tipoGrafo) {
        Random random = new Random();
        Grafo grafo_rng = new Grafo(vertices);
        grafo_rng.setTipoAresta("double");
        grafo_rng.setTipo(tipoGrafo); // se grafo é direcionado ou n direcionado
        if (tipoGrafo.equals("dir") && opCon.equals("conexo") && adj > vertices * vertices) {
            System.out.println("ERRO: o limite de arestas foi ultrapassado");
            return grafo_rng;
        }
        if (tipoGrafo.equals("dir") && opCon.equals("desconexo") && adj > (vertices * vertices) - (vertices - 1)) {
            System.out.println("ERRO: o limite de arestas foi ultrapassado para desconexo");
            return grafo_rng;
        }
        if (tipoGrafo.equals("ndir") && opCon.equals("conexo") && adj > (vertices * (vertices - 1)) / 2) {
            System.out.println("ERRO: o limite de arestas foi ultrapassado");
            return grafo_rng;
        }
        if (tipoGrafo.equals("ndir") && opCon.equals("desconexo")
                && adj <= ((vertices * (vertices - 1)) / 2) - (vertices - 1)) {
            System.out.println("ERRO: o limite de arestas foi ultrapassado");
            return grafo_rng;
        }
        if (tipoGrafo.equals("dir") && adj <= vertices * vertices) {
            int total_adj = adj; // desconta dos conexos as adjacencias
            // garante que o grafo é conexo
            if (opCon.equals("conexo")) {
                /*
                 * para garantir q o grafo seja conexo temos q criar um adj entre dos os
                 * vértices q seja unicos
                 */
                if (adj < grafo_rng.getVertices() - 1) {
                    System.out.println("ERRO: Não há arestas o suficiente para o grafo ser complexo");
                    return grafo_rng;
                }
                // vetor com todos os vertices unicos não embaralhado
                ArrayList<Integer> rng_con = new ArrayList<>();
                for (int i = 0; i < grafo_rng.getVertices(); i++) {
                    rng_con.add(i);
                }
                // embaralha os valores unicos para criar adjacencias aleatorias
                Collections.shuffle(rng_con);
                System.out.println("|II|");
                for (int i : rng_con) {
                    System.out.printf("%s, ", i);
                }

                int i2 = -1;
                // cria adjacencia de forma ciclica
                System.out.println("Tam:" + rng_con.size());
                for (int i = 0; i < rng_con.size(); i++) {
                    i2 = i + 1;
                    if (i2 >= rng_con.size())
                        i2 = 0;
                    grafo_rng.cria_adjacencia(rng_con.get(i), rng_con.get(i2), (double) random.nextInt(100));
                    total_adj--;
                }
            }
            // termina conexo
            int i1 = -1;
            int i2 = -1;
            Boolean existe_adj = false;
            Random rng = new Random();
            while (total_adj > 0) {
                i1 = rng.nextInt(vertices);
                i2 = rng.nextInt(vertices);
                existe_adj = grafo_rng.adjExiste(i1, i2);
                if (!existe_adj) {
                    grafo_rng.cria_adjacencia(i1, i2, (double) random.nextInt(100));
                    if (opCon.equals("desconexo") && grafo_rng.verificaConexo()) {
                        grafo_rng.remove_adjacencia(i1, i2);
                        System.out.println("deixando desconexo");
                    } else
                        total_adj--;
                }
            }
        }

        if (tipoGrafo.equals("ndir") && adj <= (vertices * (vertices - 1)) / 2) {
            int total_adj = adj;

            if (opCon.equals("conexo")) {
                /*
                 * para garantir q o grafo seja conexo temos q criar um adj entre dos os
                 * vértices q seja unicos
                 */
                if (adj < grafo_rng.getVertices() - 1) {
                    System.out.println("ERRO: Não há arestas o suficiente para o grafo ser complexo");
                    return grafo_rng;
                }
                ArrayList<Integer> rng_con = new ArrayList<>();
                for (int i = 0; i < grafo_rng.getVertices(); i++) {
                    rng_con.add(i);
                }
                // embaralha os valores unicos para criar adjacencias aleatorias
                Collections.shuffle(rng_con);
                System.out.println("|II|");
                for (int i : rng_con) {
                    System.out.printf("%s, ", i);
                }

                int i2 = -1;
                // cria adjacencia de forma ciclica
                System.out.println("Tam:" + rng_con.size());
                for (int i = 0; i < rng_con.size(); i++) {
                    i2 = i + 1;
                    if (i2 >= rng_con.size())
                        i2 = 0;
                    grafo_rng.cria_adjacencia(rng_con.get(i), rng_con.get(i2), (double) random.nextInt(100));
                    total_adj--;
                }
            }
            int i1 = -1;
            int i2 = -1;
            Boolean existe_adj = false;
            Random rng = new Random();
            while (total_adj > 0) {
                i1 = rng.nextInt(vertices);
                i2 = rng.nextInt(vertices);
                existe_adj = grafo_rng.adjExiste(i1, i2);
                if (!existe_adj) {
                    grafo_rng.cria_adjacencia(i1, i2, (double) random.nextInt(100));
                    if (opCon.equals("desconexo") && grafo_rng.verificaConexo()) {
                        grafo_rng.remove_adjacencia(i1, i2);
                        System.out.println("deixando desconexo");
                    } else
                        total_adj--;
                }
            }
        }

        return grafo_rng;
    }
}
