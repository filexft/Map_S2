/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphv1;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import sae_graph.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;

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
    
    public void fillGraph(String file1, String file2){
        //initialise the nodelist and adjacent list to not add another time when you import a file multiple times
        listNode = new ArrayList<Node>();
        adjacentList = new HashMap<Node, ArrayList<Edge>>();
        
        File listAdjac;
        File listSucc;
        if(file1 == null || file2 == null){
            listAdjac = new File("liste-adjacence-jeuEssai.csv");
            listSucc = new File("liste-successeurs.csv");
            System.out.println("default files");
        }else{
            listAdjac = new File(file1);
            listSucc = new File(file2);
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
                                
                                double fiab = Double.parseDouble(cellData[0])/10;
                                int dist = Integer.parseInt(cellData[1].trim());
                                int temp = Integer.parseInt(cellData[2].trim());
                                
                                addEdge(sourceNode, destNode, fiab, dist, temp);
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
        return Double.NEGATIVE_INFINITY;
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
        distances.put(s, 1.0);
        
       
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
                double calcVal = getFiabiliteBetweenTwoNodes(currentNode, adjacentNode) * distances.get(currentNode);
                if(distances.get(adjacentNode) < calcVal){
                                    distances.put(adjacentNode, calcVal);
                                    
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
    //F15 Donner le chemin le plus court en distance et en durée, entre 2 sites.
    public ArrayList<Node> printShortByDistanceOrTime(Node source, Node end, String type){
        HashMap<Node, Node> prev = ShortestPathByDistanceAndTime(source, type);
        
        ArrayList<Node> path = new ArrayList<Node>();
        ArrayList<Node> reversedPath;
        Node current = end;
        
        
        while(prev.get(current) != null){
            current.setColor(Color.blue);
            path.add(current);
            current = prev.get(current);
        }
        end.setColor(Color.black);
        source.setColor(Color.yellow);
        //path.add(prev.get(source));
        path.add(source);
        reversedPath = reverseArrayList(path);
        int sum = 0;
        for(int i = 0; i < path.size()-1; i++){
            sum += getDistanceOrTimeBetweenTwoNodes(path.get(i), path.get(i+1), type);
        }
        System.out.println(reversedPath);
//        System.out.println("dist test : " + getDistanceOrTimeBetweenTwoNodes(path.get(0), path.get(1), type));
        System.out.println("the sum is : " + sum + " and the path - 2 index is : "+ path.get(path.size()-1));
        return reversedPath;
        
    }
    //F13 Étant donné 2 nœuds quelconques du graphe, définir le chemin le plus fiable 
    public ArrayList<Node> printShortByFiability(Node source, Node end){
        
        HashMap<Node, Node> prev = ShortestPathFiability(source);
        
        ArrayList<Node> pathFiab = new ArrayList<Node>();
        
        ArrayList<Node> reversedPathFiab;
        
        Node current = end;
        
        //pathFiab.add(end);
        end.setColor(Color.yellow);
        while(prev.get(current) != null){
            current.setColor(Color.yellow);
            
            pathFiab.add(current);
            current = prev.get(current);
        }
        end.setColor(Color.black);
        source.setColor(Color.yellow);
        pathFiab.add(source);
        reversedPathFiab = reverseArrayList(pathFiab);
        double sum = 1.0;
        for(int i = 0; i < pathFiab.size()-1; i++){
            System.out.println("fiab :" + getFiabiliteBetweenTwoNodes(pathFiab.get(i), pathFiab.get(i+1)));
            sum *= getFiabiliteBetweenTwoNodes(pathFiab.get(i), pathFiab.get(i+1));
        }
        System.out.println("the sum is : " + sum );
        System.out.println(reversedPathFiab);
        return reversedPathFiab;
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
     * F4. Modifier les caractéristiques du graphe par saisie à l’écran ;
     */
    public boolean changeGraph(Node source, Node end, int type, String val){
        if(source == null || end == null  || val == null) return false;
        if(source.equals(end)) return false;
        if(Double.valueOf(val) < 0) return false;
        for(Edge ed : getAdjacentEdgeList(source)){
            if(ed.getDest().equals(end)){
                        Predicate<Edge> pr= (Edge e) ->(e.getDest().equals(ed.getDest()));  
                        Predicate<Edge> pr2 = (Edge e) ->(e.getDest().equals(source));  
                        
                        Edge n;
                        switch(type){
                            case 0:
                                if(Double.valueOf(val) > 10){
                                    return false;
                                }else if (Double.valueOf(val) == 0){
                                    getAdjacentEdgeList(source).removeIf(pr);
                                    getAdjacentEdgeList(end).removeIf(pr2);
                                    return true;
                                }else{
                                    n = new Edge( ed.getDest(),Double.valueOf(val),ed.getDist(), ed.getTemp()); 
                                    getAdjacentEdgeList(source).removeIf(pr);
                                    getAdjacentEdgeList(end).removeIf(pr2);
                                    getAdjacentEdgeList(end).add(n);
                                    return true;
                                }
                            case 1:
                                n = new Edge( ed.getDest(), ed.getFiab(), Integer.parseInt(val), ed.getTemp()); 
                                getAdjacentEdgeList(source).removeIf(pr);
                                getAdjacentEdgeList(end).removeIf(pr2);
                                
                                getAdjacentEdgeList(source).add(n);
                                getAdjacentEdgeList(end).add(n);
                                return true;
                            case 2:
                                n = new Edge( ed.getDest(), ed.getFiab(), ed.getDist(), Integer.parseInt(val)); 
                                getAdjacentEdgeList(source).removeIf(pr);
                                getAdjacentEdgeList(end).removeIf(pr2);
                                
                                getAdjacentEdgeList(source).add(n);
                                getAdjacentEdgeList(source).add(n);
                                return true;
                        }
                    }
            System.out.println("change graph");
        }
        return false;
    }
   
    /**
     * F5
     * Lister tous les nœuds (regroupés par catégories) ou un type donné de nœud, et leur nombre par type
     */
    public String printNodesByType(String type){
        
        String typeName[] = {"nutrition", "bloc operatoires", "maternite"};
        String types[] = {"N", "O", "M"};
        String returnString = "";
        int numPerType = 0;
        int typenum = 0;
        
       
        if (type != null){
             switch(type){
            case "N" -> typenum = 0;
            case "O" -> typenum = 1;
            case "M" -> typenum = 2;
            }
            returnString += "Noeuds regroupes par " + typeName[typenum] + "\n";
            for(Node n: listNode){
                    if(n.getType().equals(types[typenum])){
                        System.out.println(n.getId() + " , type :" + n.getType());
                        returnString += n.getId() + " , type :" + n.getType() + "\n";
                        numPerType++;
                }
            }
        }else {
            for(int i = 0; i < types.length; i++){
                numPerType = 0;
                System.out.println("Noeuds regroupes par " + typeName[i]);
                returnString += "Noeuds regroupes par " + typeName[i] + "\n";
                for(Node n: listNode){
                    if(n.getType().equals(types[i])){
                        System.out.println(n.getId() + " , type :" + n.getType());
                        returnString += n.getId() + " , type :" + n.getType() + "\n";
                        numPerType++;
                    }
                }
                System.out.println("\n\n");
                returnString += "il y a " + numPerType + " Noeuds regroupes par " + typeName[i];
                returnString += "\n\n";
            }
        }
        return returnString;
    }
    
    /**
     * F6. 
     * Lister toutes les arêtes et donner leur nombre ;
     */
    public String printEdgeAndNumber(){
        String returnString = "";
        int edgeNumber = 0;
        for(Map.Entry<Node, ArrayList<Edge>> pair: adjacentList.entrySet()){
            for(Edge edge: pair.getValue()){
                //System.out.println(pair.getKey().getId() + " -- " + edge.getDest().getId() );
                returnString += pair.getKey().getId() + " -- " +  edge.getFiab() + ", " + edge.getDist() + ", " + edge.getTemp()+ " --- " + edge.getDest().getId() + "\n";
                edgeNumber++;
            }
            //System.out.println("\n");
            returnString += "\n";
        }
        //System.out.println("le nombre des aretes est :" + edgeNumber);
        returnString += "le nombre des aretes est :" + edgeNumber;
        return returnString;
    }
    
    
    /**
     * F7.Pour un sommet donné, lister les voisins directs (nœuds à 1-distance) ;
     * @param n
     */
    public ArrayList<Node> printDirectConnectedNode(Node n){
        if(n == null) return null;
        ArrayList<Node> returnList = new ArrayList<>();
        returnList.add(n);
        for (Edge e : adjacentList.get(n)){
            e.getDest().setColor(Color.BLACK);
            returnList.add(e.getDest());
            //System.out.println(n.getId() +" est voisins directs a :" + e.getDest().getId());
        }
        
        return returnList;
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
    public String printDirectConnectedNodeByType(Node n, String type){
        int ConnectedNodeNumber = 0;
        String typeName[] = {"nutrition", "bloc operatoires", "maternite"};
        int typenum = 0;
        
        String returnStr = ""; 
        switch(type){
            case "N" -> typenum = 0;
            case "O" -> typenum = 1;
            case "M" -> typenum = 2;
        }
        for (Edge e : adjacentList.get(n)){
            if(e.getDest().getType().equals(type)){
                
                System.out.println(n.getId() +" est voisins directs a :" + e.getDest().getId() +" et le type est :" + typeName[typenum]);
                
                returnStr += n.getId() +" est voisins directs a ->  " + e.getDest().getId() +" et le type est :" + typeName[typenum] + "\n";
                ConnectedNodeNumber++;
            }
        }
        if(ConnectedNodeNumber == 0){
            System.out.println("il y a pas un voisin de type " + typeName[typenum]);
            returnStr += "\nil y a pas un voisin de type " + typeName[typenum] + "\n";
        }else{
            System.out.println("\nil y a " + ConnectedNodeNumber +  "  voisin de type " + typeName[typenum] + "\n");
            returnStr += "\nil y a " + ConnectedNodeNumber +  "  voisin de type " + typeName[typenum] + "\n";
        }
        return returnStr;
    }
    
    /**
     * F11. Étant donné 2 nœuds, dire s’ils sont à 2-distance ou pas ;
     */
    public String printNodeAreConnectedAt2Dist(Node source, Node end){
        String returnString = "";
        if(!source.equals(end))
        {
            for(Edge edgeA : adjacentList.get(source)){
                for(Edge edgeB : adjacentList.get(edgeA.getDest())){
                        if(edgeB.getDest().equals(end)){
                            System.out.println(source.getId() + " est a 2 distance de " + end.getId());
                            returnString += source.getId() + " est a 2 distance de " + end.getId();
                            return returnString;
                        }
                }
            }
        }
        System.out.println(source.getId() + " n'est pas  a 2 distance de " + end.getId());
        returnString += source.getId() + " n'est pas  a 2 distance de " + end.getId();
        return returnString;
    }
    
    /**
     * F11. voisinage à p sauts et chemins (p-distance)
     */
    
    public List<Node> getNodesAtNDistanceByType(Node source, int distance, int type) {
    
    String types[] = {"N", "O", "M"};
    String choosenType;
    if(type == -1) {
        choosenType = "all";
    }else{
        choosenType = types[type];
    }
    String returnString = "";
   
    List<Node> result = new ArrayList<>();
    Set<Node> visited = new HashSet<>();
    Queue<Node> queue = new LinkedList<>();
    Map<Node, Integer> nodeDistanceMap = new HashMap<>();

    queue.offer(source);
    nodeDistanceMap.put(source, 0);
    visited.add(source);

    while (!queue.isEmpty()) {
        Node current = queue.poll();
        int currentDistance = nodeDistanceMap.get(current);

        if (currentDistance == distance) {
            if(choosenType.equals("all")){
                result.add(current);
            }else{
                if(current.getType().equals(choosenType)){
                    result.add(current);
                }
            }
            
            continue;
        }

        if (currentDistance > distance) {
            break; // We have reached beyond the desired distance
        }

        for (Edge edge : adjacentList.get(current)) {
            Node neighbor = edge.getDest();
            if (!visited.contains(neighbor)) {
                queue.offer(neighbor);
                nodeDistanceMap.put(neighbor, currentDistance + 1);
                visited.add(neighbor);
            }
        }
    }
    System.out.println(result.toString());
    return result;
}
    
//    public String printNodeAreConnectedAtNDist(Node source, int dist, String type){
//        String returnString = "";
//        for(Edge edgeA : adjacentList.get(source)){
//            
//            
////                for(Edge edgeB : adjacentList.get(edgeA.getDest())){
//////                        if(edgeB.getDest().equals(end)){
//////                            System.out.println(source.getId() + " est a 2 distance de " + end.getId());
//////                            returnString += source.getId() + " est a 2 distance de " + end.getId();
//////                            return returnString;
//////                        }
////                }
//        }
//        if(dist == 0){
//            
//        }
//    }
//    public String printNodeAreConnectedAtNDist(Node source, int n){
//        String returnString = "";
//        if(n == 0){
//            return source.getId();
//        }
//        else{
//            
//        }
//        for(Edge edgeA : adjacentList.get(source)){
//            for (int i = 0; i < n; i++){
//                for(Edge edgeB : adjacentList.get(edgeA.getDest())){
//                        if(edgeB.getDest().equals(end)){
//                            System.out.println(source.getId() + " est a 2 distance de " + end.getId());
//                            returnString += source.getId() + " est a 2 distance de " + end.getId();
//                            return returnString;
//                        }
//                }
//            }
//        }
//        System.out.println(source.getId() + " n'est pas  a 2 distance de " + end.getId());
//        returnString += source.getId() + " n'est pas  a 2 distance de " + end.getId();
//        return returnString;
//    }
    
    
    /**
     * F12. Comparer 2 villes, sur le critère Opératoire (nb de Blocs opératoires à plus de 2-distance), 
     *  le critère Maternité (nb de maternités à plus de 2-distance) ou Nutritionnel (nb de centres
     *  de nutrition à plus de 2-distance) ; 
     */
    public String compare2ville(Node n1, Node n2){
        return "";
    }
    
    /**
     * F10. Pour 2 sommets donnés, lister les sommets voisins d’un type donné des centres S1 et S2 
     *(ex. : liste des blocs opératoires en voisins directs de S1 et S2) ; 
     */
    public String printTwoNodeWithType(Node n, Node m, String type){
        String returnString = "";
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
                returnString += n.getId() + " et " + m.getId() + " est connecte avec " + e.getDest().getId() + " et son type est " + typeName[typenum] + "\n";
            }
        }
        if(returnString.isBlank()){
            returnString += "il y a pas un lien avec ce type!";
        }
        return returnString;
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
