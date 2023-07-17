/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serialize;

/**
 *
 * @author coder5
 */

import java.io.FileInputStream;
import java.io.*;

public class Deserial {
    public static void main(String args[]){
        try{
            FileInputStream file=new FileInputStream("/home/coder5/Desktop/file.txt");
            ObjectInputStream in=new ObjectInputStream(file);
            Employee emp1=(Employee) in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been Deserialized");
            System.out.println("Employee name : "+emp1.name);
            System.out.println("Employee id : "+emp1.empid);
            System.out.println("Employee gender : "+emp1.gender);
            System.out.println("Employee age : "+emp1.age);
        }catch(Exception e){    System.out.println(e);     }
        
    }
}
