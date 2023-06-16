/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphv1;

import GraphFinal.Node;
import sae_graph.*;

/**
 *
 * @author lycee
 */
public class Edge {
    //private Node orig;
    private Node dest;
    
    private double fiab;
    private int dist;
    private int temp;

    public Edge(Node dest, double fiab, int dist, int temp) {
        //this.orig = orig;
        this.dest = dest;
        this.fiab = fiab;
        this.dist = dist;
        this.temp = temp;
    }

    
    
    //---Getter
    /*public Node getOrig() {
        return orig;
    }*/

    public Node getDest() {
        return dest;
    }

    public double getFiab() {
        return fiab;
    }

    public int getDist() {
        return dist;
    }

    public int getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return "Edge{" + "dest=" + dest + ", fiab=" + fiab + ", dist=" + dist + ", temp=" + temp + '}';
    }
    
    
}
