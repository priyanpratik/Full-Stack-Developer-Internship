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
public class ExternalIntArray {
    private int size;
    RandomAccessFile file=null;

    public ExternalIntArray(int size) {
        this.size = size;
        try{
            file=new RandomAccessFile("/home/coder5/Desktop/External Array.txt","rw");
            for(int i=0;i<size;i++){
                file.seek(getLoc(i));
                file.writeInt(0);
            }
        }catch(Exception e){    System.out.println(e);    }
        
    }
    
    public void put(int ind, int val){
        try{
            file.seek(getLoc(ind));
            file.writeInt(val);
        }catch(Exception e){    System.out.println("Array Out of Bound");    }
        
    }
    
    public int get(int ind){
        int ans=-1;
        try{
            file.seek(getLoc(ind));
            ans=file.readInt();
        }catch(Exception e){    System.out.println("Array Out of Bound");    }
        return ans;
    }
    
    private int getLoc(int ind){
        return 4*ind;
    }
    
    public void delete(){
        try{
            file.setLength(0);
            file.close();
        }catch(Exception e){    System.out.println(e);    }
    }
    
    
}
