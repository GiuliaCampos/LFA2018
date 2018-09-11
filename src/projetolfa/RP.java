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
public class RP {
    public char titulo, term, naoterm;
    public int id;

    @Override
    public String toString() {
        return "Regra{" + "tit=" + titulo + ", term=" + term + ", naoterm=" + naoterm + "} " + id + " ";
    }

    public RP(char titulo, char term, char naoterm, int id) {
        this.term = term;
        this.naoterm = naoterm;
        this.id = id;
        this.titulo = titulo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getTitulo() {
        return titulo;
    }

    public char getTerm() {
        return term;
    }

    public char getNaoterm() {
        return naoterm;
    }
    
}
