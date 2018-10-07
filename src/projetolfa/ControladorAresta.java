/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolfa;

/**
 *
 * @author Giulia
 */
public class ControladorAresta {

    private String estado;
    private ControladorAresta prox;
    private int ini;
    private int fin;

    public ControladorAresta(int ini, int fin, String estado, ControladorAresta prox) {
        this.ini = ini;
        this.prox = prox;
        this.fin = fin;
        this.estado = estado;
    }

    public int getIni() {
        return ini;
    }

    public int getFin() {
        return fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setIni(int ini) {
        this.ini = ini;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ControladorAresta getProx() {
        return prox;
    }

    public void setProx(ControladorAresta prox) {
        this.prox = prox;
    }
}
