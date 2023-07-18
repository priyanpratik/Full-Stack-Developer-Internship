/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randomaccessfile;

/**
 *
 * @author coder5
 */

public class ExternalIntArrayDemo {
    public static void main(String args[]){
        String path="/home/coder5/Desktop/ExternalArray.txt";
        ExternalIntArray arr=new ExternalIntArray(10,path);
        
        arr.put(0,23);
        arr.put(3,4);
        arr.append(12);
        arr.put(1,66);
        arr.append(77);
        arr.put(7,33);
        arr.append(11);
        arr.put(9,122);
        
        arr.append(222);
        arr.put(10, 1000);
        arr.put(-1,889);
        
        arr.forceAppend(555);
        arr.put(10,1000);
        arr.forceAppend(99999);
        
        
        for(int i=0;i<arr.size;i++){
            System.out.print(arr.get(i)+" ");
        }
        System.out.println();
        
        arr.closeXArray();
    }
}
