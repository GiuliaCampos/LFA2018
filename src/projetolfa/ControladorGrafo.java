/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolfa;
import java.util.ArrayList;

public class ControladorGrafo {

    public ArrayList<ControladorNo> listaVertices = new ArrayList<>();

    public ControladorGrafo(int numVert) {
        for (int i = 0; i < numVert; i++) {
            this.addVert(i);
        }
    }

    public ControladorGrafo() {

    }

    public void addAresta(int ini, int fin, String e) {
        ControladorAresta nova = null, ant = null, aux = null;
        nova = new ControladorAresta(ini, fin, e, null);
        for (aux = buscaVert(ini).getProx(); aux != null; aux = aux.getProx()) {
            ant = aux;
        }
        if (ant == null) {
            buscaVert(ini).setProx(nova);
        } else {
            ant.setProx(nova);
        }
    }

    public ControladorAresta getAdjacente(int numVert) {
        return listaVertices.get(numVert).getProx();
    }

    public void imprimeRepresentacao() {
        ControladorAresta auxiliar;
        for (int i = 0; i < listaVertices.size(); i++) {
            System.out.print(i + "->");
            for (auxiliar = listaVertices.get(i).getProx(); auxiliar != null; auxiliar = auxiliar.getProx()) {
                System.out.print("(" + auxiliar.getIni() + "," + auxiliar.getFin() + "," + auxiliar.getEstado() + ") ");
            }
            System.out.print("\n");
        }
    }

    public void addVert(int id) {
        listaVertices.add(listaVertices.size(), new ControladorNo(id));
    }

    public void setInicial(int id, boolean inicial) {
        for (ControladorNo listaVert : listaVertices) {
            if (listaVert.getId() == id) {
                listaVert.setInicial(inicial);
            }
        }
    }

    public void setFinal(int id, boolean vfinal) {
        for (ControladorNo listaVertice : listaVertices) {
            if (listaVertice.getId() == id) {
                listaVertice.setFim(vfinal);
            }
        }
    }

    public void removeAresta(int vIni, int vFin, String estado) {
        ControladorNo Ini = null;
        for (ControladorNo listaVert : listaVertices) {
            if (listaVert.getId() == vIni) {
                Ini = listaVert;
                break;
            }
        }

        ControladorAresta ant = null;
        for (ControladorAresta aux = Ini.getProx(); aux != null; aux = aux.getProx()) {
            if ((aux.getFin() == vFin) && (aux.getEstado().equals(estado))) {
                if (ant == null) {
                    Ini.setProx(aux.getProx());
                } else {
                    ant.setProx(aux.getProx());
                }
            } else {
                ant = aux;
            }
        }
    }

    public void removeVertice(int id) {
        for (int i = 0; i < listaVertices.size(); i++) {
            if (listaVertices.get(i).getId() == id) {
                listaVertices.remove(i);
                break;
            }
        }
    }

    public ControladorNo encontraVertice(int vID) {
        for (ControladorNo listaVertice : listaVertices) {
            if (listaVertice.getId() == vID) {
                return listaVertice;
            }
        }
        return null;
    }

    public ControladorNo buscaVert(int id) {
        for (int i = 0; i < listaVertices.size(); i++) {
            if (listaVertices.get(i).getId() == id) {
                return listaVertices.get(i);
            }
        }
        return null;
    }
}
