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
public class hashTable {
    public static void main(String args[]){
        Hashtable<Integer,String> ht=new Hashtable<Integer,String>();
        ht.put(100,"abc");
        ht.put(101,"def");
        ht.put(102,"ghj");
        ht.put(103,"xyz");
        
        //System.out.println(ht);
        ht.forEach((key,value)->
                System.out.println(key + " " + value)
        );
        
        ht.remove(101);
        System.out.println();
        
        ht.forEach((key,value)->
                System.out.println(key + " " + value)
        );
    }
}
