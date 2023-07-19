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
public class DeserializeObjects {
    public static Employee deserialize(int ind){
        Employee emp = null;
        try{
            RandomAccessFile rafIndex=new RandomAccessFile("/home/coder5/Desktop/index.txt","rw");
            RandomAccessFile rafObject=new RandomAccessFile("/home/coder5/Desktop/objects.txt","rw");
            rafIndex.seek(8*(2*ind));
            long start=rafIndex.readLong();
            rafIndex.seek(8*(2*ind+1));
            long size=rafIndex.readLong();
            
            if(size==0)    return emp;
            
            rafObject.seek(start);
            byte b[]=new byte[(int)size];
            rafObject.read(b);
            ByteArrayInputStream bis=new ByteArrayInputStream(b);
            ObjectInputStream in=new ObjectInputStream(bis);
            emp=(Employee)in.readObject();
            in.close();
            bis.close();
            rafIndex.close();
            rafObject.close();
        }catch(Exception e){}
        return emp;
    }
    
    public static void print(Employee emp){
        if(emp==null){
            System.out.println("No details present !!!");
            return;
        }
        
        System.out.println("Employee name : "+emp.name);
        System.out.println("Employee age : "+emp.age);
        System.out.println("Employee gender : "+emp.gender);
        System.out.println();
    }
    public static void main(String args[]){
        try{
            Employee emp1=deserialize(0);
            print(emp1);
            Employee emp2=deserialize(3);
            print(emp2);
            Employee emp3=deserialize(4);
            print(emp3);
            Employee emp4=deserialize(1);
            print(emp4);
        }catch(Exception e){}
        
    }
}
