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
public class Regra {
    private boolean terminal;
    private char simboloTerminal;
    private char simboloNTerminal;
    private Regra prox;

    public Regra(char simboloTerminal, char simboloNTerminal) {
        this.terminal = false;
        this.simboloTerminal = simboloTerminal;
        this.simboloNTerminal = simboloNTerminal;
        this.prox = null;
    }

    public Regra(char simboloTerminal) {
        this.terminal = true;
        this.simboloTerminal = simboloTerminal;
        this.prox = null;
        this.simboloNTerminal = '-';
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public char getSimboloTerminal() {
        return simboloTerminal;
    }

    public void setSimboloTerminal(char simboloTerminal) {
        this.simboloTerminal = simboloTerminal;
    }

    public char getSimboloNTerminal() {
        return simboloNTerminal;
    }

    public void setSimboloNTerminal(char simboloNTerminal) {
        this.simboloNTerminal = simboloNTerminal;
    }

    public void setProx(Regra prox) {
        this.prox = prox;
    }

    public Regra getProx() {
        return this.prox;
    }
}
