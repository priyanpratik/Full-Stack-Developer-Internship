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
public class Printwriter {
    public static void main(String args[]){
        try{
            PrintWriter pw=new PrintWriter("/home/coder5/Desktop/xyz.txt");
            pw.println("Hello");
            pw.println("My name is Pratik");
            pw.printf("My age is %d",22);
            pw.close();
        }catch(Exception e){}
    }
}
