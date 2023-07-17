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
import java.util.Scanner;

public class UserInput {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter name : ");
        String name=sc.nextLine();
        System.out.println("Enter marks : ");
        int marks=sc.nextInt();
        
        
        System.out.println("Marks obtained by " + name + " is " + marks);
    }
}
