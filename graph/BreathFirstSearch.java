package graph;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author a
 */
public class BreathFirstSearch {
    public static void main(String[] args){
        Node root = new Node();//Node class in DPS
        Node target = new Node();
        boolean contains;
        
        
        //BFS:
        Set<Node> visited = new HashSet();
        Queue<Node> nextToSearch = new LinkedList<>();
        nextToSearch.add(root);
        
        while(!nextToSearch.isEmpty()){
            Node node = nextToSearch.remove();
            if(node.id == target.id){
                contains = true;
                break;
            }
            
            if(visited.contains(node)){
                continue;//for recusions we break because each node represents the tree of nodes connected to it, but it's just an individual node
            }
            visited.add(node);
            
            for(Node child : node.children){
                nextToSearch.add(child);
            }
        }
        
        contains = false;
    }
}
