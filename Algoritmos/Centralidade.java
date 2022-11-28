package Algoritmos;

import Grafo.*;

public class Centralidade {
    public static double centralidadeDeProximidade(Grafo grafo, int origem, int intermediario) {
        double centralidade; // variável a ser calculada
        int numVertices = grafo.getVertices();
        double[] melhoresCaminhos = Dijkstra.melhoresCaminhos(grafo, origem, intermediario); // array com todoso
                                                                                             // melhores caminhos partir
                                                                                             // do vértice v
        int soma = 0; // soma dos melhores caminhos:
        for (int i = 0; i < melhoresCaminhos.length; i++) {
            if (melhoresCaminhos[i] != Double.POSITIVE_INFINITY) {
                soma += melhoresCaminhos[i];
            }
        }
        // centralidade de prox é a razão entre o total de vértices e a soma dos
        // melhores caminhos
        System.out.println("Vertices: " + (numVertices - 1));
        System.out.println("Soma:" + soma);
        centralidade = (double) (numVertices - 1) / (double) soma;
        System.out.println(centralidade);
        return centralidade;
    }

    // public static double centralidadeDeIntermediacao(Grafo grafo, int v) {
    // int qntsVezesIntermediario = 0; // variável a ser calculada
    // int numVertices = grafo.getVertices();

    // for (int vertice = 0; vertice < numVertices; vertice++) {
    // qntsVezesIntermediario = Dijkstra.caminhoVertices(grafo, vertice, v);
    // }

    // return qntsVezesIntermediario;
    // }

}
