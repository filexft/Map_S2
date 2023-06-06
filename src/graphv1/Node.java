/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphv1;

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

    public Node(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }    

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    @Override
    public String toString() {
        return  id;
    }

//    @Override
//    public int compare(Node o1, Node o2) {
//        return Integer.parseInt(o1.getId().split("S")[1]) - Integer.parseInt(o2.getId().split("S")[1]);
//    }
//    
     @Override 
     public int compare(Node n1, Node n2) {
         int na = Integer.parseInt(n1.getId().split("S")[1]);
         int nb = Integer.parseInt(n2.getId().split("S")[1]);
         return na - nb;
     }

}
