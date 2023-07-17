package com.collectionFramework;

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

class comp implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        if(o1>o2)
            return 1;
        else if(o1<o2)
            return -1;
        else
            return 0;
    }
    
}

public class CompFunction {
    public static void main(String args[]){
        ArrayList<Integer> arr=new ArrayList<Integer>();
        arr.add(3);
        arr.add(1);
        arr.add(5);
        arr.add(2);
        arr.add(0);
        Collections.sort(arr,new comp());
        System.out.println(arr);
        
    }
}
