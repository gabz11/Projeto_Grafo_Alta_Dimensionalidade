package Grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grafo {
    private int vertices;
    private No[] vertice;
    private List<List<Arco>> arestas;
    private String tipo;
    private String tipoAresta;
    private int utilGrau[]; // utilidade de grau para func euleriana.

    public Grafo(int vertices) {
        this.vertices = vertices;

        this.vertice = new No[vertices];
        for (int x = 0; x < vertice.length; x++) {
            vertice[x] = new No();
        }

        arestas = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            arestas.add(new ArrayList<>());
        }

        utilGrau = new int[vertices];
        this.tipo = "NDFND"; // grafo inicia do tipo não definido
    }

    public No[] getV_rot() {
        return vertice;
    }

    public Integer getVertices() {
        return this.vertices;
    }

    public List<List<Arco>> getArestas() {
        return this.arestas;
    }

    public void getTotalAdjacencias() {
        // Obtem total de adjacencias
        int total = 0;
        for (int i = 0; i < getArestas().size(); i++) {
            for (Arco x : getArestas().get(i)) {
                total++;
            }
        }
        System.out.printf("\nTotal de adjacências (em memoria): %s", total);
        if (tipo.equals("ndir"))
            System.out.printf("\nTotal de adjacências para grafo não direcionado: %s", total / 2);
        // Para não direcionados, considerar que arestas são a metade do que existe em
        // memória.
        else
            System.out.printf("\nTotal de adjacências para grafo direcionado: %s", total);

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoAresta() {
        return tipoAresta;
    }

    public void setTipoAresta(String tipo) {
        this.tipoAresta = tipo;
    }

    public void cria_adjacencia(int i1, int i2, double P) {
        // dir para direcionado
        // n dir para n direcionado
        if (!this.tipo.equals("NDFND")) {
            if (i1 < vertices && i2 < vertices) {
                if (!adjExiste(i1, i2)) {
                    if (tipo.equals("dir") && tipoAresta.equals("double")) {
                        System.out.printf("\nCriando adj %s --:> %s", i1, i2);
                        arestas.get(i1).add(new Arco(i2, P));
                        utilGrau[i1]++;
                    }
                    if (tipo.equals("ndir") && tipoAresta.equals("double")) {
                        arestas.get(i1).add(new Arco(i2, P));
                        arestas.get(i2).add(new Arco(i1, P));
                        utilGrau[i1]++;
                        utilGrau[i2]++;
                    }
                }
            }
        }
    }

    // OBSOLETO - APENAS PARA CRIAR COM ROTULO DE STRING
    public void cria_adjacencia(int i1, int i2, String peso) {
        // dir para direcionado
        // n dir para n direcionado
        if (!this.tipo.equals("NDFND")) {
            if (i1 < vertices && i2 < vertices) {
                if (!adjExiste(i1, i2)) {
                    if (tipo.equals("dir")) {
                        System.out.printf("\nCriando adj %s --:> %s", i1, i2);
                        arestas.get(i1).add(new Arco(i2, peso));
                        utilGrau[i1]++;
                    }
                    if (tipo.equals("ndir")) {
                        arestas.get(i1).add(new Arco(i2, peso));
                        arestas.get(i2).add(new Arco(i1, peso));
                        utilGrau[i1]++;
                        utilGrau[i2]++;
                    }
                }
            }
        }
    }

    public boolean adjExiste(int i1, int i2) {
        for (Arco x : arestas.get(i1)) {
            if (x.getVertice() == i2) {
                return true;
            }
        }

        return false;
    }

    public int qtdAdj(int vertice) {
        int qtd = 0;
        for (Arco x : getArestas().get(vertice))
            qtd++;
        System.out.printf("\nTotal de adjacências de '%s' : %s", vertice, qtd);

        return qtd;
    }

    public Grafo obterTransposto() {
        Grafo reverso = new Grafo(vertices);
        reverso.setTipo(tipo);

        for (int i = 0; i < vertices; i++) {
            for (int k = 0; k < getArestas().get(i).size(); k++) {
                reverso.cria_adjacencia(getArestas().get(i).get(k).getVertice(), i,
                        getArestas().get(i).get(k).getPeso());
            }
        }

        return reverso;
    }

    // Busca de profundidade
    public void DFS(int vertice) {
        System.out.println("|| DFS ||");

        boolean already[] = new boolean[vertices]; // vetor booleano com nos explorados
        DFSUtil(vertice, already); // chamada recursiva
    }

    public void DFSUtil(int vertice, boolean nodes[]) {
        nodes[vertice] = true; // marca adj como explorada
        System.out.print(vertice + " ");
        int a = 0;

        for (int i = 0; i < arestas.get(vertice).size(); i++) {
            a = arestas.get(vertice).get(i).getVertice();
            if (!nodes[a]) {
                DFSUtil(a, nodes);
            }
        }
    }

    public void DFSComp(int vertice, boolean nodes[]) {
        nodes[vertice] = true; // marca adj como explorada
        System.out.print(vertice + " ");
        int a = 0;

        if (arestas.get(vertice).size() == 0) {
            System.out.println();
            DFSComp(vertice + 1, nodes);
        }

        for (int i = 0; i < arestas.get(vertice).size(); i++) {
            a = arestas.get(vertice).get(i).getVertice();
            if (!nodes[a]) {
                DFSComp(a, nodes);
            }
        }
    }

    public boolean verificaCiclico() {
        boolean ciclico;
        if (getTipo().equals("dir"))
            ciclico = verificaCiclicoDir();
        else
            ciclico = verificaCiclicoNdir();
        if (ciclico) {
            System.out.println("Grafo é ciclico.");
            return ciclico;
        } else {
            System.out.println("Grafo não é ciclico.");
            return ciclico;
        }

    }

    public boolean verificaCiclicoNdir() {
        boolean visitados[] = new boolean[getVertices()];
        for (int i = 0; i < getVertices(); i++)
            visitados[i] = false;

        for (int u = 0; u < getVertices(); u++) {
            if (!visitados[u])
                if (utilCiclicoNdir(u, visitados, -1))
                    return true;
        }

        return false;

    }

    public boolean verificaCiclicoDir() {
        boolean[] visitados = new boolean[getVertices()];
        boolean[] pilhaRec = new boolean[getVertices()];

        for (int i = 0; i < getVertices(); i++) {
            if (utilCiclicoDir(i, visitados, pilhaRec)) {
                return true;
            }
        }
        return false;
    }

    public boolean utilCiclicoNdir(int vertice, boolean[] visitados, int pai) {
        visitados[vertice] = true;
        Arco i;

        Iterator<Arco> it = getArestas().get(vertice).iterator();
        while (it.hasNext()) {
            i = it.next();

            if (!visitados[i.getVertice()]) {
                if (utilCiclicoNdir(i.getVertice(), visitados, vertice))
                    return true;
            } else if (i.getVertice() != pai)
                return true;
        }
        return false;
    }

    public boolean utilCiclicoDir(int i, boolean[] visitados, boolean[] pilhaRec) {
        if (pilhaRec[i])
            return true;
        if (visitados[i])
            return false;

        visitados[i] = true;
        pilhaRec[i] = true;

        for (Arco x : getArestas().get(i)) {
            if (utilCiclicoDir(x.getVertice(), visitados, pilhaRec))
                return true;
        }
        // n encontrou ciclo em DFS
        pilhaRec[i] = false;

        return false;
    }

    public int verificaEuleriano() {
        // não direcionado ||
        // 0 n for euleriano | 1 caminho euleriano | 2 ciclo euleriano
        // direcionado | 0 não é euleriano | 1 é euleriano
        if (tipo.equals("ndir")) {
            if (!verificaConexo()) { // se grafo n for conexo n pode ser euleriano
                System.out.println("Grafo não é euleriano");
                return 0;
            }
            int vert_impar = 0; // qtd impares
            for (int i = 0; i < getVertices(); i++) {
                if (qtdAdj(i) % 2 != 0) // conta vertices c/grau impar
                    vert_impar++;
            }

            if (vert_impar > 2) {
                System.out.println("Grafo não é euleriano.");
                return 0;
            } else if (vert_impar == 2 || vert_impar == 0) {
                System.out.println("Grafo possuí um caminho euleriano.");
                return 1;
            } else {
                System.out.println("Grafo possuí um ciclo euleriano.");
                return 2;
            }
        }
        if (tipo.equals("dir")) {
            if (!fortementeConexo()) {
                System.out.println("N FORTEMENTE CONEXO");
                System.out.println("Grafo direcionado não é euleriano.");
                return 0;
            }

            for (int i = 0; i < vertices; i++) {
                if (getArestas().get(i).size() != utilGrau[i]) {
                    System.out.println("GRAU DIFERENTE");

                    System.out.println("Grafo direcionado não é euleriano.");
                    return 0;
                }
            }
            System.out.println("Grafo direcionado é euleriano.");
            return 1;
        }
        return 0;
    }

    public boolean fortementeConexo() {
        boolean visitados[] = new boolean[vertices];

        for (int i = 0; i < vertices; i++) { // inicializa
            visitados[i] = false;
        }

        DFSUtil(0, visitados);

        for (int i = 0; i < getVertices(); i++)
            if (!visitados[i])
                return false;

        Grafo reverso = obterTransposto();

        for (int i = 0; i < vertices; i++) {
            visitados[i] = false;
        }

        reverso.DFSUtil(0, visitados);

        for (int i = 0; i < vertices; i++) {
            if (!visitados[i])
                return false;
        }

        return true;
    }

    // COMPONENTES
    public void obtemComponentes() {
        boolean nodes[] = new boolean[vertices];
        System.out.println("|| Componentes ||");

        int qtd_comp = 0;
        for (int i = 0; i < vertices; i++) {
            if (!nodes[i]) {
                DFSUtil(i, nodes);
                System.out.println("");
                qtd_comp++;
            }
        }
        System.out.println("Total componentes: " + qtd_comp);
    }

    public void maiorComponente() {
        boolean nodes[] = new boolean[vertices];
        System.out.println("|| Componentes ||");

        int qtd_comp = 0;
        for (int i = 0; i < vertices; i++) {
            if (!nodes[i]) {
                DFSUtil(i, nodes);
                System.out.println("");
                qtd_comp++;
            }
        }
        System.out.println("Total componentes: " + qtd_comp);
    }

    // FIM COMPONENTES
    // CONECTIVIDADE
    public boolean verificaConexo() {
        boolean[] visitados = new boolean[vertices];
        int cont = 0;

        DFSUtil(cont, visitados);

        for (boolean a : visitados) {
            if (a)
                cont++;
        }

        if (cont == vertices) {
            System.out.println("Grafo é conexo");
            return true;
        } else {
            System.out.println("Grafo é desconexo");
            return false;
        }
    }
    // FIM CONECTIVIDADE

    public void remove_adjacencia(int i1, int i2) {
        if (i1 < vertices && i2 < vertices)
            for (int k = 0; k < vertices; k++) {
                for (int x = 0; x < arestas.get(k).size(); x++) {
                    if (arestas.get(k).get(x).getVertice() == i2)
                        arestas.get(k).remove(x);
                }
            }
        else
            System.out.println("Um dos vértices não existe.");
    }

    public void imprime_adjacencia() {
        if (tipo.equals("dir")) {
            for (int j = 0; j < vertices; j++) {
                for (int x = 0; x < arestas.get(j).size(); x++) {
                    if (tipoAresta.equals("double"))
                        System.out.printf("\n%s \"%s\"  --:> %s \"%s\" | Peso: %s", arestas.get(j).get(x).getVertice(),
                                getV_rot()[arestas.get(j).get(x).getVertice()].getRotulo(), j,
                                getV_rot()[j].getRotulo(),
                                arestas.get(j).get(x).getPeso());
                    if (tipoAresta.equals("string"))
                        System.out.printf("\n%s \"%s\"  --:> %s \"%s\" | Peso: %s", arestas.get(j).get(x).getVertice(),
                                getV_rot()[arestas.get(j).get(x).getVertice()].getRotulo(), j,
                                getV_rot()[j].getRotulo(),
                                arestas.get(j).get(x).getPesoStr());
                }
            }
        }

        if (tipo.equals("ndir")) {
            ArrayList<Integer> repetidas = new ArrayList<>();
            for (int j = 0; j < vertices; j++) {
                for (int x = 0; x < arestas.get(j).size(); x++) {
                    if (arestas.get(j).get(x).getPeso() != Double.POSITIVE_INFINITY && !repetidas.contains(j)) {
                        if (tipoAresta.equals("double"))
                            System.out.printf("\n%s \"%s\" <:--:> %s \"%s\" | Peso: %s",
                                    arestas.get(j).get(x).getVertice(),
                                    getV_rot()[arestas.get(j).get(x).getVertice()].getRotulo(), j,
                                    getV_rot()[j].getRotulo(),
                                    arestas.get(j).get(x).getPeso());
                        if (tipoAresta.equals("string"))
                            System.out.printf("\n%s <:--:> %s | Peso: %s", arestas.get(j).get(x).getVertice(),
                                    getV_rot()[arestas.get(j).get(x).getVertice()].getRotulo(), j,
                                    getV_rot()[j].getRotulo(),
                                    arestas.get(j).get(x).getPesoStr());
                        repetidas.add(j);
                    }
                }
            }
            System.out.println();
        }

    }

    public void seta_informacao(int indice, String novo_rotulo) {
        if (indice < vertices) {
            for (int i = 0; i < vertices; i++) {
                if (i == indice)
                    vertice[i].setRotulo(novo_rotulo);
            }
        } else
            System.out.println("ERRO: Um dos vértices não existe.");
    }
}