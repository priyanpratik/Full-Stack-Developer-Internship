/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stream;

/**
 *
 * @author coder5
 */
import java.io.*;
public class WrittingFile {
    public static void main(String args[]){
        System.out.println("Using Byte Stream");
        try{
            FileOutputStream fi=new FileOutputStream("/home/coder5/Desktop/def.txt");
            byte content[]="Hello world".getBytes();
            fi.write(content);
            fi.close();
            System.out.println("File Written Successfully");
        }catch(Exception e){}
        
        
        
        System.out.println("Using Character Stream");
        try{
            FileWriter f=new FileWriter("/home/coder5/Desktop/abc.txt");
            f.write("Hello World");
            f.close();
            System.out.println("File Written Successfully");
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
}
