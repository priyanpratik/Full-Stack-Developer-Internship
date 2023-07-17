/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collection;

/**
 *
 * @author coder5
 */
import java.util.*;

public class ArrayVSLinked {
    
    public static void main(String args[]){
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        System.out.println(arr);
        System.out.println(arr.get(1));
        arr.set(0,5);
        System.out.println(arr);
        arr.add(1,6);
        System.out.println(arr);
        arr.remove(2);
        System.out.println(arr);
        
        LinkedList<Integer> list=new LinkedList<Integer>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println(list);
        System.out.println(list.get(1));
        list.set(0,50);
        System.out.println(list);
        list.add(1,60);
        System.out.println(list);
        list.removeFirstOccurrence(20);
        System.out.println(list);
    }
}
