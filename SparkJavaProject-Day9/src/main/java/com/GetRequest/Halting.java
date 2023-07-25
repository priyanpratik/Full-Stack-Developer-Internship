/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GetRequest;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;

/**
 *
 * @author coder5
 */
public class Halting {
    public static void main(String args[]){
        port(33001);
        
        System.out.println("Starting...");
        
        get("/calc", (request, response) -> {
            int result=0;
            try{
                int num1=Integer.parseInt(request.queryParams("num1"));
                int num2=Integer.parseInt(request.queryParams("num2"));
                String operator=request.queryParams("operator");
            
            
                if(operator.equals("+"))          result=num1+num2;
                if(operator.equals("-"))          result=num1-num2;
                if(operator.equals("*"))          result=num1*num2;
                if(operator.equals("/"))          result=num1/num2;
            }catch(Exception e){
                halt("Invalid Input");
            }
            
            return result;
        });
    }
}
