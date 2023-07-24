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
public class SimpleCalc {
    public static void main(String args[]){
        port(33001);
        System.out.println("Loading Simple Calcultor");
        
        get("/?name=john&age=20", (request, response) -> {
            int num1=Integer.parseInt(request.splat()[0]);
            String ch=request.splat()[1];
            int num2=Integer.parseInt(request.splat()[2]);
            int result=0;
            if(ch.equals("*")){
                result=num1*num2;
            }
            if(ch.equals("-")){
                result=num1-num2;
            }
            if(ch.equals("+")){
                result=num1+num2;
            }
            if(ch.equals("/")){
                result=num1/num2;
            }
            
            return "Result : "+result;
        });


        
        System.out.println("Simple Calcultor Loaded");
    }
}
