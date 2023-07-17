package com.collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author coder5
 */
import java.util.*;

public class TreeSetVSHashSet {
    public static void main(String args[]){
        Set<Integer> st1=new HashSet<Integer>();
        st1.add(65);
        st1.add(13);
        st1.add(34);
        st1.add(55);
        st1.add(65);
        System.out.println("HashSet : ");
        for(int i : st1)
            System.out.println(i);
        
        Set<Integer> st2=new TreeSet<Integer>();
        st2.add(65);
        st2.add(13);
        st2.add(34);
        st2.add(55);
        st2.add(65);
        System.out.println("TreeSet : ");
        for(int i : st2)
            System.out.println(i);
    }
}
