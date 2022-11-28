import Algoritmos.*;
import Grafo.*;
import Pajek.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Pajek paj = new Pajek();
        Leitor leitor = new Leitor();
        Exportar exportar = new Exportar();
        GeraGrafo gera_grafo = new GeraGrafo();
        String path = "DataSet/dataset.csv";
        String path1 = "GrafosSalvos/dataset.txt";

        Centralidade cent = new Centralidade();

        // INICIA CSV
        // Grafo dataset = paj.PajekDataSet(path);
        // Função Para exportar pajek
        // exportar.ExportarGrafo(dataset, "dataset");
        // importando pajek;
        // Grafo dataset_importado = paj.Pajek(path1);
        // Exibir vertices
        // System.out.println("Total de vertices:" + dataset_importado.getVertices());
        // Exibir arestas
        // dataset_importado.imprime_adjacencia();
        // // Obtem
        // dataset_importado.getTotalAdjacencias();
        // // Função para verificar se conectado
        // dataset_importado.verificaConexo();
        // // Função obter componentes
        // dataset_importado.obtemComponentes();
        // // // // Função pra verificar se euleriano
        // dataset_importado.verificaEuleriano();
        // // Função pra ver/ciclico
        // dataset_importado.verificaCiclico();
        // // Função pra centralidade de proximidade
        // cent.centralidadeDeProximidade(dataset_importado, 1, 5133);

        // GERA GRAFO ALEATORIO
        Grafo grafo_4 = gera_grafo.grafoAleatorio(4, 4, "desconexo", "ndir");
        grafo_4.verificaConexo();
        grafo_4.obtemComponentes();
        grafo_4.imprime_adjacencia();

        // Grafo grafo_1 = new Grafo(4);

        // Grafo g1 = new Grafo(3);
        // g1.setTipo("dir");
        // g1.cria_adjacencia(2, 1, 1);
        // g1.cria_adjacencia(1, 0, 1);
        // // g1.obtemComponentes();
        // // System.out.println();
        // g1.verificaEuleriano();
        // // System.out.println();
        // g1.imprime_adjacencia();
        // System.out.println();
        // System.out.println("GRAFO @2");
        // Grafo g2 = g1.obterTransposto();
        // g2.imprime_adjacencia();

        // Grafo g = new Grafo(4);
        // g.setTipo("ndir");
        // g.cria_adjacencia(0, 1, 24);
        // g.cria_adjacencia(0, 2, 3);
        // g.cria_adjacencia(0, 3, 20);
        // g.cria_adjacencia(2, 3, 12);
        // Dijkstra dick = new Dijkstra();
        // Centralidade c = new Centralidade();
        // dick.melhoresCaminhos(g, 0, 3);
        // c.centralidadeDeProximidade(g, 0, 3);

        // Grafo ex = new Grafo(5);
        // ex.setTipo("ndir");
        // ex.setTipoAresta("double");
        // ex.cria_adjacencia(0, 1, 1);
        // ex.cria_adjacencia(0, 2, 1);
        // ex.cria_adjacencia(1, 4, 1);
        // ex.cria_adjacencia(1, 3, 1);
        // ex.cria_adjacencia(2, 3, 1);
        // Centralidade c = new Centralidade(); // EXEPLO DOCK CENTRALDIE E OS CARALHo
        // c.centralidadeDeProximidade(ex, 1, 0); ////////////////// LEMBRA ESSA LINHA
        // AQUI
        // Dijkstra dijk = new Dijkstra(); //
        // https://www.sciencedirect.com/topics/computer-science/closeness-centrality
        // dijk.caminhoVertices(ex, 0, 4);

        // Grafo ex = new Grafo(8);
        // ex.setTipo("ndir");
        // ex.cria_adjacencia(0, 1, 1);
        // ex.cria_adjacencia(1, 2, 1);
        // ex.cria_adjacencia(2, 3, 1);
        // ex.cria_adjacencia(3, 4, 1);
        // ex.cria_adjacencia(3, 5, 1);
        // ex.cria_adjacencia(4, 5, 1);
        // ex.cria_adjacencia(4, 6, 1);
        // ex.cria_adjacencia(4, 7, 1);
        // ex.cria_adjacencia(5, 6, 1);
        // ex.cria_adjacencia(6, 7, 1);
        // Centralidade c = new Centralidade();
        // c.centralidadeDeProximidade(ex, 0, 7);

        // Grafo g2 = new Grafo(5);
        // g2.setTipo("ndir");
        // g2.cria_adjacencia(1, 0, 1);
        // g2.cria_adjacencia(0, 2, 1);
        // g2.cria_adjacencia(2, 1, 1);
        // g2.cria_adjacencia(0, 3, 1);
        // g2.cria_adjacencia(3, 4, 1);
        // g2.cria_adjacencia(4, 0, 1);
        // g2.verificaEuleriano();

        // Grafo g3 = new Grafo(5);
        // g3.setTipo("ndir");
        // g3.cria_adjacencia(1, 0, 1);
        // g3.cria_adjacencia(0, 2, 1);
        // g3.cria_adjacencia(2, 1, 1);
        // g3.cria_adjacencia(0, 3, 1);
        // g3.cria_adjacencia(3, 4, 1);
        // g3.cria_adjacencia(1, 3, 1);
        // g3.verificaEuleriano();

        // Grafo g4 = new Grafo(3);
        // g4.setTipo("ndir");
        // g4.cria_adjacencia(0, 1, 1);
        // g4.cria_adjacencia(1, 2, 1);
        // g4.cria_adjacencia(2, 0, 1);
        // g4.verificaEuleriano();

        // Grafo g5 = new Grafo(3);
        // g5.setTipo("ndir");
        // g5.verificaEuleriano();

        // Grafo txt = pajek.Pajek("teste.txt"); || TESTE
        // txt.imprime_adjacencia();
        // exportar.ExportarGrafo(txt, "txt");

        // Grafo grafo = new Grafo(3);
        // grafo.setTipo("dir");
        // grafo.cria_adjacencia(0, 1, 1);
        // grafo.cria_adjacencia(1, 2, 1);
        // grafo.cria_adjacencia(1, 2, 1);
        // grafo.cria_adjacencia(1, 2, 1);
        // grafo.cria_adjacencia(1, 2, 1);
        // grafo.cria_adjacencia(2, 0, 1);
        // grafo.qtdAdj(1);

        // grafo.verificaCiclico();

        // Grafo grafo_2 = pajek.Pajek("teste3.txt");
        // exportar.ExportarGrafo(grafo_2, "grafo3.txt");

        // Grafo grafo_3 = new Grafo(5);
        // grafo_3.setTipo("dir");
        // grafo_3.cria_adjacencia(0, 1, 0);
        // grafo_3.adjExiste(0, 0);

    }
}
