/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package graphv1;

import GraphFinal.Edge;
import GraphFinal.Node;
import GraphFinal.Graph;
import GraphFinal.Visual;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author lycee
 */
public class Screen0 extends javax.swing.JFrame {

    Visual graphV;
    
    private Graph graph;
    
    ArrayList<Node> nodes;
    HashMap<Node, ArrayList<Edge>> adjacentList;
//        
    private File file1;
    
    private File file2;
        
    private  boolean dikjstra;
    
    /**
     * Creates new form Guitest
     */
    public Screen0(Graph graph) {
        initComponents();
//        nodes = graph.getNodeList();
//        adjacentList =  graph.getAdjacentList();
        this.graph = graph;
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        showGraphButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        listAdjacentCheckBox = new javax.swing.JCheckBox();
        listSuccesseurCheckBox = new javax.swing.JCheckBox();
        fileStatusLabel = new javax.swing.JLabel();
        dijkstraCheckBox = new javax.swing.JCheckBox();
        startSpinner = new javax.swing.JSpinner();
        endSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        graphVisualPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 700));
        setSize(new java.awt.Dimension(1000, 700));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setMaximumSize(new java.awt.Dimension(100, 32767));

        jButton1.setText("liste-successeurs");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        showGraphButton.setBackground(new java.awt.Color(245, 237, 0));
        showGraphButton.setText("Show Graph");
        showGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showGraphButtonActionPerformed(evt);
            }
        });

        jButton2.setText("liste-adjacence");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        listAdjacentCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listAdjacentCheckBoxActionPerformed(evt);
            }
        });

        listSuccesseurCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listSuccesseurCheckBoxActionPerformed(evt);
            }
        });

        fileStatusLabel.setText("...");

        dijkstraCheckBox.setText("Dikjstra");
        dijkstraCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dijkstraCheckBoxActionPerformed(evt);
            }
        });

        startSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 30, 1));

        endSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 30, 1));

        jLabel1.setText("S");

        jLabel2.setText("->");

        jLabel4.setText("S");

        jButton3.setText("start Dikjstra");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(listAdjacentCheckBox)
                            .addComponent(listSuccesseurCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(showGraphButton)
                            .addComponent(dijkstraCheckBox))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(fileStatusLabel)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(40, 40, 40))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(listAdjacentCheckBox))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(listSuccesseurCheckBox))
                .addGap(18, 18, 18)
                .addComponent(showGraphButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fileStatusLabel)
                .addGap(30, 30, 30)
                .addComponent(dijkstraCheckBox)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(47, 47, 47))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout graphVisualPanelLayout = new javax.swing.GroupLayout(graphVisualPanel);
        graphVisualPanel.setLayout(graphVisualPanelLayout);
        graphVisualPanelLayout.setHorizontalGroup(
            graphVisualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        graphVisualPanelLayout.setVerticalGroup(
            graphVisualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        getContentPane().add(graphVisualPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showGraphButtonActionPerformed
        // TODO add your handling code here:
        
        //if(graphV != null || nodes == null || adjacentList == null) return;
        if(listAdjacentCheckBox.isSelected() && listSuccesseurCheckBox.isSelected()){
            graphV = null;
            System.out.println("check is true");
            graph.fillGraph(file1, file2);
            file1 = null;
            file2 = null;
            graphV = new Visual(graphVisualPanel, graph, this);
            graphVisualPanel.add(graphV);
            this.setSize(this.getWidth() + 1, this.getHeight());
            nodes = graph.getNodeList();
            adjacentList = graph.getAdjacentList();
        }else{
            fileStatusLabel.setText("il y a un problem avec le fichiers");
        }
    }//GEN-LAST:event_showGraphButtonActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        if(graphV == null || nodes == null || adjacentList == null) return;
        graphV.repaint();
    }//GEN-LAST:event_formComponentResized

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        file1 = filefinder();
        if(file1 != null){
            listAdjacentCheckBox.setSelected(true);
            System.out.println(file1.getName());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        file2 = filefinder();
        if(file2 != null){
            listSuccesseurCheckBox.setSelected(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listAdjacentCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listAdjacentCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listAdjacentCheckBoxActionPerformed

    private void listSuccesseurCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listSuccesseurCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listSuccesseurCheckBoxActionPerformed

    private void dijkstraCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dijkstraCheckBoxActionPerformed
        // TODO add your handling code here:
        dikjstra = dijkstraCheckBox.isSelected();
    }//GEN-LAST:event_dijkstraCheckBoxActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        startDikjstra();
    }//GEN-LAST:event_jButton3ActionPerformed

    public File getFile1() {
        return file1;
    }

    public File getFile2() {
        return file2;
    }

    public void startDikjstra(){
        if(dikjstra){
            int startNode = (int) startSpinner.getValue();
            int endNode = (int) endSpinner.getValue();        
            if(startNode != endNode){
                graph.initialColor();
                graph.printShortByDistanceOrTime(graph.getNodeByNum(startNode), graph.getNodeByNum(endNode), "t");
                graph.printShortByDistanceOrTime(graph.getNodeByString("S1"), graph.getNodeByString("S17"), "t");
            }
        }
    }

//    /**
//     * @param args the command line arguments
//     */
////    public static void main(String args[]) {
////        /* Set the Nimbus look and feel */
////        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
////        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
////         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
////         */
////        try {
////            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
////                if ("Nimbus".equals(info.getName())) {
////                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
////                    break;
////                }
////            }
////        } catch (ClassNotFoundException ex) {
////            java.util.logging.Logger.getLogger(Guitest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
////        } catch (InstantiationException ex) {
////            java.util.logging.Logger.getLogger(Guitest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
////        } catch (IllegalAccessException ex) {
////            java.util.logging.Logger.getLogger(Guitest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
////        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
////            java.util.logging.Logger.getLogger(Guitest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
////        }
////        //</editor-fold>
////
////        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new Guitest(null, null).setVisible(true);
////            }
////        });
////    }

    
    
    
    
    public void setFileStatus(boolean b){
        String text = "le graphe est ";
        if(b){
            text +=  "bien chargé et mémoire";
        }else{
            text += " mal chargé et mémoire";
        }
        fileStatusLabel.setText(text);
    }
    
    
    
    
    public File filefinder(){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier CSV (*.csv)", "csv");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        chooser.setApproveButtonText("Ouvrir");
        chooser.setDialogTitle("Enregistrer sous");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int result = chooser.showSaveDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            System.out.println( chooser.getSelectedFile());
            return chooser.getSelectedFile();
        } else if(result == JFileChooser.CANCEL_OPTION){
        // Dans le cas où l’on annule
            return null;
        }
        return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox dijkstraCheckBox;
    private javax.swing.JSpinner endSpinner;
    private javax.swing.JLabel fileStatusLabel;
    private javax.swing.JPanel graphVisualPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox listAdjacentCheckBox;
    private javax.swing.JCheckBox listSuccesseurCheckBox;
    private javax.swing.JButton showGraphButton;
    private javax.swing.JSpinner startSpinner;
    // End of variables declaration//GEN-END:variables
}
