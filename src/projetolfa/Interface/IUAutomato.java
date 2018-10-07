/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetolfa.Interface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import projetolfa.Automato;
import projetolfa.ControladorAresta;
import projetolfa.ControladorNo;
import projetolfa.State;
import projetolfa.Transicao;

/**
 *
 * @author Giulia
 */
public class IUAutomato extends javax.swing.JDialog {
    private String operacao;
    private boolean BOTAO1, BOTAO2, BOTAO3, BOTAO4, BOTAO5, stepbystep, auxstep, resultado, proximo;
    public Automato graph;
    private String input;
    private ControladorNo init;
    private boolean[] visit, nextVisit;
     private Integer[] index;
     public Painel painel;
     private ArrayList<String> sentencas = new ArrayList<>();
     private int orig = -1, dest = -1, indice;
     public final static String y = "\u03BB";
     DefaultTableModel model, GRTableModel;
    
    
    public IUAutomato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
    }
    
    private boolean validacao(Automato g, String s) {
        if (g != null && g.grafo.inicio() != null && g.grafo.fim() != null && s != null) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Verifique se você setou um estado inicial, final e pelo menos uma sentença de entrada.");
        return false;
    }
    
    private void multiplasExecucoes() {

        for (int i = 0; i < jTable1.getRowCount() - 1; i++) {
            boolean resultado;

            if (graph.grafo.executa((String) jTable1.getValueAt(i, 0))) {
                jTable1.setValueAt("Válido", i, 1);
            } else {
                jTable1.setValueAt("Inválido", i, 1);
            }
        }
    }
    
    private void executar() {
        input = txtSentenca.getText();
        if (validacao(graph, input)) {
            if (graph.grafo.executa(input)) {
                lblResult.setText("Válida");
            } else {
                lblResult.setText("Inválida");
            }
        }
    }

    private void passoApasso() {
        stepbystep = true;
        String input = txtSentenca.getText();

        if (validacao(graph, input)) {
            this.init = graph.grafo.inicio();
            this.visit = new boolean[graph.getVert()];
            this.nextVisit = new boolean[graph.getVert()];
            this.index = new Integer[graph.getVert()];
            nextVisit[init.getId()] = true;
            index[init.getId()] = 0;
            proximo = true;
            resultado = false;
            JOptionPane.showMessageDialog(null, "Utilize o botão 'PRÓXIMO' para ir ao próximo passo.");
        }
        if (input != null) {
            btnPassoAPasso.setEnabled(true);
            proximoPasso();
        } else {
            stepbystep = false;
        }
    }
    
    public void proximoPasso() {
        if (proximo) {
            for (int i = 0; i < nextVisit.length; i++) {
                if (graph.findVertex(i) != null) {
                    if (nextVisit[i]) {
                        graph.findVertex(i).setColor(Color.GRAY);
                    } else {
                        graph.findVertex(i).setColor(Color.YELLOW);
                    }
                }
            }

            this.painel.cleanImage();
            this.painel.repaint();
            System.arraycopy(nextVisit, 0, visit, 0, visit.length);
            estadoInicial(nextVisit);
            boolean lambda = false;

            for (int i = 0; i < visit.length; i++) {
                if (visit[i] == true) {
                    lambda = verificaVazio(i, lambda);
                    if (graph.grafo.passoExeVisita(i, input, index, nextVisit)) {
                        proximo = false;
                        resultado = true;
                        return;
                    }
                }
            }
            boolean semTransicoes = true;
            for (int i = 0; i < graph.vertex.size(); i++) {
                if ((!lambda) && (visit[i]) && (index[i] >= input.length())) {
                    proximo = false;
                    resultado = false;
                } else if ((!lambda) && (visit[i]) && (index[i] < input.length())) {
                    proximo = true;
                }
                if (visit[i]) {
                    semTransicoes = false;
                }
            }
            if (semTransicoes) {
                proximo = false;
            }
        }
        verificaFim();
    }
    
    private void verificaFim() {
        if (!proximo) {
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Válida!");
            } else {
                JOptionPane.showMessageDialog(null, "Inválida!");
            }
            stepbystep = false;
            for (State v : graph.getVertex()) {
                v.setColor(Color.yellow);
            }
            btnPassoAPasso.setEnabled(false);
            this.painel.cleanImage();
            this.painel.repaint();
        }
    }
    
    private boolean verificaVazio(int i, boolean lambda) {
        ControladorNo v = graph.grafo.getLista().encontraVertice(i);

        if (lambda) {
            return true;
        }

        for (ControladorAresta aux = v.getProx(); aux != null; aux = aux.getProx()) {
            if (aux.getEstado().equals("\u03BB")) {
                return true;
            }
        }

        return false;
    }

    private void estadoInicial(boolean[] proximosVisitados) {
        for (int i = 0; i < proximosVisitados.length; i++) {
            proximosVisitados[i] = false;
        }
    }

    public void pegaSentenças(String strRHS) {
        String aux;
        for (int i = 0; i < jTable1.getRowCount() - 1; i++) {
            aux = jTable1.getValueAt(i, 0).toString();
            sentencas.add(aux);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnStep = new javax.swing.JButton();
        btnPassoAPasso = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        grButton = new javax.swing.JButton();
        erButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtSentenca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();
        testarButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Autômato Finito");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetolfa/Interface/icones/close.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetolfa/Interface/icones/hand_cursor-48_44849.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetolfa/Interface/icones/plus_91060.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetolfa/Interface/icones/seta.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetolfa/Interface/icones/next-4_icon-icons.com_64988.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        btnStep.setText("Passo a passo OFF");
        btnStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStepActionPerformed(evt);
            }
        });

        btnPassoAPasso.setText("Próximo");

        limparButton.setText("Limpar");

        grButton.setText("Gramatica");

        erButton.setText("Expressão Regular");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setText("Sentença");

        lblResult.setText("Resultado");

        testarButton.setText("Testar");
        testarButton.setFocusable(false);
        testarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        testarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        testarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSentenca, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblResult))
                    .addComponent(jLabel1))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(testarButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSentenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResult))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(testarButton)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Entrada", jPanel2);

        jButton12.setText("Testar");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Sentença", "Resultado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Multiplas entradas", jPanel3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetolfa/Interface/icones/double-circular.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(122, 122, 122)
                        .addComponent(btnStep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPassoAPasso))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(limparButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(erButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(grButton))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnStep)
                            .addComponent(btnPassoAPasso))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(limparButton)
                            .addComponent(erButton)
                            .addComponent(grButton))
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        multiplasExecucoes();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (jTable1.getSelectedRow() == jTable1.getRowCount() - 1) {
            Object[] linha = new Object[2];
            linha[0] = null;
            linha[1] = null;
            model.addRow(linha);
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void testarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testarButtonActionPerformed
        if (btnStep.getText().equals("PASSO A PASSO (OFF)")) {
            executar();
        } else {
            passoApasso();
        }
    }//GEN-LAST:event_testarButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.operacao = "adicionar";
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.operacao = "cursor";
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.operacao = "remover";
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.operacao = "transicao";
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.operacao = "inicial";
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.operacao = "final";
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStepActionPerformed
        if(btnStep.getText().equals("Passo a passo OFF")){
            btnStep.setText("Passo a passo ON");
        } else{
            btnStep.setText("Passo a passo OFF");
        }
    }//GEN-LAST:event_btnStepActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IUAutomato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IUAutomato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IUAutomato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IUAutomato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IUAutomato dialog = new IUAutomato(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    public final class Painel extends JPanel {

        public Painel() {
            this.setBackground(java.awt.Color.white);
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.addMouseListener(new Mouse());
            this.addMouseMotionListener(new MouseMotion());
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            //desenhar estado
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
            if (graph != null && this.imageBuffer == null) {
                this.imageBuffer = new BufferedImage(graph.getSize().width + 1,
                        graph.getSize().height + 1, BufferedImage.TYPE_INT_RGB);

                java.awt.Graphics2D g2Buffer = this.imageBuffer.createGraphics();
                g2Buffer.setColor(this.getBackground());
                g2Buffer.fillRect(0, 0, graph.getSize().width + 1, graph.getSize().height + 1);

                g2Buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graph.draw(g2Buffer);
                g2Buffer.setColor(Color.BLACK);

                g2Buffer.dispose();
            }

            if (this.imageBuffer != null) {
                g2.drawImage(this.imageBuffer, 0, 0, null);
            }
        }

        public void cleanImage() {
            this.imageBuffer = null;
        }

        @Override
        public void setBackground(Color bg) {
            super.setBackground(bg);
        }

        public BufferedImage imageBuffer;
    }

    public class Mouse extends MouseAdapter {

        State selecionado = null;

        @Override
        public void mouseClicked(MouseEvent e) {

            if (operacao.equals("adicionar")) {

                if (graph == null) {
                    graph = new Automato();
                }
                graph.addVertex(e.getX(), e.getY());
            }

            painel.cleanImage();
            painel.repaint();

            if (operacao.equals("remover")) {
                if (selecionado != null) {
                    graph.delVertex(selecionado);
                }
                if (graph.numVert == 0) {
                    graph = null;
                }
            }

            if (operacao.equals("inicial") || operacao.equals("final")) {
                if (this.getSelecionado() != null) {
                    if (operacao.equals("inicial")) {
                        if (getSelecionado().isInicial() == false) {
                            for (int i = 0; i < graph.vertex.size(); i++) {
                                graph.setInicial(graph.getVertex(i), false);
                            }
                            graph.setInicial(getSelecionado(), true);
                        } else {
                            graph.setInicial(getSelecionado(), false);
                        }
                        painel.cleanImage();
                        painel.repaint();

                    }
                    if (operacao.equals("final")) {
                        if (getSelecionado().isFinal() == false) {
                            graph.setFinal(getSelecionado(), true);
                            getSelecionado().setFinal(true);
                        } else {
                            graph.setFinal(getSelecionado(), false);

                        }
                        painel.cleanImage();
                        painel.repaint();
                    }
                }

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON2 && stepbystep == false) {
                if ((graph != null) && (!graph.vertex.isEmpty())) {
                    for (int i = graph.vertex.size() - 1; i >= 0; i--) {
                        float x = graph.vertex.get(i).getX(),
                                y = graph.vertex.get(i).getY(),
                                ray = graph.vertex.get(i).getRay();
                        if ((x + ray > e.getX()) && (x - ray < e.getX()) && (y + ray > e.getY()) && (y - ray < e.getY())) {
                            setSelecionado(graph.vertex.get(i));
                            getSelecionado().setSelected(true);
                            break;
                        }
                    }
                }
            }
            painel.cleanImage();
            painel.repaint();
            if ((e.getButton() == MouseEvent.BUTTON3)) {
                BOTAO3 = true;
            }

            if ((e.getButton() == MouseEvent.BUTTON1)) {
                BOTAO1 = true;
            }

            if ((e.getButton() == MouseEvent.BUTTON2)) {
                BOTAO2 = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (operacao.equals("transicao")) {
                for (int i = graph.vertex.size() - 1; i >= 0; i--) {
                    if (graph.vertex.get(i).isSelected()) {
                        orig = i;
                        break;
                    }
                }
                if (orig != -1) {
                    BOTAO4 = true;
                }
            }
            if ((graph != null) && (!graph.vertex.isEmpty()) && (this.getSelecionado() != null) && stepbystep == false) {
                this.getSelecionado().setSelected(false);
                painel.cleanImage();
                painel.repaint();
            }
            if (operacao.equals("transicao")) {
                if ((graph != null) && (!graph.vertex.isEmpty())) {
                    for (int i = graph.vertex.size() - 1; i >= 0; i--) {
                        float x = graph.vertex.get(i).getX(),
                                y = graph.vertex.get(i).getY(),
                                ray = graph.vertex.get(i).getRay();
                        if ((x + ray > e.getX()) && (x - ray < e.getX()) && (y + ray > e.getY()) && (y - ray < e.getY())) {
                            dest = i;
                            break;
                        }
                    }
                }
                if (dest != -1) {
                    String label = JOptionPane.showInputDialog(null, "Transição:");
                    if (label != null) {
                        if (label.equals("")) {
                            label = y;
                        }
                        graph.addEdge(new Transicao(graph.vertex.get(orig), graph.vertex.get(dest), label));
                        BOTAO4 = false;
                        orig = dest = -1;
                        painel.cleanImage();
                        painel.repaint();
                    } else {
                        dest = -1;
                        BOTAO4 = false;
                    }
                } else {
                    BOTAO4 = false;
                }
            }

            if (BOTAO5 && stepbystep == false) {
                if ((graph != null) && (!graph.vertex.isEmpty())) {
                    for (int i = graph.vertex.size() - 1; i >= 0; i--) {
                        float x = graph.vertex.get(i).getX(),
                                y = graph.vertex.get(i).getY(),
                                ray = graph.vertex.get(i).getRay();
                        if ((x + ray > e.getX()) && (x - ray < e.getX()) && (y + ray > e.getY()) && (y - ray < e.getY())) {
                            dest = i;
                            break;
                        }
                    }
                }
                if (dest != -1) {
                    String label = JOptionPane.showInputDialog(null, "Transição a ser excluída: ");
                    if (label != null) {
                        if (label.equals("")) {
                            label = y;
                        }
                        if (!graph.delEdge(graph.vertex.get(orig), graph.vertex.get(dest), label)) {
                            JOptionPane.showMessageDialog(null, "Transição inexistente!");
                        }

                        BOTAO5 = false;
                        orig = dest = -1;
                        painel.cleanImage();
                        painel.repaint();
                    } else {
                        dest = -1;
                        BOTAO5 = false;
                    }
                } else {
                    BOTAO5 = false;
                }
            }
            BOTAO3 = false;
            BOTAO2 = false;
            BOTAO1 = false;
        }

        public void setSelecionado(State verticeSelecionado) {
            this.selecionado = verticeSelecionado;
        }

        public State getSelecionado() {
            return selecionado;
        }

    }

    public class MouseMotion extends MouseMotionAdapter {

        @Override
        //arrastar mouse
        public void mouseDragged(MouseEvent e) {

            //arrastar estado com botao esquerdo do mouse
            if (operacao.equals("cursor")) {
                for (int i = graph.vertex.size() - 1; i >= 0; i--) {
                    if (graph.vertex.get(i).isSelected()) {
                        graph.vertex.get(i).setX(e.getX());
                        graph.vertex.get(i).setY(e.getY());
                        painel.cleanImage();
                        painel.repaint();
                        break;
                    }
                }
            } //criar transição com o botão direito (descobrindo o estado origem)
            else if (operacao.equals("transicao")) {
                for (int i = graph.vertex.size() - 1; i >= 0; i--) {
                    if (graph.vertex.get(i).isSelected()) {
                        orig = i;
                        break;
                    }
                }
                if (orig != -1) {
                    BOTAO4 = true;
                }
            }

            //excluir transição (descobrindo o estado de origem ())
            if ((graph != null) && (!graph.vertex.isEmpty()) && BOTAO2 && stepbystep == false) {
                for (int i = graph.vertex.size() - 1; i >= 0; i--) {
                    if (graph.vertex.get(i).isSelected()) {
                        orig = i;
                        break;
                    }
                }
                if (orig != -1) {
                    BOTAO5 = true;
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPassoAPasso;
    private javax.swing.JButton btnStep;
    private javax.swing.JButton erButton;
    private javax.swing.JButton grButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblResult;
    private javax.swing.JButton limparButton;
    private javax.swing.JButton testarButton;
    private javax.swing.JTextField txtSentenca;
    // End of variables declaration//GEN-END:variables
}
