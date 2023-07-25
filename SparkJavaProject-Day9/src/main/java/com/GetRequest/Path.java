/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GetRequest;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.get;
import static spark.Spark.port;

/**
 *
 * @author coder5
 */
public class Path {
    public static void main(String args[]){
        port(33001);
        
        System.out.println("Starting...");
        
        path("api/", () ->{
            get("/sayhello", (request,response) ->{
               return "hello";
            });
            
            get("/greet/:name", (request, response) -> {
                return "Hello " + request.params(":name");
            });
        });
        
        
        
        System.out.println("Started...");
    }
}
