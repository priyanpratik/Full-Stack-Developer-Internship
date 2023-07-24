/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.first.myfirstsparkjavaproject.day8;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 *
 * @author coder5
 */
public class RequestMethods {
    public static void main(String args[]){
        port(33001);
        System.out.println("Starting Request Methods");
        
//        get("/hello", (request, response) -> {
//            return request.attributes();
//        });

//        get("/hello", (request, response) -> {
//            return request.cookies();
//        });
        
//        get("/hello", (request, response) -> {
//            return request.headers("Connection");
//        });

//        get("/hello", (request, response) -> {
//            return request.host();
//        });
        
//        get("/hello", (request, response) -> {
//            return request.ip();
//        });

//        get("/hello", (request, response) -> {
//            return request.protocol();
//        });

//        get("/hello", (request, response) -> {
//            return request.uri();
//        });    
         
        
        get("/hello?id=1", (request, response) -> {
            return "Hello";
        });
        
        
        System.out.println("Started Request Methods");
    }
}
