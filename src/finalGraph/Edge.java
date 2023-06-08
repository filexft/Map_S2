/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalGraph;

import graphv1.*;
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

    /**
     * Constructs a new Edge object with the specified destination node, fiab value, distance, and temperature.
     * 
     * @param dest the destination node of the edge
     * @param fiab the fiab value of the edge
     * @param dist the distance of the edge
     * @param temp the temperature of the edge
     */
    public Edge(Node dest, double fiab, int dist, int temp) {
        //this.orig = orig;
        this.dest = dest;
        this.fiab = fiab;
        this.dist = dist;
        this.temp = temp;
    }

    /**
     * Returns the destination node of the edge.
     * 
     * @return the destination node of the edge
     */
    public Node getDest() {
        return dest;
    }

    /**
     * Returns the fiab value of the edge.
     * 
     * @return the fiab value of the edge
     */
    public double getFiab() {
        return fiab;
    }

    /**
     * Returns the distance of the edge.
     * 
     * @return the distance of the edge
     */
    public int getDist() {
        return dist;
    }

    /**
     * Returns the temperature of the edge.
     * 
     * @return the temperature of the edge
     */
    public int getTemp() {
        return temp;
    }

    /**
     * Returns a string representation of the edge.
     * 
     * @return a string representation of the edge
     */
    @Override
    public String toString() {
        return "Edge{" +
                "dest=" + dest +
                ", fiab=" + fiab +
                ", dist=" + dist +
                ", temp=" + temp +
                '}';
    }
    
}
