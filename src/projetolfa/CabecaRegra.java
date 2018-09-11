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
public class CabecaRegra {
    private Character simbolo;
    private Regra prox;

    public CabecaRegra(Character simbolo){
        this.simbolo = simbolo;
    }
    public Character getSimbolo() {
        return simbolo;
    }
    public void setSimbolo(Character simbolo) {
        this.simbolo = simbolo;
    }
    public Regra getProx() {
        return prox;
    }
    public void setProx(Regra prox) {
        this.prox = prox;
    }
}
