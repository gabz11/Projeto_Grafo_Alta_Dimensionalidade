package Algoritmos;

import java.util.PriorityQueue;
import Grafo.Arco;
import Grafo.Grafo;

public class Prim {
    public void algPrim(Grafo grafo) {
        int vert = grafo.getVertices();

        double key[] = new double[vert];
        int parent[] = new int[vert];
        boolean mst_conj[] = new boolean[vert];
        for (int i = 0; i < vert; i++) {
            key[i] = Double.MAX_VALUE;
            mst_conj[i] = false;
            parent[i] = -1;
        }
        PriorityQueue<Arco> pq = new PriorityQueue<Arco>(vert, new Arco());

        // pega o 1ro vertice
        key[0] = 0;
        pq.add(new Arco(0, key[0]));

        for (int i = 0; i < vert - 1; i++) {
            int u = pq.poll().getVertice();
            mst_conj[u] = true;

            for (Arco a : grafo.getArestas().get(u)) {
                if (!mst_conj[a.getVertice()] && a.getPeso() < key[a.getVertice()]) {
                    parent[a.getVertice()] = u;
                    key[a.getVertice()] = a.getPeso();
                    pq.add(new Arco(a.getVertice(), key[a.getVertice()]));
                }
            }
        }

        double total = 0;

        for (int i = 0; i < vert; i++) {
            if (key[i] != Double.MAX_VALUE)
                total += key[i];
        }

        System.out.println(total);
    }
}
