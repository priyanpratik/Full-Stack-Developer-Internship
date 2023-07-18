/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.randomaccessfile;

/**
 *
 * @author coder5
 */
import java.io.*;

public class ReadWriteObjects {
    public static void main(String args[]){
        try{
            RandomAccessFile raf=new RandomAccessFile("/home/coder5/Desktop/Read Write in Object.txt","rw");
            raf.seek(500);
            byte[] b=new byte[10];
            raf.read(b);
            for(int i=0;i<10;i++)
                System.out.println((char)b[i]);
            
            raf.seek(500);
            raf.write("abc ".getBytes());
            System.out.println("File written successfully");
            raf.close();
            
            
        }catch(Exception e){System.out.println(e);}
    }
}
