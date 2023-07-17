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
        ExternalIntArray arr=new ExternalIntArray(5);
        arr.put(3, 1234);
        System.out.println(arr.get(1));
        System.out.println(arr.get(3));
        arr.delete();
    }
}
