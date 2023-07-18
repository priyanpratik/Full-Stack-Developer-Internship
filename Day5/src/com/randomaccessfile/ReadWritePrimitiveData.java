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
public class ReadWritePrimitiveData {
    public static void main(String args[]){
        try{
            RandomAccessFile raf=new RandomAccessFile("/home/coder5/Desktop/Read Write in Primitive.txt","rw");
            long l=0;
            
            // Integer value
            int num=123;
            raf.seek(0);
            raf.writeBytes("The integer value is : ");
            l=raf.length();
            raf.writeInt(num);
            raf.seek(l);
            System.out.println(raf.readInt());
            System.out.println("Int value read and write successfully");
            
            // Character value
            char ch='m';
            raf.seek(raf.length());
            raf.writeBytes("\nThe character value is : ");
            l=raf.length();
            raf.writeChar(ch);
            raf.seek(l);
            System.out.println(raf.readChar());
            System.out.println("Char value read and write successfully");
            
            // Boolean value
            boolean b=true;
            raf.seek(raf.length());
            raf.writeBytes("\nThe boolean value is : ");
            l=raf.length();
            raf.writeBoolean(b);
            raf.seek(l);
            System.out.println(raf.readBoolean());
            System.out.println("Boolean value read and write successfully");
            
            
            // Double Value
            double d=123.321;
            raf.seek(raf.length());
            raf.writeBytes("\nThe double value is  : ");
            l=raf.length();
            raf.writeDouble(d);
            raf.seek(l);
            System.out.println(raf.readDouble());
            System.out.println("Double value read and write successfully");
            
            raf.close();
            
        }catch(Exception e){System.out.println(e);}
    }
}
