/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multipleObjects;

/**
 *
 * @author coder5
 */

import java.io.*;
public class SerializeObjects {
    public static byte[] convertToByteArray(Employee emp){
        byte[] b1={};
        try{
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream out=new ObjectOutputStream(bos);
            out.writeObject(emp);
            b1=bos.toByteArray();
        }catch(Exception e){}
        return b1;
    }
    
    public static void writeIndexFile(ExternalArray arr, int index, int start, int size){
        try{
            arr.put(2*index, start);
            arr.put(2*index+1, size);
        }catch(Exception e){}
    }
    
    public static void writeObjectFile(RandomAccessFile raf, int size, byte[] b){
        try{
            raf.seek(size);
            raf.write(b);
        }catch(Exception e){}
    }
    
    public static int serialize(Employee emp, ExternalArray arr, int ind, int size){
        try{
            RandomAccessFile raf=new RandomAccessFile("/home/coder5/Desktop/objects.txt","rw");
            byte[] b1=convertToByteArray(emp);
            writeIndexFile(arr, ind, size, b1.length);
            writeObjectFile(raf,size,b1);
            size+=b1.length;
            raf.close();
        }catch(Exception e){}
        return size;
    }
    
    public static void main(String args[]){
        ExternalArray arr=new ExternalArray(2*5, "/home/coder5/Desktop/index.txt");
        Employee emp1=new Employee("Amit",35,'M');
        Employee emp2=new Employee("Riya",32,'F');
        Employee emp3=new Employee("Pratik",30,'M');
        
        try{
            int size=0;
            size=serialize(emp1, arr, 0, size);
            size=serialize(emp2, arr, 3, size);
            size=serialize(emp3, arr, 4, size); 

            System.out.println("All objects are serialized");
        }catch(Exception e){ System.out.println(e.getMessage()); }
        
        arr.closeXArray();
    }
}
