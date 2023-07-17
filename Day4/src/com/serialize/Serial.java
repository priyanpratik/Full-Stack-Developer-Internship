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

import java.io.*;


public class Serial {
    public static void main(String args[]){
        Employee emp1=new Employee("Amit", 101, 'M', 25);
        
        try{
            FileOutputStream file=new FileOutputStream("/home/coder5/Desktop/file.txt");
            ObjectOutputStream out=new ObjectOutputStream(file);
            out.writeObject(emp1);
            out.close();
            file.close();
            System.out.println("Object has been serialized");
        }catch(Exception e){}
    }
}
