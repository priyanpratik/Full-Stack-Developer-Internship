/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serialize;

/**
 *
 * @author coder5
 */
import java.io.*;

public class Employee implements Serializable {

    String name;
    int empid;
    char gender;
    transient int age;
    int a;

    public Employee(String name, int empid, char gender, int age) {
        this.name = name;
        this.empid = empid;
        this.gender = gender;
        this.age = age;
    }
}
