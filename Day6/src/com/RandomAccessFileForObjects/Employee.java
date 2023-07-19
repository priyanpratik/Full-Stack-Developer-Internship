/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RandomAccessFileForObjects;

import java.io.*;

/**
 *
 * @author coder5
 */
public class Employee implements Serializable{
    String name;
    int age;
    char gender;

    public Employee(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
