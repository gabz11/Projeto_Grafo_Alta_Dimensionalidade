package Grafo;

import java.util.Comparator;

public class Arco implements Comparator<Arco> {
    private int vertice;
    private double peso;
    private String pesoString;

    public Arco(int vertice, double peso) {
        this.vertice = vertice;
        this.peso = peso;
        this.pesoString = "";
    }

    public Arco(int vertice, String pesoStr) {
        this.vertice = vertice;
        this.peso = 0.0;
        this.pesoString = pesoStr;
    }

    public Arco() {

    }

    public double getPeso() {
        return peso;
    }

    public String getPesoStr() {
        return pesoString;
    }

    @Override
    public int compare(Arco a1, Arco a2) {
        if (a1.getPeso() < a2.getPeso())
            return -1;
        if (a1.getPeso() > a2.getPeso())
            return 1;
        return 0;
    }

    public int getVertice() {
        return vertice;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
