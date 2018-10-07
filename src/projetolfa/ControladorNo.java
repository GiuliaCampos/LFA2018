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
public class ControladorNo {

    private int id;
    private ControladorAresta prox;
    private boolean inicial, fim;

    public ControladorNo(int id) {
        this.id = id;
        this.inicial = false;
        this.fim = false;
        this.prox = null;
    }

    public ControladorAresta getProx() {
        return prox;
    }

    public void setProx(ControladorAresta prox) {
        this.prox = prox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInicial() {
        return inicial;
    }

    public void setInicial(boolean estadoIn) {
        this.inicial = estadoIn;
    }

    public boolean isFim() {
        return fim;
    }

    public void setFim(boolean estadoFin) {
        this.fim = estadoFin;
    }

}

