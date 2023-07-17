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
import java.util.*;
public class SortFileData {
    public static void main(String args[]){
        try{
            FileReader fr=new FileReader("/home/coder5/Desktop/sortInput.txt");
            String s="";
            while(true){
                int i=fr.read();
                if(i==-1)    break;
                s+=(char)i;
            }
            fr.close();
            
            String arr[]=s.split(" ");
            s="";
            
            
            Arrays.sort(arr);
            
            for(String str : arr){
                s+=(str+" ");
            }
            
            System.out.println(s);
            FileWriter fw=new FileWriter("/home/coder5/Desktop/sortOutput.txt");
            fw.write(s);
            fw.close();
            
            
        }catch(Exception e){}
    }
}
