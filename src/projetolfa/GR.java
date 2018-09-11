/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolfa;

import java.io.IOException;

/**
 *
 * @author Giulia
 */
public class GR {
    RP[] reg;

    public GR(RP[] reg) {
        this.reg = reg;
    }

    public RP[] getReg() {
        return reg;
    }

    public void setReg(RP[] reg) {
        this.reg = reg;
    }

    public int getRegraID(char c) {
        for (int i = 0; reg[i] != null; i++) {
            if (reg[i].getTitulo() == c) {
                return reg[i].getId();
            } else if (c == '0') {
                return reg.length;
            }
        }
        return -1;
    }

    public boolean Verif(String teste, char ini, int ind) throws IOException {

        char title = ini;
        int i = 0, j = 0;
        String conf = "";
        while (i < teste.length() && j < ind) {
            if (reg[j].getTitulo() == title && reg[j].getTerm() == "\u03BB".charAt(0)) { //reconhece vazio
                title = reg[j].getNaoterm();
                j = -1;
            } else if ((reg[j].getTitulo() == title && reg[j].getTerm() == teste.charAt(i))) {
                title = reg[j].getNaoterm();

                for (int c = 0; c < ind; c++) {
                    for (int d = 0; d < ind; d++) {
                        if (reg[j].getTitulo() == reg[c].getTitulo() && reg[d].getTerm() == teste.charAt(i + 1) && reg[d].getTitulo() == reg[c].getNaoterm()) {
                            title = reg[c].getNaoterm();
                            break;
                        }
                    }
                }
                conf += "" + reg[j].getTerm();
                i++;
                j = -1;
            }
            j++;
        }
        return (teste.equals(conf)) || (teste.equals(conf.concat("\u03BB")));
    }
    
}
