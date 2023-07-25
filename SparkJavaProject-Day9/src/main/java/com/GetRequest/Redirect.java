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
public class Redirect {
    public static void main(String args[]){
        
        port(33001);
        System.out.println("Starting...");
        get("/old", (request, response) -> {
            response.redirect("/new");
            return "Old Page";
        });
        
        get("/new", (request, response) -> {
            return "New Page";
        });
        System.out.println("Started...");
    }
}
