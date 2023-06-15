/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphv1;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lycee
 */

public final class Visual extends JPanel{
        
        private static int center_X;
        private static int center_Y;
        private static int innerRadius = 100;
        private static int layerSpacing = 70;
        private static int nodesLayer;
        private static int nodesPerLayer;
        private double nodeRadius = 15;
        
        private int panelWidth;
        private int panelHeight;
        
        private Node selectedNode;
        private Node selectedEndNode;
        private boolean isSelected = false;
        
        private Screen0 frame;
        
        private Graph graph;
        private ArrayList<Node> nodes; //= new ArrayList<Node>();
        private HashMap<Node, ArrayList<Edge>> adjacentList; //= new HashMap<Node, ArrayList<Edge>>();
        
        private ArrayList<Node> path;
        
        
        public Visual(JPanel visualPanel, Graph graph, Screen0 fram){
            this.setSize(visualPanel.getWidth(), visualPanel.getHeight());
            this.setPreferredSize(new Dimension(visualPanel.getWidth(), visualPanel.getHeight()));
            
            
            panelWidth = visualPanel.getWidth();
            panelHeight = visualPanel.getHeight();
            
            frame = fram;
            this.graph = graph;
            nodes = graph.getNodeList();
            adjacentList = graph.getAdjacentList();
            
            center_X = visualPanel.getWidth()/2;
            center_Y = visualPanel.getHeight()/2;
           
            nodesLayer = (int) Math.ceil(graph.getNodeList().size()/8);
            nodesPerLayer = graph.getNodeList().size()/nodesLayer;
            //init nodes pos
            setNodePosition();
            /**
             * adding event listener to make it draggable
             */
            this.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent e) {
                    //System.out.println("Presingg");
                    selectedNode = getNodeAtPos(e.getX(), e.getY());  
                    if(selectedNode != null){
                        graph.initialColor();
                        selectedNode.setColor(Color.RED);
                        selectedNode.setColor(Color.BLUE);
                        path =  graph.printDirectConnectedNode(selectedNode);
                    }else{
                        graph.initialColor();
                        path = null;
                    }
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    //System.out.println("clicking");
                    //graph.initialColor();
                    
                    if(selectedNode != null){
//                        graph.initialColor();
//                        selectedNode.setColor(Color.RED);
//                        graph.printDirectConnectedNode(selectedNode);
                          
                    }
                    selectedNode = getNodeAtPos(e.getX(), e.getY());
                    if(selectedNode == null){
                        for(Node n: nodes){
                            for(Edge line: adjacentList.get(n)){
                                double distance = Line2D.ptSegDist​(n.getX(), n.getY(), 
                                       line.getDest().getX(), line.getDest().getY(),
                                       e.getX(), e.getY());

                                if (distance < 2) {
                                    // success!
                                     n.setColor(Color.blue);
                                    line.getDest().setColor(Color.blue);
                                    Integer node1 = Integer.valueOf(n.getId().split("S")[1]);
                                    Integer node2 = Integer.valueOf(line.getDest().getId().split("S")[1]);
                                    frame.setSpinnerVal(node1, node2);
                    
                                    System.out.println("line entre " + n.getId() + " et "  +line.getDest().getId());
                                }
                            }
                        }
                        return;
                    }
                    System.out.println(selectedNode.getId());
                    selectedNode.setColor(Color.BLUE);
                    System.out.println(selectedNode.getColor());
                    repaint();
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
            });
            
            this.addMouseMotionListener(new MouseMotionListener(){
                @Override
                public void mouseDragged(MouseEvent e) {
                    //
                     if(selectedNode == null) return;
                    
                    if(e.getX() <= 0 )
                    {
                        selectedNode.setX((int) nodeRadius + 5);
                    }else if (e.getX() >= panelWidth){
                        selectedNode.setX((int) (panelWidth - nodeRadius));
                    }
                    else if(e.getY() <= 0 )
                    {
                        selectedNode.setY((int) nodeRadius + 5);
                    }else if (e.getY() >= panelHeight){
                        selectedNode.setY((int) (panelHeight - nodeRadius));
                    }else{
                        selectedNode.setX(e.getX());
                        selectedNode.setY(e.getY());
                        repaint();
                        revalidate();
                    }
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                   
                }
                
            });
            this.setVisible(true);
        } 
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.red);
            
            
            if(path == null){
                //connecting the nodes by edges
                for(Map.Entry<Node, ArrayList<Edge>> set : adjacentList.entrySet()){
                    ArrayList<Edge> edges = set.getValue();
                    for(Edge e: edges){
                        g2d.setColor(Color.red);
                        g2d.setStroke(new BasicStroke(0.5f));
                        g2d.drawLine(set.getKey().getX(), set.getKey().getY(), e.getDest().getX(), e.getDest().getY());
                    }
                }
                double layerRadius = innerRadius ;
                int j = 0;
                for(int layer = 0; layer < nodesLayer; layer++){
                    double angleStep = 2 * Math.PI/ nodesPerLayer;

                    for(int node = 0; node < nodesPerLayer; node++){
                        double angle = angleStep * node;
                        double x = nodes.get(j).getX() - nodeRadius ;
                        double y = nodes.get(j).getY() - nodeRadius ;


                        Shape nodeCircle = new Ellipse2D.Double(x, y, 2 * nodeRadius, 2 * nodeRadius);
                        g2d.setColor(nodes.get(j).getColor());

                        
                        g2d.fill(nodeCircle);

                        //write the names
                        Font font = new Font("Arial", Font.BOLD, 12);
                        g2d.setFont(font);
                        g2d.setColor(Color.WHITE);
                        FontMetrics fontMetrics = g2d.getFontMetrics();
                        int textWidth = fontMetrics.stringWidth( nodes.get(j).getId());
                        int textHeight = fontMetrics.getHeight();
                        //int textX = (int) (x - textWidth / 2 + nodeRadius) ;
                        int textY = (int) (x + textHeight / 2 + nodeRadius);
                        g2d.drawString(nodes.get(j).getId(), (int) (x + 5), (int) (y + nodeRadius + 7));



                        j++;
                    }

                    layerRadius += layerSpacing;
                }
            }else{
                 //connecting the nodes by edges
                for(Map.Entry<Node, ArrayList<Edge>> set : adjacentList.entrySet()){
                    ArrayList<Edge> edges = set.getValue();
                    for(Edge e: edges){
                        //allows us to set the opacity using the setComposite() 
                        //method. The AlphaComposite class is used to define the transparency level (opacity). 
//                      
//                        if(path.contains(e.getDest()) && path.contains(set.getKey()) || path.get(0).equals(e.getDest())){
//                            g2d.setColor(Color.blue);
//                            g2d.drawLine(set.getKey().getX(), set.getKey().getY(), e.getDest().getX(), e.getDest().getY());
//                    
//                        }else{
//                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
//                            g2d.setStroke(new BasicStroke(0.5f));
//                            g2d.setColor(Color.red);
//                            g2d.drawLine(set.getKey().getX(), set.getKey().getY(), e.getDest().getX(), e.getDest().getY());
//                    
//                        }

                        g2d.setColor(Color.blue);
                        g2d.drawLine(set.getKey().getX(), set.getKey().getY(), e.getDest().getX(), e.getDest().getY());
                    
                    }
                }
                double layerRadius = innerRadius ;
                int j = 0;
                for(int layer = 0; layer < nodesLayer; layer++){
                    double angleStep = 2 * Math.PI/ nodesPerLayer;

                    for(int node = 0; node < nodesPerLayer; node++){
                        double angle = angleStep * node;
                        double x = nodes.get(j).getX() - nodeRadius ;
                        double y = nodes.get(j).getY() - nodeRadius ;


                        Shape nodeCircle = new Ellipse2D.Double(x, y, 2 * nodeRadius, 2 * nodeRadius);
                        g2d.setColor(nodes.get(j).getColor());

                        //allows us to set the opacity using the setComposite() 
                        //method. The AlphaComposite class is used to define the transparency level (opacity).
                        if(path.contains(nodes.get(j))){
                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
                        }else{
                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                            
                        }
                        
                        g2d.fill(nodeCircle);

                        //write the names
                        Font font = new Font("Arial", Font.BOLD, 12);
                        g2d.setFont(font);
                        g2d.setColor(Color.WHITE);
                        FontMetrics fontMetrics = g2d.getFontMetrics();
                        int textWidth = fontMetrics.stringWidth( nodes.get(j).getId());
                        int textHeight = fontMetrics.getHeight();
                        //int textX = (int) (x - textWidth / 2 + nodeRadius) ;
                        int textY = (int) (x + textHeight / 2 + nodeRadius);
                        g2d.drawString(nodes.get(j).getId(), (int) (x + 5), (int) (y + nodeRadius + 7));



                        j++;
                    }

                    layerRadius += layerSpacing;
                }
            }
            repaint();
        }
        
        public void setNodePosition(){
            double layerRadius = innerRadius;
            
           
            int i = 0;
            for(int layer = 0; layer < nodesLayer; layer++){
                double angleStep = 2 * Math.PI/ nodesPerLayer;
                
                for(int node = 0; node < nodesPerLayer; node++){
                    double angle = angleStep * node;
                    double x = center_X + layerRadius * Math.cos(angle) - nodeRadius;
                    double y = center_Y + layerRadius * Math.sin(angle) - nodeRadius;
                    
                    nodes.get(i).setX((int) (x + nodeRadius));
                    nodes.get(i).setY((int) (y + nodeRadius) );
                    i++;
                }
                
                layerRadius += layerSpacing;
            }
        }
        
        public Node getNodeAtPos(int x, int y){
            for(Node n: nodes){
                if(n.getX() < x + nodeRadius && n.getX() > x - nodeRadius && n.getY() < y + nodeRadius && n.getY() > y - nodeRadius ){
                    return n;
                }
            }
            return null;
        }
        
        
     
        //end of slate

    public String EdgeConnectedNode(int x, int y){
        for(Node n: nodes){
            for(Edge line: adjacentList.get(n)){
                double distance = Line2D.ptSegDist​(n.getX(), n.getY(),
                        line.getDest().getX(), line.getDest().getY(),
                        x, y);
                if (distance < 2) {
                    // success!                      
                    n.setColor(Color.yellow);
                    line.getDest().setColor(Color.yellow);
                    System.out.println("line entre " + n.getId() + " et "  + line.getDest().getId());
                    this.repaint();
                    return (n.getId() + ";"  +line.getDest().getId());
                }
            }
        }
        return "";
    }    
    
    public Node getSelectedNode() {
        return selectedNode;
    }
    }
    
