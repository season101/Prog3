import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * RoadTrip breadth-first search
 */
public class RoadTrip {
    private Graph map = new Graph();
    private ArrayList<String> states = new ArrayList<String>();

    private Graph neighbours = new Graph();

    public RoadTrip (String filename){
        try {
            Scanner in = new Scanner(new File(filename));
            

            while(in.hasNextLine()){
                String[] line = in.nextLine().split(" ");
                if(!states.contains(line[0])){
                    states.add(line[0]);
                    map.addVertex(states.indexOf(line[0]));
                }
                if(!states.contains(line[1])){
                    states.add(line[1]);
                    map.addVertex(states.indexOf(line[1]));
                }
                map.addEdge(states.indexOf(line[0]), states.indexOf(line[1]));
           }
           System.out.println(map);
        } 

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String generatePath(int sou, int dest){
        String path = "";
        Iterator<Integer> it = map.getAdjacent(dest);
        boolean flag = false;
        while(!flag){
            int temp = it.next();
            if(temp == dest)
             {path+=dest;
                flag = true;
             }
        }
        return path;
    }

    public void breadthFirstSearch(String sou, String dest){
        
        Integer source =  states.indexOf(sou);
        Integer destination =  states.indexOf(dest);
       
        ArrayList<Integer> visited = new ArrayList<Integer>();
        
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        
        Integer presentNode = source;        
  
        bfsQueue.add(source);
        boolean reached = false;

        

        while(!bfsQueue.isEmpty() && !reached){
            presentNode = bfsQueue.remove();
            System.out.print(states.get(presentNode)+" ");
            

            Iterator<Integer> it = map.getAdjacent(presentNode);
            visited.add(presentNode);
            neighbours.addVertex(presentNode);
            while(it.hasNext()){                            
                Integer child = (Integer) it.next();   
                if(!visited.contains(child)){                                    
                    bfsQueue.add(child); 
                    visited.add(child);          
                    neighbours.addVertex(child);
                    neighbours.addEdge(presentNode, child);       
                }   
                          
            }
            System.out.print(presentNode+"->");

            if(presentNode == destination) reached = true;
            
        }
        
    }

    public String getPath(String source, String destination){
        breadthFirstSearch(source, destination);
        //return generatePath(6, 9) ;
        System.out.println("\n");
        return neighbours.toString();
    }

}