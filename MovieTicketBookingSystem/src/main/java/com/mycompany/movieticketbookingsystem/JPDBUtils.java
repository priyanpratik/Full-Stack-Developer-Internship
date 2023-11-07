/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.movieticketbookingsystem;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author DELL
 */
public class JPDBUtils {

    static String createPutRequest(String connToken, String dbName, String relName, JSONObject jsonObj) {
        String putRequest = "{\n"
                + "\"token\" : \""
                + connToken
                + "\","
                + "\"dbName\": \""
                + dbName
                + "\",\n" + "\"cmd\" : \"PUT\",\n"
                + "\"rel\" : \""
                + relName + "\","
                + "\"jsonStr\": \n"
                + jsonObj
                + "\n"
                + "}";

        return putRequest;
    }

    static String createGetByKeyRequest(String connToken, String dbName, String relName, JSONObject jsonObj) {
        String getByKeyRequest = "{\n"
                + "\"token\" : \""
                + connToken
                + "\",\n" + "\"cmd\" : \"GET_BY_KEY\",\n"
                + "\"dbName\": \""
                + dbName
                + "\",\n"
                + "\"rel\" : \""
                + relName
                + "\",\n"
                + "\"jsonStr\":\n"
                + jsonObj
                + "\n"
                + "}";
        return getByKeyRequest;
    }

    static String executeCommand(String putRequest, String endPointUrl) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create("http://api.login2explore.com:5577/" + endPointUrl))
                .POST(HttpRequest.BodyPublishers.ofString(putRequest))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
