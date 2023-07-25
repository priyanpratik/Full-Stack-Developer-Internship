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
public class StaticFiles {
    public static void main(String args[]){
        port(33001);
        staticFiles.location("/public");
        System.out.println("Starting...");
        get("/hello", (request, response) -> {
            return "hello"; 
        });
        System.out.println("Started...");
    }
}
