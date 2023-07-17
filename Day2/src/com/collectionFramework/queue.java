/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collectionFramework;

/**
 *
 * @author coder5
 */
import java.util.*;
public class queue {
    public static void main(String args[]){
        // queue using linked list
        Queue<Integer> q=new LinkedList<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        
        while(!q.isEmpty()){
            System.out.println(q.peek());
            q.remove();
        }
        
        
        // queue using ArrayDeque
        Deque<Integer> p=new ArrayDeque<Integer>();
        p.add(1);
        p.add(2);
        p.addFirst(3);
        p.add(4);
        p.addFirst(5);
        
        while(!p.isEmpty()){
            System.out.println(p.peek());
            p.remove();
        }
        
    }
    
}
