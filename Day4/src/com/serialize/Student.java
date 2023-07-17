/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serialize;

import java.io.Serializable;

/**
 *
 * @author coder5
 */
public class Student implements  Serializable{
    String name;
    int empid;
    char gender;
    transient int age;

    public Student(String name, int empid, char gender, int age) {
        this.name = name;
        this.empid = empid;
        this.gender = gender;
        this.age = age;
    }
}
