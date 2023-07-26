/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.json;

import java.io.*;

public class Employee implements Serializable {

    String name;
    int empid;
    char gender;

    public Employee(String name, int empid, char gender) {
        this.name = name;
        this.empid = empid;
        this.gender = gender;
    }
}
