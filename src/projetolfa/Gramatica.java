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
public class Gramatica {
    
    //private String teste;
    private NO gramatica = null;
    
    
    public void Gramatica(String letra, String regra){
        NO novoNo = new NO();
        //novoNo = null;
        
        novoNo.setLetra(letra);
        
        if((regra == null|| regra.trim().equals(""))){ // se for o ultimo 
            novoNo.setProx(null);
        }
        else if(regra.length() == 1){//se só houver uma letra ela será tratada como não terminal
            NO aux = new NO();
            aux.setLetra(regra);
            novoNo.setProx(aux);
        }
        else{ //Aqui "regra" seria a 2 coluna, ex: aA
            for(int i = 0; i < regra.length(); i++){ 
                novoNo.adcFilho(regra.substring(i, i));
                if(i == regra.length() - 1){
                    NO aux = new NO();
                    aux.setLetra(regra.substring(i, i));
                    novoNo.setProx(aux);
                }
            }
        }
    }
    
}
