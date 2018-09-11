/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolfa;

import projetolfa.Interface.IUPrincipal;

/**
 *
 * @author Giulia
 */
public class ProjetoLFA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IUPrincipal principal = new IUPrincipal();
        principal.setVisible(true);
        principal.setTitle("Trabalho de LFA");
        principal.toFront();
    }
    
}
