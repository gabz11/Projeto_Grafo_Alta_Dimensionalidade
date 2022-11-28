package Algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Grafo.*;

public class Dijkstra {
    public static double algoritmo_Dijkstra(Grafo g, int x, int y) {
        if (x < 0 || g.getArestas().size() < y) {
            System.out.println("Não é possivel fazer o Dijkstra");

            return Double.POSITIVE_INFINITY;
        } else {
            // Instânciando as listas de caminho, distancia, pai e aberto.
            List<Integer> caminhoList = new ArrayList<>();

            double[] distancia = new double[g.getVertices()];
            int[] pai = new int[g.getVertices()];
            boolean[] aberto = new boolean[g.getVertices()];

            // Setando as listas a serem analisadas.
            for (int i = 0; i < g.getVertices(); i++) {
                if (i == x) {
                    distancia[i] = 0;
                    pai[i] = -1;
                    aberto[i] = true;
                } else {
                    distancia[i] = Double.POSITIVE_INFINITY;
                    pai[i] = -1;
                    aberto[i] = true;
                }
            }

            while (true) {
                double menorDistancia = Double.POSITIVE_INFINITY;
                int menorIndice = -1;
                for (int i = 0; i < g.getVertices(); i++) {
                    if (aberto[i] && distancia[i] < menorDistancia) {
                        menorDistancia = distancia[i];
                        menorIndice = i;
                    }
                }

                // Se não for achado ele sai do while
                if (menorIndice == -1) {
                    break;
                }

                aberto[menorIndice] = false;
                List<Arco> adj = g.getArestas().get(menorIndice);

                // Percorre a as listaAdjacencias do menor indice.
                // Arco p = adj.get(0);
                for (Arco a : adj) {
                    if (distancia[menorIndice] + a.getPeso() < distancia[a.getVertice()]) {
                        distancia[a.getVertice()] = distancia[menorIndice] + a.getPeso();
                        pai[a.getVertice()] = menorIndice;
                    }
                }
            }

            int p = y;
            while (p != -1) {
                caminhoList.add(p);
                p = pai[p];
            }

            Collections.sort(caminhoList);
            System.out.println("A menor distancia entre " + x + " e " + y + " é: " + distancia[y]);
            System.out.println("Caminho entre  " + x + " e " + y + " é: " + caminhoList);

            return distancia[y];
        }
    }

    public static double[] melhoresCaminhos(Grafo g, int x, int y) {
        double[] melhores_caminhos = new double[g.getVertices()];

        if (x < 0 || g.getArestas().size() < y) {
            System.out.println("Não é possivel fazer o Dijkstra");

            return melhores_caminhos;
        } else {
            // Instânciando as listas de caminho, distancia, pai e aberto.
            List<Integer> caminhoList = new ArrayList<>();

            double[] distancia = new double[g.getVertices()];
            int[] pai = new int[g.getVertices()];
            boolean[] aberto = new boolean[g.getVertices()];

            // Setando as listas a serem analisadas.
            for (int i = 0; i < g.getVertices(); i++) {
                if (i == x) {
                    distancia[i] = 0;
                    pai[i] = -1;
                    aberto[i] = true;
                } else {
                    distancia[i] = Double.POSITIVE_INFINITY;
                    pai[i] = -1;
                    aberto[i] = true;
                }
            }

            while (true) {
                double menorDistancia = Double.POSITIVE_INFINITY;
                int menorIndice = -1;
                for (int i = 0; i < g.getVertices(); i++) {
                    if (aberto[i] && distancia[i] < menorDistancia) {
                        menorDistancia = distancia[i];
                        menorIndice = i;
                    }
                }

                // Se não for achado ele sai do while
                if (menorIndice == -1) {
                    break;
                }

                aberto[menorIndice] = false;
                List<Arco> adj = g.getArestas().get(menorIndice);

                // Percorre a as listaAdjacencias do menor indice.
                // Arco p = adj.get(0);
                for (Arco a : adj) {
                    if (distancia[menorIndice] + a.getPeso() < distancia[a.getVertice()]) {
                        distancia[a.getVertice()] = distancia[menorIndice] + a.getPeso();
                        pai[a.getVertice()] = menorIndice;
                    }
                }
            }

            int p = y;
            while (p != -1) {
                caminhoList.add(p);
                p = pai[p];
            }

            Collections.sort(caminhoList);
            System.out.println("A menor distancia entre " + x + " e " + y + " é: " + distancia[y]);
            System.out.println("Caminho entre  " + x + " e " + y + " é: " + caminhoList);
            for (int i = 0; i < distancia.length; i++) {
                System.out.println(distancia[i]);

            }
            return distancia;
        }
    }

    public static List<Integer> caminhoVertices(Grafo g, int x, int y) {
        List<Integer> melhores_caminhos = new ArrayList<>();

        if (x < 0 || g.getArestas().size() < y) {
            System.out.println("Não é possivel fazer o Dijkstra");

            return melhores_caminhos;
        } else {
            // Instânciando as listas de caminho, distancia, pai e aberto.
            List<Integer> caminhoList = new ArrayList<>();

            double[] distancia = new double[g.getVertices()];
            int[] pai = new int[g.getVertices()];
            boolean[] aberto = new boolean[g.getVertices()];

            // Setando as listas a serem analisadas.
            for (int i = 0; i < g.getVertices(); i++) {
                if (i == x) {
                    distancia[i] = 0;
                    pai[i] = -1;
                    aberto[i] = true;
                } else {
                    distancia[i] = Double.POSITIVE_INFINITY;
                    pai[i] = -1;
                    aberto[i] = true;
                }
            }

            while (true) {
                double menorDistancia = Double.POSITIVE_INFINITY;
                int menorIndice = -1;
                for (int i = 0; i < g.getVertices(); i++) {
                    if (aberto[i] && distancia[i] < menorDistancia) {
                        menorDistancia = distancia[i];
                        menorIndice = i;
                    }
                }

                // Se não for achado ele sai do while
                if (menorIndice == -1) {
                    break;
                }

                aberto[menorIndice] = false;
                List<Arco> adj = g.getArestas().get(menorIndice);

                // Percorre a as listaAdjacencias do menor indice.
                // Arco p = adj.get(0);
                for (Arco a : adj) {
                    if (distancia[menorIndice] + a.getPeso() < distancia[a.getVertice()]) {
                        distancia[a.getVertice()] = distancia[menorIndice] + a.getPeso();
                        pai[a.getVertice()] = menorIndice;
                    }
                }
            }

            int p = y;
            while (p != -1) {
                caminhoList.add(p);
                p = pai[p];
            }

            Collections.sort(caminhoList);
            System.out.println("A menor distancia entre " + x + " e " + y + " é: " + distancia[y]);
            System.out.println("Caminho entre  " + x + " e " + y + " é: " + caminhoList);
            for (int i = 0; i < distancia.length; i++) {
                System.out.println(distancia[i]);

            }
            for (int a : caminhoList) {
                System.out.println("[");
                System.out.printf("%s,", a);
            }

            return caminhoList;
        }
    }

}
