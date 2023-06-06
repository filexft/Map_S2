/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lycee
 */
public class Node {
    
    
    private ArrayList<String> listNode;
    private HashMap<String, ArrayList<Edge>> adjacList;
    
    public Node(){
        listNode = new ArrayList<String>();
        adjacList = new HashMap<String, ArrayList<Edge>>();
    }
    
    public boolean addEdge(String orig, String dest, double fiab, int dist, int temp){
        if(!listNode.contains(orig)){
            ArrayList<Edge> tempList = new ArrayList<Edge>();
            tempList.add(new Edge(dest, fiab, dist, temp));
            adjacList.put(orig, tempList);
            listNode.add(orig);
            return true;
        }
        if(!listNode.contains(dest)){
            ArrayList<Edge> tempList = new ArrayList<Edge>();
            tempList.add(new Edge(orig, fiab, dist, temp));
            adjacList.put(dest, tempList);
            listNode.add(dest);
            return true;
        }
        adjacList.get(orig).add(new Edge(dest, fiab, dist, temp));
        adjacList.get(dest).add(new Edge(orig, fiab, dist, temp));
        return true;
    }
    
    public ArrayList<String> getNodeList(String node){
        return listNode;
    }
    public ArrayList<String> getAdjacNodeStringList(String node){
        ArrayList<String> returnList = new ArrayList<String>();
        for (Edge e : adjacList.get(node)){
            returnList.add(e.getDest());
        }
        return returnList;
    }
    
    public ArrayList<Edge> getAdjacNodeObjectList(String node){
        return adjacList.get(node);
    }

    @Override
    public String toString() {
        return "Node{" + "listNode=" + listNode + ", adjacList=" + adjacList + '}';
    }
    
    
    
}
