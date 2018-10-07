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
public class Controller {

    private ControladorGrafo listaAdj;
    private int nVertices;

    //Controlador vazio
    public Controller() {
        this.nVertices = 0;
        this.listaAdj = new ControladorGrafo();
    }

    public Controller(int numVert) {
        this.nVertices = 0;
        this.listaAdj = new ControladorGrafo(numVert);
    }

    public void addAresta(int ini, int fim, String estado) {
        listaAdj.addAresta(ini, fim, estado);
    }

    public int getNumVert() {
        return nVertices;
    }

    public boolean delAresta(int vIni, int vFin, String estado) {
        ControladorAresta ant = null;
        for (ControladorAresta aux = listaAdj.buscaVert(vIni).getProx(); aux != null; aux = aux.getProx()) {
            if ((aux.getFin() == vFin) && (aux.getEstado().equals(estado))) {
                if (ant == null) {
                    listaAdj.buscaVert(vIni).setProx(aux.getProx());
                } else {
                    ant.setProx(aux.getProx());
                }
                return true;
            }
            ant = aux;
        }
        return false;
    }

    public void addVert(int ID) {
        listaAdj.addVert(ID);
        nVertices++;
    }

    public ControladorGrafo getLista() {
        return listaAdj;
    }

    public void imprimeListaAdjacencia() {
        listaAdj.imprimeRepresentacao();
    }

    public boolean executa(String input) {
        ControladorNo inicial = inicio();
        return exeRapidaVisita(inicial.getId(), input, 0);
    }

    private boolean exeRapidaVisita(int vID, String input, int index) {
        ControladorNo v = listaAdj.buscaVert(vID);
        boolean lambda = false;
        for (ControladorAresta aux = v.getProx(); aux != null; aux = aux.getProx()) {
            if (aux.getEstado().equals("\u03BB")) {
                lambda = true;
            }
        }

        if ((index >= input.length()) && (!lambda)) {
            return v.isFim();
        }

        for (ControladorAresta aux = v.getProx(); aux != null; aux = aux.getProx()) {
            if (aux.getEstado().equals("\u03BB")) {
                if (exeRapidaVisita(aux.getFin(), input, index)) {
                    return true;
                }
            } else {
                String saresta = input.substring(index, index + aux.getEstado().length());
                if (aux.getEstado().equals(saresta)) {
                    if (exeRapidaVisita(aux.getFin(), input, index + aux.getEstado().length())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ControladorNo inicio() {
        for (ControladorNo listaVertice : listaAdj.listaVertices) {
            if (listaVertice.isInicial()) {
                return listaVertice;
            }
        }
        return null;
    }

    public ControladorNo fim() {
        for (ControladorNo listaVertice : listaAdj.listaVertices) {
            if (listaVertice.isFim()) {
                return listaVertice;
            }
        }
        return null;
    }

    public boolean passoExeVisita(int vID, String input, Integer[] index, boolean proxVisita[]) {
        ControladorNo v = listaAdj.buscaVert(vID);
        boolean lambda = false;

        for (ControladorAresta aux = v.getProx(); aux != null; aux = aux.getProx()) {
            if (aux.getEstado().equals("\u03BB")) {
                lambda = true;
            }
        }

        if ((index[vID] >= input.length()) && (!lambda)) {
            return v.isFim();
        }

        for (ControladorAresta aux = v.getProx(); aux != null; aux = aux.getProx()) {

            if (aux.getEstado().equals("\u03BB")) {
                proxVisita[aux.getFin()] = true;
                index[aux.getFin()] = index[vID];
            } else {
                String saresta = input.substring(index[vID], index[vID] + aux.getEstado().length());
                if (aux.getEstado().equals(saresta)) {
                    proxVisita[aux.getFin()] = true;
                    index[aux.getFin()] = index[vID] + aux.getEstado().length();
                }
            }
        }
        return false;
    }

}
