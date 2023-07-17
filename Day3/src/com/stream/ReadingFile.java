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
public class ReadingFile {
    public static void main(String args[]){
        try{
            FileReader f1=new FileReader("/home/coder5/Desktop/abc.txt");
            char[] arr=new char[1000];
            f1.read(arr);
            System.out.println("Reading using Character Array : ");
            System.out.println(arr);
            
            
            FileReader f2=new FileReader("/home/coder5/Desktop/abc.txt");
            System.out.println("Reading character by character : ");
            int i;
            while(true){
                i=f2.read();
                if(i==-1)     break;
                System.out.print((char)i);
            }
            
            f1.close();
            f2.close();
        }
        catch(Exception e){}
    }
}
