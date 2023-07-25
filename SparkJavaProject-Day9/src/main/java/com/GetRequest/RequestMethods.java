/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GetRequest;

import spark.QueryParamsMap;
import static spark.Spark.get;
import static spark.Spark.port;

/**
 *
 * @author coder5
 */
public class RequestMethods {
    public static void main(String args[]){
        port(33001);
        
        System.out.println("Staring Server");
        
//        body()
//        get("/practice", (request, response) -> {
//            return request.body();
//        });


//        host()
//        get("/practice", (request, response) -> {
//            return request.host();
//        });

        
//        queryMap()
//        get("/practice", (request, response) -> {
//            QueryParamsMap queryParamMap=request.queryMap();
//            String name=queryParamMap.get("name").value();
//            String id=queryParamMap.get("id").value();
//            return name + " " + id;
//        });


//        ip()
//        get("/practice", (request, response) -> {
//            return request.ip();
//        });


//        port()
//        get("/practice", (request, response) -> {
//            return request.port();
//        });


//        uri()
//        get("/practice", (request, response) -> {
//            return request.uri();
//        });


//        url()
//        get("/practice", (request, response) -> {
//            return request.url();
//        });

//        userAgent()
        get("/practice", (request, response) -> {
            return request.userAgent();
        });


        
        System.out.println("Server Started");
    }
}
