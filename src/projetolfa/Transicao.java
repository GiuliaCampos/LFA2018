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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

public class Transicao {

    private Color color = Color.WHITE;
    private String label;
    private State ori;
    private State dest;

    public Transicao(State ori, State dest, String label) {
        this.ori = ori;
        this.dest = dest;
        this.label = label;
    }

    public void desenha(java.awt.Graphics2D g2, ArrayList<Transicao> edges) {

        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
        g2.setStroke(new java.awt.BasicStroke(1.0f));
        g2.setColor(this.color.darker().darker());

        Transicao aux = null;
        for (int i = 0; i < edges.size(); i++) {
            if ((this.getDest() == edges.get(i).getOri()) && (this.getOri() == edges.get(i).getDest())) {
                aux = edges.get(i);
            }
        }

        if (this.ori.getId() != this.dest.getId()) {
            Point a, b;
            if (aux == null) { // reta normal
                g2.drawLine(((int) this.ori.getX()), ((int) this.ori.getY()), ((int) this.dest.getX()), ((int) this.dest.getY()));
                g2.setStroke(new java.awt.BasicStroke(1.0f));
                a = new Point((int) ori.getX(), (int) ori.getY());
                b = new Point((int) dest.getX(), (int) dest.getY());
            } else {  //curva
                Point s = new Point((int) this.ori.getX(), (int) this.ori.getY());
                Point t = new Point((int) this.dest.getX(), (int) this.dest.getY());
                //achar o ponto do meio
                int xMeio = (int) ((s.getX() + t.getX()) / 2);
                int yMeio = (int) ((s.getY() + t.getY()) / 2);
                Point meio;

                //achar o quadrante do aux em relacao ao x
                double dx = t.x - s.x, dy = t.y - s.y;
                double theta = Math.toDegrees(Math.atan2(dy, dx));

                if ((s.getX() <= t.getX()) && (s.getY() >= t.getY())) { //primeiro quadrante
                    if ((theta < 45)) {
                        meio = new Point(xMeio - 25, yMeio - 50);
                    } else {
                        meio = new Point(xMeio - 50, yMeio - 25);
                    }
                } else if ((s.getX() > t.getX()) && (s.getY() >= t.getY())) { //segundo quadrante
                    if ((theta < 135)) {
                        meio = new Point(xMeio + 50, yMeio - 25);
                    } else {
                        meio = new Point(xMeio + 25, yMeio - 50);
                    }
                } else if ((s.getX() > t.getX()) && (s.getY() <= t.getY())) { //terceiro quadrante
                    if ((theta < 225)) {
                        meio = new Point(xMeio + 25, yMeio + 50);
                    } else {
                        meio = new Point(xMeio + 50, yMeio + 25);
                    }
                } else { //quarto quadrante
                    if ((theta < 315)) {
                        meio = new Point(xMeio - 50, yMeio + 25);
                    } else {
                        meio = new Point(xMeio - 25, yMeio + 50);
                    }
                }

                QuadCurve2D q = new QuadCurve2D.Float();

                q.setCurve(s, meio, t);
                g2.draw(q);
                meio.x += (meio.x > xMeio ? -10 : 10);
                meio.y += (meio.y > yMeio ? -10 : 10);
                a = meio;
                b = t;
            }
            double ux, uy, norma;
            ux = (a.getX() - b.getX());
            uy = (a.getY() - b.getY());
            norma = Math.sqrt(ux * ux + uy * uy);
            ux = ux / norma;
            uy = uy / norma;
            b.x = (int) (1 + b.getX() + dest.getRay() * ux);
            b.y = (int) (1 + b.getY() + dest.getRay() * uy);
            desenhaHeadSeta(g2, a, b);

            g2.setColor(Color.BLACK);
            desenhaLabel(g2, a, b);
        } else { //laço; aresta para o próprio vértice
            g2.drawOval((int) (this.ori.getX()) - 15, (int) (this.ori.getY()) - 50, this.ori.getRay() + 10, this.ori.getRay() + 20);

            Point a = new Point((int) this.ori.getX() - 20, (int) this.ori.getY() - 50);
            Point b = new Point((int) this.dest.getX() - 11, (int) this.dest.getY() - 15);
            desenhaHeadSeta(g2, a, b);

            g2.setColor(Color.BLACK);
            Point l = new Point((int) this.ori.getX() - 10, (int) this.ori.getY() - 55);
            desenhaLabel(g2, l, l);
        }

        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
    }

    private void desenhaHeadSeta(Graphics2D g2, Point origem, Point destino) {
        double dy = destino.y - origem.y;
        double dx = destino.x - origem.x;
        double theta = Math.atan2(dy, dx);
        double phi = Math.toRadians(20);
        double rho = theta + phi;
        int tam = 10;
        Polygon pol = new Polygon();
        pol.addPoint(destino.x, destino.y);
        pol.addPoint((int) (destino.x - tam * Math.cos(rho)), (int) (destino.y - tam * Math.sin(rho)));
        rho = theta - phi;
        pol.addPoint((int) (destino.x - tam * Math.cos(rho)), (int) (destino.y - tam * Math.sin(rho)));
        g2.fillPolygon(pol);
    }

    private void desenhaLabel(Graphics2D g2, Point s, Point t) {
        int transX = (int) ((t.x + s.x) * 0.5f); //metade da reta
        int transY = (int) ((t.y + s.y) * 0.5f); //metade da reta  
        g2.drawString(label, transX - label.length() * 2, transY - 5);
    }

    private void desenhaTriangulo(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = (x2 - x1), dy = (y2 - y1);
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        len = len - 20;

        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.fillPolygon(new int[]{len, len - 5, len - 5, len},
                new int[]{0, -5, 5, 0}, 4);

    }

    public State getOri() {
        return ori;
    }

    public void setOri(State ori) {
        this.ori = ori;
    }

    public State getDest() {
        return dest;
    }

    public void setDest(State dest) {
        this.dest = dest;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
