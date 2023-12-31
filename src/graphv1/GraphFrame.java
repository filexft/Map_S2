/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphv1;

import GraphFinal.Edge;
import GraphFinal.Node;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import static java.awt.SystemColor.text;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lycee
 */
public class GraphFrame  extends JFrame{
    
    public GraphFrame(ArrayList<Node> nodeList, HashMap<Node, ArrayList<Edge>> adjacentList){
        
        this.setAlwaysOnTop(true);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        ArrayList<Node> nodes = new ArrayList<Node>();
        
        Node na = new Node("S1", "M");
        Node nb = new Node("S1", "M");
        Node nc = new Node("S1", "M");
        
        
        nodes.add(na);
        nodes.add(nb);
        nodes.add(nc);
        
        //Slate s = new Slate(this,  nodeList, adjacentList);
        
        //this.getContentPane().add(s);
        
//        JFileChooser file = new JFileChooser();
//        file.showSaveDialog(file);
       
         this.setVisible(true);
       
    }
//    
//    public final class Slate extends JPanel{
//        
//        private static int center_X;
//        private static int center_Y;
//        private static int innerRadius = 100;
//        private static int layerSpacing = 70;
//        private static int nodesLayer;
//        private static int nodesPerLayer;
//        private double nodeRadius = 15;
//        
//        private int start_X = 0;
//        private int start_Y = 0;
//        
//        private Node seletedNode;
//        boolean isSelected = false;
//        
//        ArrayList<Node> nodes = new ArrayList<Node>();
//        HashMap<Node, ArrayList<Edge>> adjacentList = new HashMap<Node, ArrayList<Edge>>();
//        
//        
//        
//        
//        public Slate(JFrame frame, ArrayList<Node> nodeList, HashMap<Node, ArrayList<Edge>> adjacList){
//            this.setSize(frame.getWidth(), frame.getHeight());
//            this.setVisible(true);
//            nodes = nodeList;
//            adjacentList = adjacList;
//            center_X = frame.getWidth()/2;
//            center_Y = frame.getHeight()/2;
//            nodesLayer = (int) Math.ceil(nodeList.size()/8);
//            nodesPerLayer = nodeList.size()/nodesLayer;
//            
//            //init nodes pos
//            setNodePosition();
//            /**
//             * adding event listener to make it draggable
//             */
//            this.addMouseListener(new MouseAdapter(){
//                @Override
//                public void mousePressed(MouseEvent e) {
////                     if(seletedNode == null) return;
////                    seletedNode = getNodeAtPos(e.getX(), e.getY());
////                    start_X = seletedNode.getX();
////                    start_Y = seletedNode.getY();
//
//                    seletedNode = getNodeAtPos(e.getX(), e.getY());
//                    if(seletedNode == null) return;
//                }
//
//                @Override
//                public void mouseClicked(MouseEvent e) {
////                    if(seletedNode != null){
////                         seletedNode.setX(e.getX());
////                         seletedNode.setX(e.getY());
////                         seletedNode = null;
////                    }
////                    seletedNode = getNodeAtPos(e.getX(), e.getY());
////                     if(seletedNode == null) return;
//                     
////                     
////                    System.out.println("clicked " + seletedNode.getId() + "at "+ seletedNode.getX() + "," + seletedNode.getY());
////                    start_X = seletedNode.getX();
////                    start_Y = seletedNode.getY();
//                      for(Node n: nodes){
//                          for(Edge e : adjacentList.get(n)){
//                              
//                          }
//                      }
//                }
//                
//                @Override
//                public void mouseReleased(MouseEvent e) {
//                    
//                    if(seletedNode != null){
//                         seletedNode.setX(e.getX());
//                         seletedNode.setX(e.getY());
//                         seletedNode = null;
//                    }
//                    System.out.println(e.getX()+" , "+ e.getY());
////                    if(seletedNode == null) return;
////                    
////                    seletedNode.setX(e.getX());
////                    seletedNode.setY(e.getY());
////                    System.out.println("release " + seletedNode.getId() + "at "+ seletedNode.getX() + "," + seletedNode.getY());
////                    repaint();
////                    revalidate();
//                }
//                
//            });
//            
//            this.addMouseMotionListener(new MouseMotionListener(){
//                @Override
//                public void mouseDragged(MouseEvent e) {
//                    //
//                     if(seletedNode == null) return;
//                    System.out.println(seletedNode.getId() + "moved to" +e.getX()+ ", "+ e.getY());
//                    
//                    seletedNode.setX(e.getX());
//                    seletedNode.setY(e.getY());
//                    System.out.println("release " + seletedNode.getId() + "at "+ seletedNode.getX() + "," + seletedNode.getY());
//                    repaint();
//                    revalidate();
//                }
//
//                @Override
//                public void mouseMoved(MouseEvent e) {
//                    //
////                    if(seletedNode == null) return;
////                    System.out.println(seletedNode.getId() + "moved to" +e.getX()+ ", "+ e.getY());
////                    
////                    seletedNode.setX(e.getX());
////                    seletedNode.setY(e.getY());
////                    System.out.println("release " + seletedNode.getId() + "at "+ seletedNode.getX() + "," + seletedNode.getY());
////                    repaint();
////                    revalidate();
//                }
//                
//            });
//        }
// 
//        @Override
//        public void paintComponent(Graphics g){
//            super.paintComponent(g);
//            Graphics2D g2d = (Graphics2D) g;
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setColor(Color.red);
//            
//            
////            double layerRadius = innerRadius;
////            
////           
////            int i = 0;
////            for(int layer = 0; layer < nodesLayer; layer++){
////                double angleStep = 2 * Math.PI/ nodesPerLayer;
////                
////                for(int node = 0; node < nodesPerLayer; node++){
////                    double angle = angleStep * node;
////                    double x = center_X + layerRadius * Math.cos(angle) - nodeRadius;
////                    double y = center_Y + layerRadius * Math.sin(angle) - nodeRadius;
////                    
////                    nodes.get(i).setX((int) (x + nodeRadius));
////                    nodes.get(i).setY((int) (y + nodeRadius) );
////                    i++;
////                }
////                
////                layerRadius += layerSpacing;
////            }
//            
//            
//            
//            
//            
//            //connecting the nodes by edges
//            for(Map.Entry<Node, ArrayList<Edge>> set : adjacentList.entrySet()){
//                ArrayList<Edge> edges = set.getValue();
//                for(Edge e: edges){
//                    g2d.setColor(Color.BLUE);
//                    g2d.drawLine(set.getKey().getX(), set.getKey().getY(), e.getDest().getX(), e.getDest().getY());
//                }
//            }
//            double layerRadius = innerRadius ;
//            int j = 0;
//            for(int layer = 0; layer < nodesLayer; layer++){
//                double angleStep = 2 * Math.PI/ nodesPerLayer;
//                
//                for(int node = 0; node < nodesPerLayer; node++){
//                    double angle = angleStep * node;
//                    double x = nodes.get(j).getX() - nodeRadius ;
//                    double y = nodes.get(j).getY() - nodeRadius ;
//                  
//                    
//                    Shape nodeCircle = new Ellipse2D.Double(x, y, 2 * nodeRadius, 2 * nodeRadius);
//                    g2d.setColor(Color.red);
//                    g2d.fill(nodeCircle);
//                    
//                    //write the names
//                    Font font = new Font("Arial", Font.BOLD, 12);
//                    g2d.setFont(font);
//                    g2d.setColor(Color.WHITE);
//                    FontMetrics fontMetrics = g2d.getFontMetrics();
//                    int textWidth = fontMetrics.stringWidth( nodes.get(j).getId());
//                    int textHeight = fontMetrics.getHeight();
//                    //int textX = (int) (x - textWidth / 2 + nodeRadius) ;
//                    int textY = (int) (x + textHeight / 2 + nodeRadius);
//                    g2d.drawString(nodes.get(j).getId(), (int) (x + 5), (int) (y + nodeRadius + 7));
//                    
//                    
//                    
//                    j++;
//                }
//                
//                layerRadius += layerSpacing;
//            }
//            repaint();
//        }
//        public void setNodePosition(){
//            double layerRadius = innerRadius;
//            
//           
//            int i = 0;
//            for(int layer = 0; layer < nodesLayer; layer++){
//                double angleStep = 2 * Math.PI/ nodesPerLayer;
//                
//                for(int node = 0; node < nodesPerLayer; node++){
//                    double angle = angleStep * node;
//                    double x = center_X + layerRadius * Math.cos(angle) - nodeRadius;
//                    double y = center_Y + layerRadius * Math.sin(angle) - nodeRadius;
//                    
//                    nodes.get(i).setX((int) (x + nodeRadius));
//                    nodes.get(i).setY((int) (y + nodeRadius) );
//                    i++;
//                }
//                
//                layerRadius += layerSpacing;
//            }
//        }
//        
//        public Node getNodeAtPos(int x, int y){
//            for(Node n: nodes){
//                if(n.getX() < x + nodeRadius && n.getX() > x - nodeRadius && n.getY() < y + nodeRadius && n.getY() > y - nodeRadius ){
//                    return n;
//                }
//            }
//            return null;
//        }
//        
//        //end of slate
//    }
//    
}
