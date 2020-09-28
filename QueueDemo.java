/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementations;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author a
 */
public class QueueDemo {
    public static void main(String[] args){
        /*
        FIFO
        Key methods:
        1. Offer
        2. Poll
        3. Peek
        */
        
        Queue curr = new LinkedList();
        curr.offer(1);
        curr.offer(2);
        curr.offer(3);
        
        System.out.println(curr.toString());
        System.out.println("peek looks at lastest value: " + curr.peek());
        System.out.println(curr.toString());
        System.out.println("Offer adds valu: " + curr.offer(69));
        System.out.println(curr.toString());
        System.out.println("Polll looks at and removes value " + curr.poll());
        System.out.println(curr.toString());
    }
}
