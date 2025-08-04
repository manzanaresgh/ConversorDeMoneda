package com.alura.pixesoft.principal;

import com.google.gson.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Conversor {
    public double buscarMoneda(int menu, Double valor) {
        double moneda = 0;
        String monOrigen = "";
        switch(menu){
            case 1,3,5:
                monOrigen = "USD";
                break;
            case 2:
                monOrigen = "ARS";
                break;
            case 4:
                monOrigen = "BRL";
                break;
            case 6:
                monOrigen = "CLP";
                break;
        }

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/a3f520380a048ed4ad70e5fd/latest/"+monOrigen);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //System.out.println(response.body());
        String json = response.body();

        // Parsear JSON
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();

        // Extraer solo el objeto conversion_rates
        JsonObject conversionRates = root.getAsJsonObject("conversion_rates");

        // Convertir a Map<String, Double>
        Gson gson = new Gson();
        Type type = new com.google.gson.reflect.TypeToken<Map<String, Double>>(){}.getType();
        Map<String, Double> tasas = gson.fromJson(conversionRates, type);

        switch(menu){
            case 1:
                moneda = valor * tasas.get("ARS");
                break;
            case 2:
                moneda = valor * tasas.get("USD");
                break;
            case 3:
                moneda = valor * tasas.get("BRL");
                break;
            case 4:
                moneda = valor * tasas.get("USD");
                break;
            case 5:
                moneda = valor * tasas.get("CLP");
                break;
            case 6:
                moneda = valor * tasas.get("USD");
                break;
            default:
                moneda = 0;
        }
        return moneda;
    }
}
