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

import java.io.*;
import static spark.Spark.stop;

public class NewClass {
    public static void main(String args[]) throws FileNotFoundException, IOException{
        port(33001);
        System.out.println("Loading API's");
        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });
        System.out.println("API Loaded!!!");
        stop();

    }
    
    
}
