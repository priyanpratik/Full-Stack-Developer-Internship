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

public class HashVSLinkedHashVSTreeMap {
    public static void main(String args[]){
        HashMap<Integer,String> mp1=new HashMap<Integer,String>();
        mp1.put(12, "ABC");
        mp1.put(64, "DEF");
        mp1.put(29, "XYZ");
        mp1.put(67, "PQR");
        System.out.println("HashMap : ");
        System.out.println(mp1);
        
        LinkedHashMap<Integer,String> mp2=new LinkedHashMap<Integer,String>();
        mp2.put(12, "ABC");
        mp2.put(64, "DEF");
        mp2.put(29, "XYZ");
        mp2.put(67, "PQR");
        System.out.println("LinkedHashMap : ");
        System.out.println(mp2);
        
        TreeMap<Integer,String> mp3=new TreeMap<Integer,String>();
        mp3.put(12, "ABC");
        mp3.put(64, "DEF");
        mp3.put(29, "XYZ");
        mp3.put(67, "PQR");
        System.out.println("TreeMap : ");
        System.out.println(mp3);
    }
}
