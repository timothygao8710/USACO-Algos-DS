package graph;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a
 */
public class DepthFirstSearch {
    //Depth-first search
    
    public static void main(String[] args){
    }
    
    static boolean DFS(Node destination, Node root, Set<Node> visited){
        if(root.id == destination.id){
            return true;
        }
        
        if(visited.contains(root)){
            return false; //we know that it has been visited but this function is still running
        }
        visited.add(root);
        
        for(Node child : root.children){
            if(
            DFS(destination, child, visited)){
                return true;
            }
        }
        return false;
    }
}


class Node{
    public int id;
    public Set<Node> children;
}
