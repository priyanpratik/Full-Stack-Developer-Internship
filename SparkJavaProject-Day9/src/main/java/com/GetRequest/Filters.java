/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GetRequest;

import static spark.Spark.*;

/**
 *
 * @author coder5
 */
public class Filters {
    public static void main(String args[]){
        port(33001);
        
        System.out.println("Starting...");
        
        before((request, response) -> {
            System.out.println("Before block executed");
        });
        
        after((request, response) -> {
            System.out.println("After block executed");
        });
        
        get("/demo1", (request, response) -> {
           return "Demo 1"; 
        });
        
        get("/demo2", (request, response) -> {
           return "Demo 2"; 
        });
    }
}
