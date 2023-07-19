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
public class RAFMultipleObjDeserial {
    public static void main(String args[]){
        try{
            
            RandomAccessFile file=new RandomAccessFile("/home/coder5/Desktop/MultipleObjects.txt","rw");
            file.seek(0);
            byte[] byteArray=new byte[(int)file.length()];
            file.read(byteArray);
            ByteArrayInputStream bis=new ByteArrayInputStream(byteArray);
            ObjectInputStream in=new ObjectInputStream(bis);
            
            ArrayList<Employee> arr=new ArrayList<>();
            arr=(ArrayList<Employee>) in.readObject();
            
            System.out.println("Displaying Information of First Employee");
            System.out.println("Name = " + arr.get(0).name);
            System.out.println("Age = " + arr.get(0).age);
            System.out.println("Gender = " + arr.get(0).gender);
            
            
            System.out.println("Displaying Information of Second Employee");
            System.out.println("Name = " + arr.get(1).name);
            System.out.println("Age = " + arr.get(1).age);
            System.out.println("Gender = " + arr.get(1).gender);
            
        }catch(Exception e){}
    }
}
