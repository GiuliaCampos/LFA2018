/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolfa;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Giulia
 */
public class Gramatica {
    
    ArrayList<CabecaRegra> regras = new ArrayList<>();

    private boolean procuraCabeca(Character c) {
        for (int i = 0; i < regras.size(); i++) {
            if (regras.get(i).getSimbolo().equals(c)) {
                return true;
            }
        }
        return false;
    }

    public void montaRegras(Character LHS, Character TRHS, Character NTRHS) {
        Regra aux = null;
        Regra novaRegra = new Regra(TRHS, NTRHS);

        if (!procuraCabeca(LHS)) {
            regras.add(new CabecaRegra(LHS));
        }

        for (int i = 0; i < regras.size(); i++) {
            if (regras.get(i).getSimbolo().equals(LHS)) {
                if (regras.get(i).getProx() == null) {
                    regras.get(i).setProx(novaRegra);
                } else {
                    aux = regras.get(i).getProx();
                    while (aux.getProx() != null) {
                        aux = aux.getProx();
                    }
                    aux.setProx(novaRegra);
                }
            }
        }
    }

    public String mostraRegras() {
        Regra aux;
        String strRegra = "";
        for (int i = 0; i < regras.size(); i++) {
            strRegra += regras.get(i).getSimbolo();
            aux = regras.get(i).getProx();
            while (aux != null) {
                strRegra += " -> " + aux.getSimboloTerminal() + aux.getSimboloNTerminal();
                aux = aux.getProx();
            }
            strRegra += "\n";
        }
        return strRegra;
    }     

    public void validar(String expressao) {

        if (percorreRegras(0, 0, expressao)) {
            JOptionPane.showMessageDialog(null, "Pertence");
        }
        JOptionPane.showMessageDialog(null, "Não pertence");
    }

    public boolean percorreRegras(int indice_e, int indice_r, String expressao) {
        int indice_nt;
        CabecaRegra c = regras.get(indice_r);
        Regra aux = c.getProx();
        char token = expressao.charAt(indice_e);

        while (aux != null) {
            if (aux.getSimboloTerminal() == '-') {
                indice_nt = procuraNTerminal(aux.getSimboloNTerminal());
                return percorreRegras(indice_e, indice_nt, expressao);
            } else if (aux.getSimboloTerminal() == token) {
                indice_nt = procuraNTerminal(aux.getSimboloNTerminal());
                return percorreRegras(indice_e++, indice_nt, expressao);
            }
            aux = aux.getProx();
        }
        return false;
    }

    public int procuraNTerminal(char simbolo) {
        for (int i = 0; i < regras.size(); i++) {
            if (regras.get(i).getSimbolo() == simbolo) {
                return i;
            }
        }
        return -1;
    }
    
}
