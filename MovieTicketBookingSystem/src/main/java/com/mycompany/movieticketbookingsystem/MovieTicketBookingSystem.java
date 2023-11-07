/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.movieticketbookingsystem;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static spark.Spark.*;

/**
 *
 * @author DELL
 */
public class MovieTicketBookingSystem {

    public static void main(String[] args) {
        staticFiles.location("/public");
        
        String connToken="90933146|-31949318775558192|90951378";
        String dbName="Movie Ticket Booking Database";
        String loginRelation="User Login";
        
        post("/login", (request, response) -> {
            String requestBody=request.body();
            System.out.println(requestBody);
            
            JSONParser parser=new JSONParser();
            JSONObject emailObj=(JSONObject)parser.parse(requestBody);
            String getByKeyRequest=JPDBUtils.createGetByKeyRequest(connToken, dbName, loginRelation, emailObj);
            String resposeBody=JPDBUtils.executeCommand(getByKeyRequest, "/api/irl");
            System.out.println(resposeBody);
            
            JSONObject responseMsg=(JSONObject)parser.parse(resposeBody);
            return responseMsg;
        });
        
        post("/user-exists", (request, response) -> {
            String requestBody=request.body();
            System.out.println(requestBody);
            
            JSONParser parser=new JSONParser();
            JSONObject emailObj=(JSONObject)parser.parse(requestBody);
            String getByKeyRequest=JPDBUtils.createGetByKeyRequest(connToken, dbName, loginRelation, emailObj);
            String resposeBody=JPDBUtils.executeCommand(getByKeyRequest, "/api/irl");
            System.out.println("check response" + resposeBody);
            
            JSONObject responseMsg=(JSONObject)parser.parse(resposeBody);
            return responseMsg.get("status");
        });
        
        post("/register", (request, response) -> {
            String requestBody=request.body();
            System.out.println(requestBody);
            
            JSONParser parser=new JSONParser();
            JSONObject loginCredentials=(JSONObject)parser.parse(requestBody);
            String putRequest=JPDBUtils.createPutRequest(connToken, dbName, loginRelation, loginCredentials);
            String resposeBody=JPDBUtils.executeCommand(putRequest, "/api/iml");
            
            JSONObject responseMsg=(JSONObject)parser.parse(resposeBody);
            return responseMsg.get("status");
        });
    }
}
