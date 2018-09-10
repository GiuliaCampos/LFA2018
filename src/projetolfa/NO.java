/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolfa;

import java.util.ArrayList;

/**
 *
 * @author Giulia
 */
public class NO {
    private String letra;
    private NO prox;
    private ArrayList<String> filho = new ArrayList();
    
    public NO(){
        
    }
    
    public NO(String letra, NO proximo){
        this.letra = letra;
        this.prox = proximo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public NO getProx() {
        return prox;
    }

    public void setProx(NO prox) {
        this.prox = prox;
    }

    public ArrayList<String> getFilho() {
        return filho;
    }

    public void setFilho(ArrayList<String> filho) {
        this.filho = filho;
    }
    
    public void adcFilho(String letra){
        filho.add(letra);
    }
    
}
