/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sae_graph;

import java.util.ArrayList;

/**
 *
 * @author lycee
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        Node node = new Node();
        
        node.addEdge("S1", "S2", 8, 7, 9);
        node.addEdge("S2", "S3", 4, 6, 7);
        
        ArrayList<Edge> adjacnode = node.getAdjacNodeObjectList("S1");
        
        for (Edge e: adjacnode){
            System.out.println("value :"  + e.getFiab());
        }
        
    }
    
}
