/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RandomAccessFileForObjects;

/**
 *
 * @author coder5
 */
import java.io.*;
public class RAFObjSerial {
    public static void main(String args[]){
        Employee emp1=new Employee("Amit",25,'M');
        
        try{
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream out=new ObjectOutputStream(bos);
            out.writeObject(emp1);
            out.close();
            byte[] arr=bos.toByteArray();
            bos.close();
            
            RandomAccessFile raf=new RandomAccessFile("/home/coder5/Desktop/file.txt","rw");
            raf.seek(0);
            raf.write(arr);
            System.out.println("Object has been serialized");
        }catch(Exception e){}
    }
}
