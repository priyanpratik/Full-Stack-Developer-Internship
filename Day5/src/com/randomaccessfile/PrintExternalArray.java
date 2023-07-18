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
import java.io.*;
public class PrintExternalArray {
    public static void main(String args[]){
        try{
            RandomAccessFile arr=new RandomAccessFile("/home/coder5/Desktop/ExternalArray.txt","rw");
            long size=arr.length()/4;
            
            for(int i=0;i<size;i++){
                int loc=4*i;
                arr.seek(loc);
                System.out.print(arr.readInt()+" ");
            }
            System.out.println();
        }catch(Exception e){}
        
    }
}
