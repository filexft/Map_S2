/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package graphv1;

import GraphFinal.Screen0;
import GraphFinal.Graph;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import sae_graph.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lycee
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        FlatLightLaf.setup();
        Graph graph = new Graph();
       
        //graph.fillGraph();
        //graph.printTwoNodeWithType(graph.getNodeByString("S3"), graph.getNodeByString("S2"), "M");
       //graph.printGraph();
//        System.out.println("time dijkstra ");
//        graph.printShortByDistanceOrTime(graph.getNodeByString("S1"), graph.getNodeByString("S17"), "t");
//        System.out.println("fiablity dijkstra ");
//        graph.printShortByFiability(graph.getNodeByString("S1"), graph.getNodeByString("S17"));
//        //graph.ShortestPathFiability(graph.getNodeByString("S1"));
        
        //graph.fillGraph(null, null);
        Screen0 g = new Screen0(graph);
        //boolean fileFound = graph.fillGraph(g.getFile1(), g.getFile2());
        //File f1 = g.getFile1();
        //System.out.println("file :" + f1.getAbsolutePath());
        //g.setFileStatus(fileFound);
        //graph.fillGraph(null, null);
        //graph.printShortByDistanceOrTime(graph.getNodeByString("S1"), graph.getNodeByString("S17"), "t");
        g.setVisible(true);
    }
    
}
