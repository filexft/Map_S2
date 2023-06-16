/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphv1;

import GraphFinal.Edge;
import GraphFinal.Node;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import sae_graph.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author lycee
 */
public class Graph {
    
    
    private ArrayList<Node> listNode;
    private HashMap<Node, ArrayList<Edge>> adjacentList;
    
    public Graph(){
        listNode = new ArrayList<Node>();
        adjacentList = new HashMap<Node, ArrayList<Edge>>();
    }
    
    public boolean addNode(String id, String type){
        Node node = new Node(id, type);
        listNode.add(node);
        adjacentList.put(node,new ArrayList<Edge>());
        return true;
    }
    public boolean addEdge(Node orig, Node dest, double fiab, int dist, int temp){
        if(!listNode.contains(orig)){
            ArrayList<Edge> tempList = new ArrayList<Edge>();
            tempList.add(new Edge(dest, fiab, dist, temp));
            adjacentList.put(orig, tempList);
            listNode.add(orig);
            return true;
        }
        /**
         * trying remove the destination part becauese it add 2 time the adjact nodes
         */
        /*if(!listNode.contains(dest)){
            ArrayList<Edge> tempList = new ArrayList<Edge>();
            tempList.add(new Edge(orig, fiab, dist, temp));
            adjacList.put(dest, tempList);
            listNode.add(dest);
            return true;
        }*/
        adjacentList.get(orig).add(new Edge(dest, fiab, dist, temp));
        //adjacList.get(dest).add(new Edge(orig, fiab, dist, temp));
        return true;
    }
    
    public ArrayList<Node> getNodeList(){
        return listNode;
    }
    
    public HashMap<Node, ArrayList<Edge>> getAdjacentList(){
        return adjacentList;
    }
    
    public ArrayList<Node> getAdjacentNodeList(Node node){
        ArrayList<Node> returnList = new ArrayList<>();
        for (Edge e : adjacentList.get(node)){
            returnList.add(e.getDest());
        }
        return returnList;
    }
    
    public ArrayList<Edge> getAdjacentEdgeList(Node node){
        return adjacentList.get(node);
    }
    
    public void fillGraph(File file1, File file2){
        File listAdjac;
        File listSucc;
        if(file1 == null || file2 == null){
            listAdjac = new File("liste-adjacence-jeuEssai.csv");
            listSucc = new File("liste-successeurs.csv");
            System.out.println("default files");
        }else{
            listAdjac = file1;
            listSucc = file2;
            System.out.println("deposed file");
        }
        try{
            
            Scanner scAdj = new Scanner(listAdjac);
            Scanner scSucc = new Scanner(listSucc);
            
            String[] adjacSeparatedLine;
            String[] succSeparatedLine;
            
            String succLine;
            String adjLine;
            
            /*
            ArrayList<String> succLineList = new ArrayList<String>();
            while (scSucc.hasNext()) {
                System.out.println(scSucc.nextLine());
                succLineList.add(scSucc.nextLine());
            }
            */
            ArrayList<String> adjLineList = new ArrayList<String>();
            while (scAdj.hasNext()) {
              adjLineList.add(scAdj.nextLine());
            }
            
            
            boolean start = false;
            
            //add all nodes
            for(String adline: adjLineList){
                adjLine = adline;
                if(adjLine.startsWith("S1"))
                {
                    start = true;
                }
                if(start){
                    adjacSeparatedLine = adjLine.split(";");
                    addNode(adjacSeparatedLine[0], adjacSeparatedLine[1]);
                }
            }
            
            start = false;
            for(String adline: adjLineList){
                
                
                adjLine = adline;
                if(adjLine.startsWith("S1"))
                {
                    start = true;
                }
                if(start){
                        //2nd line take a line from a succ file and copy from index 1 till end
                        succLine = scSucc.nextLine();
                        succSeparatedLine = Arrays.copyOfRange(succLine.split(";"), 1, succLine.split(";").length);


                        /**
                         * change the String array of int into into array of int and sort them
                         */
                        int[] myIntArray = new int[succSeparatedLine.length];

                        for (int i = 0; i < succSeparatedLine.length; i++) {
                            myIntArray[i] = Integer.parseInt(succSeparatedLine[i]);
                        }
                        sortStringArray(myIntArray);


                        adjacSeparatedLine = Arrays.copyOfRange(adjLine.split(";"), 2, adjLine.split(";").length);
                        int col = 0;
                        for (String cell: adjacSeparatedLine){
                            if(!cell.equals("0")){
                                Node sourceNode = getNodeByString(adjLine.split(";")[0]);
                                Node destNode = getNodeByNum(myIntArray[col]);
                                String[] cellData = cell.split(",");
                               
                                /*
                                 * --Use Trim to remove the problem of not reading the int 
                                 * url : https://stackoverflow.com/questions/43535578/why-is-the-integer-parseint-not-working-in-my-java-code
                                 */
                                //System.out.println("Source :" + sourceNode.getId() + " destination :" + destNode.getId() +  "  fiab  " + cellData[0] + " dist:" + cellData[1] + " temp: " + cellData[2]);
                                //System.out.println("\n");
                                
                                double fiab = Double.parseDouble(cellData[0]);
                                int dist = Integer.parseInt(cellData[1].trim());
                                int temp = Integer.parseInt(cellData[2].trim());
                                
                                addEdge(sourceNode, destNode, 10, 10,10);
                                col++;
                            }
                        }
                    }
                }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public Node getNodeByNum(int num){
        for(Node n: listNode){
            if(n.getId().equals("S" + num)){
                return n;
            }
        }
        return null;
    }
    
    public Node getNodeByString(String str){
        for(Node n: listNode){
            if(n.getId().equals(str)){
                return n;
            }
        }
        return null;
    }
    
    private void sortStringArray(int[] str){
        int size = str.length;
        for (int i = 0; i < size - 1; i++){
            for (int j = i+1; j < size ; j++){
                if(str[i] > str[j]){
                    int tmp = str[i];
                    str[i] = str[j];
                    str[j] = tmp;
                }
            }
        }
    }

    public void printGraph(){
        TreeMap<Node, ArrayList<Edge>> sortedList = new TreeMap<Node, ArrayList<Edge>>(
            new Comparator<Node>() {
            @Override public int compare(Node n1, Node n2) {
                /**
                 * get the num from the node name ex : "S12" -> 12
                 */
                int na = Integer.parseInt(n1.getId().split("S")[1]);
                int nb = Integer.parseInt(n2.getId().split("S")[1]);
            return na - nb; //n1.getId().split("S") // Acending.
            // or  p2.getAge() - p1.getAge(); // Descending.
            }}
        );
        sortedList.putAll(adjacentList);
        for(Map.Entry<Node, ArrayList<Edge>> set: sortedList.entrySet()){
            String out = ""+ set.getKey().getId() +"(" + set.getKey().getType() + ")";
            for(Edge e: set.getValue()){
                out += " -> " + e.getDest().getId();
            }
            System.out.println(out);
        }
    }
    
    public void printNodeList(){
        for(Node n: listNode){
            System.out.println(n.getId());
        }
    }
    
    @Override
    public String toString() {
        return "Graph{" + "listNode=" + listNode.toString() + ", adjacList=" + adjacentList.toString() + '}';
    }
   
    public double getFiabiliteBetweenTwoNodes(Node source, Node end){
        for(Edge edge: getAdjacentEdgeList(source)){
            if(edge.getDest() == end){
                return edge.getFiab();
            }
        }
        return Double.POSITIVE_INFINITY;
    }
    
    public int getDistanceOrTimeBetweenTwoNodes(Node source, Node end, String type){
        
        if(type.equals("t")){
             for(Edge edge: getAdjacentEdgeList(source)){
                if(edge.getDest() == end){
                    return edge.getTemp();
                }
            }
        }else if(type.equals("d")){
            for(Edge edge: getAdjacentEdgeList(source)){
                if(edge.getDest() == end){
                    return edge.getDist();
                }
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public HashMap<Node, Node> ShortestPathByDistanceAndTime(Node s, String type){
        ArrayList<Node> queue = new ArrayList<Node>();
        ArrayList<Node> visited = new ArrayList<Node>();
        HashMap<Node, Integer> distances = new HashMap<Node, Integer>();
        
        HashMap<Node, Node> prev = new HashMap<Node, Node>();
        
        queue.add(0,s);
        distances.put(s, 0);
        
       
        while(!queue.isEmpty()){
            Node currentNode = queue.remove(queue.size() - 1);
            
           
            if (distances.get(currentNode) == null) {
                    distances.put(currentNode, Integer.MAX_VALUE);
            }
            for(Node adjacentNode : getAdjacentNodeList(currentNode)){
                if(distances.get(adjacentNode) == null) {
                        distances.put(adjacentNode, Integer.MAX_VALUE);
                }
                if(true){
                    if(distances.get(adjacentNode) > getDistanceOrTimeBetweenTwoNodes(currentNode, adjacentNode, type) + distances.get(currentNode) ){
                                    distances.put(adjacentNode, getDistanceOrTimeBetweenTwoNodes(currentNode, adjacentNode, type) + distances.get(currentNode));
                                    //System.out.println(adjacentNode.getId() + " - " + currentNode);
                                    prev.put(adjacentNode, currentNode);
                    }
                }
                if(!visited.contains(adjacentNode) && !queue.contains(adjacentNode)){
                    queue.add(adjacentNode);
                }
            }
            visited.add(currentNode);
        }
        for (Node n: listNode){
            if(!distances.containsKey(n)){
                distances.put(n, Integer.MAX_VALUE);
            }
        }
        return prev;
    }
    
    /**
     * 
     * @param s
     * @return prev un hashmap des noueds avec leur nodes precedent
     */
    public HashMap<Node, Node> ShortestPathFiability(Node s){
        
        ArrayList<Node> queue = new ArrayList<Node>();
        ArrayList<Node> visited = new ArrayList<Node>();
        HashMap<Node, Double> distances = new HashMap<Node, Double>();
        
        HashMap<Node, Node> prev = new HashMap<Node, Node>();
        
        queue.add(0,s);
        distances.put(s, 100.0);
        
       
        while(!queue.isEmpty()){
            Node currentNode = queue.remove(queue.size() - 1);
            
           
            if (distances.get(currentNode) == null) {
                    distances.put(currentNode, 0.0);
            }
            for(Node adjacentNode : getAdjacentNodeList(currentNode)){
                if(distances.get(adjacentNode) == null) {
                        distances.put(adjacentNode, 0.0);
                }
                /**
                 * fiab 9 : 1/9
                 */
                /*if(true){
                    if(distances.get(adjacentNode) < getFiabiliteBetweenTwoNodes(currentNode, adjacentNode) * distances.get(currentNode)){
                                    distances.put(adjacentNode, getFiabiliteBetweenTwoNodes(currentNode, adjacentNode) * distances.get(currentNode));
                                    
                                    prev.put(adjacentNode, currentNode);
                                    System.out.println(adjacentNode.getId() +" , "+  currentNode.getId());
                    }
                }
                */
                //test another method doesn't seem to Work : add distances.get(adjacentNode) = 
                if(distances.get(adjacentNode) < (1/(getFiabiliteBetweenTwoNodes(currentNode, adjacentNode) * distances.get(currentNode)))){
                                    distances.put(adjacentNode, getFiabiliteBetweenTwoNodes(currentNode, adjacentNode) * distances.get(currentNode));
                                    
                                    prev.put(adjacentNode, currentNode);
                                    //System.out.println(adjacentNode.getId() +" , "+  currentNode.getId());
                    }
                
                //end test method
                if(!visited.contains(adjacentNode) && !queue.contains(adjacentNode)){
                    queue.add(adjacentNode);
                }
            }
            visited.add(currentNode);
        }
        for (Node n: listNode){
            if(!distances.containsKey(n)){
                distances.put(n, 0.0);
            }
        }
        return prev;
    }
    
    public void printShortByDistanceOrTime(Node source, Node end, String type){
        HashMap<Node, Node> prev = ShortestPathByDistanceAndTime(source, type);
        
        ArrayList<Node> path = new ArrayList<Node>();
        ArrayList<Node> reversedPath;
        Node current = end;
        
        while(prev.get(current) != null){
            current.setColor(Color.blue);
            System.out.println(current.getId());
             path.add(current);
             current = prev.get(current);
        }
        end.setColor(Color.black);
        source.setColor(Color.yellow);
        //path.add(prev.get(source));
        path.add(source);
        reversedPath = reverseArrayList(path);
        System.out.println(reversedPath);
        
    }
    
    public void printShortByFiability(Node source, Node end){
        
        HashMap<Node, Node> prev = ShortestPathFiability(source);
        
        ArrayList<Node> pathFiab = new ArrayList<Node>();
        
        ArrayList<Node> reversedPathFiab;
        
        Node current = end;
        
        //pathFiab.add(end);
        end.setColor(Color.black);
        while(!prev.get(current).equals(source)){
            current.setColor(Color.blue);
            if(current.equals(source)){
                current.setColor(Color.yellow);
            }
            
            pathFiab.add(current);
            current = prev.get(current);
        }
        
        pathFiab.add(source);
        reversedPathFiab = reverseArrayList(pathFiab);
        System.out.println(reversedPathFiab);
        
    }
    
    
    public ArrayList<Node> reverseArrayList(ArrayList<Node> alist)
    {
        // Arraylist for storing reversed elements
        ArrayList<Node> revArrayList = new ArrayList<Node>();
        for (int i = alist.size() - 1; i >= 0; i--) {
 
            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }
 
        // Return the reversed arraylist
        return revArrayList;
    }
    
    
   
    /**
     * F5
     * Lister tous les nœuds (regroupés par catégories) ou un type donné de nœud, et leur nombre par type
     */
    public void printNodesByType(){
        String typeName[] = {"nutrition", "bloc operatoires", "maternite"};
        String type[] = {"M", "O", "N"};
        for(int i = 0; i < type.length; i++){
            System.out.println("Noeuds regroupes par " + typeName[i]);
            for(Node n: listNode){
                if(n.getType().equals(type[i])){
                    System.out.println(n.getId() + " , type :" + n.getType());
                }
            }
            System.out.println("\n\n");
        }
    }
    
    /**
     * F6. 
     * Lister toutes les arêtes et donner leur nombre ;
     */
    public void printEdgeAndNumber(){
        int edgeNumber = 0;
        for(Map.Entry<Node, ArrayList<Edge>> pair: adjacentList.entrySet()){
            for(Edge edge: pair.getValue()){
                System.out.println(pair.getKey().getId() + " -- " + edge.getDest().getId() );
                edgeNumber++;
            }
            System.out.println("\n");
        }
        System.out.println("le nombre des aretes est :" + edgeNumber);
    }
    
    
    /**
     * F7.Pour un sommet donné, lister les voisins directs (nœuds à 1-distance) ;
     * @param n
     */
    public void printDirectConnectedNode(Node n){
        for (Edge e : adjacentList.get(n)){
            e.getDest().setColor(Color.BLACK);
            System.out.println(n.getId() +" est voisins directs a :" + e.getDest().getId());
        }
    }
    
    public void initialColor(){
        for(Node n: listNode){
            n.setColor(Color.RED);
        }
    }
    /**
     * F9.Pour un sommet donné, lister les voisins directs d’un type donné (nœuds à 1-distance) ;
     * @param n
     * @param type
     */
    public void printDirectConnectedNodeByType(Node n, String type){
        int ConnectedNodeNumber = 0;
        String typeName[] = {"nutrition", "bloc operatoires", "maternite"};
        int typenum = 0;
        
        switch(type){
            case "N" -> typenum = 0;
            case "0" -> typenum = 1;
            case "M" -> typenum = 2;
        }
        for (Edge e : adjacentList.get(n)){
            if(e.getDest().getType().equals(type)){
                System.out.println(n.getId() +" est voisins directs a :" + e.getDest().getId() +" et le type est :" + typeName[typenum]);
                ConnectedNodeNumber++;
            }
        }
        if(ConnectedNodeNumber == 0){
            System.out.println("il y a pas un voisin de type " + typeName[typenum]);
        }else{
            System.out.println("\nil y a " + ConnectedNodeNumber +  "  voisin de type " + typeName[typenum] + "\n");
        }
    }
    
    /**
     * F11. Étant donné 2 nœuds, dire s’ils sont à 2-distance ou pas ;
     */
    public void printNodeAreConnected(Node source, Node end){
        
        for(Edge edgeA : adjacentList.get(source)){
            for(Edge edgeB : adjacentList.get(edgeA.getDest())){
                    if(edgeB.getDest().equals(end)){
                        System.out.println(source.getId() + " est a 2 distance de " + end.getId());
                        return;
                    }
            }
        }
        System.out.println(source.getId() + " n'est pas  a 2 distance de " + end.getId());
    }
    
    /**
     * F10. Pour 2 sommets donnés, lister les sommets voisins d’un type donné des centres S1 et S2 
     *(ex. : liste des blocs opératoires en voisins directs de S1 et S2) ; 
     */
    public void printTwoNodeWithType(Node n, Node m, String type){
        String typeName[] = {"nutrition", "bloc operatoires", "maternite"};
        int typenum = 0;
        
        ArrayList<Node> firstNodeConnectedNodes = new ArrayList<>();
        
        switch(type){
            case "N" -> typenum = 0;
            case "0" -> typenum = 1;
            case "M" -> typenum = 2;
        }
        for(Edge e: adjacentList.get(n)){
            if(e.getDest().getType().equals(type)){
                firstNodeConnectedNodes.add(e.getDest());
            }
        }
        for(Edge e: adjacentList.get(m)){
            if(e.getDest().getType().equals(type) && firstNodeConnectedNodes.contains(e.getDest())){
                System.out.println(n.getId() + " et " + m.getId() + " est connecte avec " + e.getDest().getId() + " et son type est " + typeName[typenum]);
            }
        }
    }
    
    
     public Node getNodeAtPos(int x, int y){
         int nodeRadius = 15;
         for(Node n: listNode){
            if(n.getX() < x + nodeRadius && n.getX() > x - nodeRadius && n.getY() < y + nodeRadius && n.getY() > y - nodeRadius ){
                return n;
            }
        }
        return null;
    }
    
}
