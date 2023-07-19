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

public class RAFObjDeSerial {
    public static void main(String args[]){
        try{
            RandomAccessFile raf=new RandomAccessFile("/home/coder5/Desktop/file.txt","r");
            raf.seek(0);
            byte[] arr=new byte[(int)raf.length()];
            raf.read(arr);
            ByteArrayInputStream bis=new ByteArrayInputStream(arr);
            ObjectInputStream in=new ObjectInputStream(bis);
            Employee emp1=(Employee)in.readObject();
           
            in.close();
            raf.close();
            System.out.println("Object has been Deserialized");
            System.out.println("Employee name : "+emp1.name);
            System.out.println("Employee age : "+emp1.age);
            System.out.println("Employee gender : "+emp1.gender);
        }catch(Exception e){    System.out.println(e);     }
    }
}
