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
import java.util.*;
import java.io.*;
public class RAFMultipleObjSerial {
    public static void main(String args[]){
        Employee emp1=new Employee("Amit",35,'M');
        Employee emp2=new Employee("Riya",32,'F');
        
        ArrayList<Employee> arr=new ArrayList<>();
        try{
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream out=new ObjectOutputStream(bos);
            arr.add(emp1);
            arr.add(emp2);
            out.writeObject(arr);
            out.close();
            byte[] byteArray=bos.toByteArray();
            bos.close();
            
            RandomAccessFile file=new RandomAccessFile("/home/coder5/Desktop/MultipleObjects.txt","rw");
            file.seek(0);
            file.write(byteArray);
            file.close();
        }catch(Exception e){}
    }
}
