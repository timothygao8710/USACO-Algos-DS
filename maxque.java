
import java.util.Deque;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// package Implementations;
/**
 * -Take it slow, think- Watch out for: - Long/Int - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class maxque {
    
    public static void main(String[] args){
        Deque<Integer> dq = new LinkedList();
        dq.add(1);
        dq.add(2);
        System.out.println(dq.getFirst());
    }
    
    //for max sliding window
    public Deque<Integer> dq = new LinkedList();
    public int size = 0;

    public void add(int a) {
        size++;
        while (!dq.isEmpty() && dq.getLast() < a) {
            dq.removeLast();
        }
        dq.addLast(a);
    }

    public void remove(int a) {
        //works if the remove is always the earliest(sliding window)
        size--;

        if (dq.getFirst() == a) {
            dq.removeFirst();
        }
    }

    public int peek() {
        //returns largest
        return dq.getFirst();
    }

    public void print() {
        for (int i : dq) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
