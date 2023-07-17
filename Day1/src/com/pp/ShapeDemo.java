/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pp;

/**
 *
 * @author coder5
 */
public class ShapeDemo {
    public static void main(String args[]){
        
        // Rectangle
        Rectangle r1=new Rectangle(10,5);
        System.out.println("Area of Rectangle is : " + r1.getArea());
        
        Rectangle r2=new Rectangle(10,5);
        System.out.println(r1.equals(r2));
        System.out.println(r1.hashCode());
        System.out.println(r1);
        
        Rectangle r3=new Rectangle(10,6);
        int val=r2.compareTo(r3);
        if(val==1)
            System.out.println("r2 is greater");
        else if(val==-1)
            System.out.println("r3 is greater");
        else
            System.out.println("Both are equal");
        
        
        // Square
        Square s1=new Square(4);
        System.out.println("Area of Square is : " + s1.getArea());
        Square s2=new Square(4);
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode());
        System.out.println(s1);
        
        Square s3=new Square(4);
        int val2=s2.compareTo(s3);
        if(val2==1)
            System.out.println("r2 is greater");
        else if(val2==-1)
            System.out.println("r3 is greater");
        else
            System.out.println("Both are equal");
    }
}
