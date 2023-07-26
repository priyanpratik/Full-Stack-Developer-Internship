/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.json;

import org.json.simple.*;

/**
 *
 * @author coder5
 */
public class SimpleJson {

    public static void main(String args[]) {
        JSONObject obj = new JSONObject();
        obj.put("Fname", "Pratik");
        obj.put("Lname", "Priyan");
        obj.put("Basic", "12000");
        JSONArray arr = new JSONArray();
        arr.add(12);
        arr.add(35);
        arr.add(69);
        obj.put("Data", arr);
        System.out.println(arr.size());
        
//        Employee emp=new Employee("Pratik", 101, 'M');
//        JSONObject empObj = new JSONObject(emp);

        System.out.println(obj);
    }
}
