package projetolfa;

import projetolfa.ControladorAresta;
import projetolfa.Controller;
import projetolfa.ControladorNo;
import java.util.ArrayList;
import java.util.Iterator;

public class Automato {

    public int numVert;
    private int vert = 0;
    public Controller grafo;
    public ArrayList<Transicao> edges = new ArrayList<>();
    public ArrayList<State> vertex = new ArrayList<>();

    public Automato() {
        this.numVert = 0;
        grafo = new Controller();
    }

    public void addVertex(float x, float y) {
        this.setVert();
        grafo.addVert(vert);
        this.vertex.add(new State(vert, "q" + vert, x, y));
        vert++;
        this.numVert++;
    }

    public void addVertex(int id, String name, String label, float x, float y) {
        grafo.addVert(id);
        this.vertex.add(new State(id, name, label, x, y));
        this.numVert++;
        this.setVert();
    }

    public void setVert() {
        boolean achou = false;
        int tamanho = this.vertex.size();
        int maior = 0;
        int ant;

        while (!achou) {
            ant = maior;
            for (State vertex1 : this.vertex) {
                if (vertex1.getId() == maior) {
                    maior++;
                    break;
                }
            }
            if (maior == ant) {
                achou = true;
            }
        }
        vert = maior;
    }

    public int getVert() {
        return vert;
    }

    public void delVertex(State v) {
        for (int j = 0; j < this.edges.size();) {
            if ((this.edges.get(j).getOri().equals(v))
                    || (this.edges.get(j).getDest().equals(v))) {
                this.delEdge(this.edges.get(j));
            } else {
                j++;
            }
        }
        this.vertex.remove(v);
        grafo.getLista().removeVertice(v.getId());
        this.delNumVertices();
    }

    public void addEdge(Transicao e) {
        Transicao aux = verifEdgesIguais(e);
        if (aux == null) {
            this.edges.add(e);
        } else {
            aux.setLabel(aux.getLabel() + " | " + e.getLabel());
        }
        grafo.addAresta(e.getOri().getId(), e.getDest().getId(), e.getLabel());

    }

    public void delEdge(Transicao e) {
        this.edges.remove(e);
        grafo.getLista().removeAresta(e.getOri().getId(), e.getDest().getId(), e.getLabel());
    }

    public final void compeCircledPos(int ray) {
        int nVertices = this.vertex.size();
        int step = 360 / nVertices;
        int deslocX = 100 + ray;
        int deslocY = 100 + ray;
        for (int i = 0; i < nVertices; i++) {
            double ang = i * step;
            ang = ang * Math.PI / 180;
            float X = (float) Math.cos(ang);
            float Y = (float) Math.sin(ang);
            X = X * ray + deslocX;
            Y = Y * ray + deslocY;
            this.vertex.get(i).setX(X);
            this.vertex.get(i).setY(Y);
        }
    }

    public ArrayList<State> getVertex() {
        return this.vertex;
    }

    public void draw(java.awt.Graphics2D g2) {

        edges.stream().forEach((edge) -> {
            edge.desenha(g2, edges);
        });
        this.vertex.stream().forEach((v) -> {
            v.desenhar(g2);
        });

    }

    public ArrayList<Transicao> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Transicao> edges) {
        this.edges = edges;
    }

    public java.awt.Dimension getSize() {
        if (this.vertex.size() > 0) {
            float maxX = vertex.get(0).getX();
            float minX = vertex.get(0).getX();
            float maxY = vertex.get(0).getY();
            float minY = vertex.get(0).getY();

            for (State v : this.vertex) {
                if (maxX < v.getX()) {
                    maxX = v.getX();
                } else {
                    if (minX > v.getX()) {
                        minX = v.getX();
                    }
                }

                if (maxY < v.getY()) {
                    maxY = v.getY();
                } else {
                    if (minY > v.getY()) {
                        minY = v.getY();
                    }
                }
            }

            int w = (int) (maxX + (this.vertex.get(0).getRay() * 5)) + 350;
            int h = (int) (maxY + (this.vertex.get(0).getRay() * 5));

            return new java.awt.Dimension(w, h);
        } else {
            return new java.awt.Dimension(0, 0);
        }
    }

    public void delNumVertices() {
        this.numVert--;
    }

    public int getNumVert() {
        return numVert;
    }

    public State getVertex(int i) {
        return this.vertex.get(i);
    }

    private Transicao verifEdgesIguais(Transicao e) {
        for (int i = 0; i < getEdges().size(); i++) {
            Transicao aux = this.getEdges().get(i);
            if ((aux.getOri() == e.getOri()) && (aux.getDest() == e.getDest())) {
                return aux;
            }
        }
        return null;
    }

    public boolean delEdge(State origem, State destino, String label) {

        if (grafo.delAresta(origem.getId(), destino.getId(), label) == false) {
            return false;
        }

        Transicao edg = null;
        for (Iterator<Transicao> it = this.getEdges().iterator(); it.hasNext();) {
            edg = it.next();
            if ((edg.getOri().getId() == origem.getId()) && (edg.getDest().getId() == destino.getId())) {
                break;
            }
        }

        if (edg == null) {
            return false;
        }

        String newLabel = null;
        for (ControladorAresta aux = grafo.getLista().encontraVertice(origem.getId()).getProx(); aux != null; aux = aux.getProx()) {
            if (aux.getFin() == destino.getId()) {
                if (newLabel == null) {
                    newLabel = aux.getEstado();
                } else {
                    newLabel = newLabel.concat(" | ").concat(aux.getEstado());
                }
            }
        }
        if (newLabel != null) {
            edg.setLabel(newLabel);
        } else {
            getEdges().remove(edg);
        }

        return true;
    }

    public void setInicial(State v, boolean isInicial) {
        v.setInicial(isInicial);
        grafo.getLista().setInicial(v.getId(), isInicial);
    }

    public void setFinal(State v, boolean isFinal) {
        v.setFinal(isFinal);
        grafo.getLista().setFinal(v.getId(), isFinal);
    }

    public ControladorNo getFinal() {
        return grafo.fim();

    }

    public State findVertex(int vID) {
        for (int i = 0; i < this.getVertex().size(); i++) {
            if (this.getVertex().get(i).getId() == vID) {
                return this.getVertex().get(i);
            }
        }
        return null;
    }

}

