/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalGraph;

import java.awt.Color;
import java.util.Comparator;
import sae_graph.*;

/**
 *
 * @author lycee
 */
public class Node implements Comparator<Node>{
    private String id;
    private String type;
    private int x, y;
    private Color color= Color.red;

    /**
     * Constructs a new Node object with the specified ID and type.
     * 
     * @param id   the ID of the node
     * @param type the type of the node
     */
    public Node(String id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Returns the ID of the node.
     * 
     * @return the ID of the node
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the type of the node.
     * 
     * @return the type of the node
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the x-coordinate of the node.
     * 
     * @return the x-coordinate of the node
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the node.
     * 
     * @return the y-coordinate of the node
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the color of the node.
     * 
     * @return the color of the node
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the x-coordinate of the node.
     * 
     * @param x the x-coordinate of the node
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the node.
     * 
     * @param y the y-coordinate of the node
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the type of the node.
     * 
     * @param type the type of the node
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the color of the node.
     * 
     * @param color the color of the node
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns a string representation of the node.
     * 
     * @return a string representation of the node
     */
    @Override
    public String toString() {
        return id;
    }

    /**
     * Compares two nodes based on their IDs.
     * 
     * @param n1 the first node
     * @param n2 the second node
     * @return a negative integer, zero, or a positive integer as the first node is less than, equal to, or greater than the second node based on their IDs
     */
    @Override
    public int compare(Node n1, Node n2) {
        int na = Integer.parseInt(n1.getId().split("S")[1]);
        int nb = Integer.parseInt(n2.getId().split("S")[1]);
        return na - nb;
    }
}
