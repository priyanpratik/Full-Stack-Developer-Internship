/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.first.mavenjson.day11.post;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import static spark.Spark.*;

/**
 *
 * @author coder5
 */
public class JSONBody {
    public static void main(String args[]){
        
        port(33001);
        
        post("/practice", (request, response) -> {
            String reqBody=request.body();
            Object obj=JSONValue.parse(reqBody);
            JSONObject jobj=(JSONObject)obj;
            System.out.println("Name is  : " + jobj.get("Name"));
            System.out.println("Age is  : " + jobj.get("Age"));
            return "Hello";
        });
    }
}
