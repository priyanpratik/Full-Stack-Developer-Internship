/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.first.mavenjson.day11.post;

import org.json.simple.*;
import static spark.Spark.*;

/**
 *
 * @author coder5
 */
public class SimpleCalculator {

    public static void main(String args[]) {
        port(33001);
        System.out.println("Starting Calculator !!!");
        post("/calculator", (request, response) -> {
            String calcBody = request.body();
            JSONObject jobj = (JSONObject) JSONValue.parse(calcBody);
            
            long num1=(long) jobj.get("num1");
            long num2=(long) jobj.get("num2");
            String operator = (String) jobj.get("operator");
            
            if(operator.equals("+"))
                return num1+num2;
            else if(operator.equals("-"))
                return num1-num2;
            else if(operator.equals("*"))
                return num1*num2;
            else if(operator.equals("/"))
                return num1/num2;
            
            halt("Invalid Input");
            return "h";
        });        
        System.out.println("Calculator Started !!!");

    }
}
