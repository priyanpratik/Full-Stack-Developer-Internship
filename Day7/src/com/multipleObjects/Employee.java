/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multipleObjects;

import java.io.Serializable;

/**
 *
 * @author coder5
 */
public class Employee implements Serializable{
    String name;
    int age;
    char gender;

    public Employee(String name, int age, char gender) {
        if(this!=null){
            this.name = name;
            this.age = age;
            this.gender = gender;
        }
    }
    
    public void print(){
        if(this==null){
            System.out.println("No object found!!!");
            return;
        }
        
        System.out.println("Employee name : "+this.name);
        System.out.println("Employee age : "+this.age);
        System.out.println("Employee gender : "+this.gender);
        System.out.println();
    }
}
