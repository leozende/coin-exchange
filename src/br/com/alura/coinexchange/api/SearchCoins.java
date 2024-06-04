package br.com.alura.coinexchange.api;

import br.com.alura.coinexchange.models.ConversionRates;
import br.com.alura.coinexchange.models.RatesAPI;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class SearchCoins {

    public ConversionRates requestCoin(String coin) {

        String acessKeyAPI = "8e336b6a3cafc92658bdfa77";

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try {
            URI adress = URI.create("https://v6.exchangerate-api.com/v6/" + acessKeyAPI +"/latest/" + coin);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(adress)
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            RatesAPI ratesAPI = gson.fromJson(json, RatesAPI.class);

            if (ratesAPI.result().equals("error")){
                throw new RuntimeException("Moeda Inválida");
            } else {
                return ratesAPI.conversion_rates();
            }


        } catch (IOException e) {
            System.out.println("Ocorreu um erro de IO: " + e);
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("Ocorreu um erro de interrupção: " + e);
            throw new RuntimeException(e);
        }

    }

    public void valueConverter(ConversionRates coin,int quantity) {

        String formattedCoins = """
                "ARS" = %.2f
                "BOB" = %.2f
                "BRL" = %.2f
                "CLP" = %.2f
                "COP" = %.2f
                "USD" = %.2f
                
                """.formatted(coin.ARS() * quantity, coin.BOB() * quantity,coin.BRL() * quantity, coin.CLP() * quantity, coin.COP() * quantity, coin.USD() * quantity);

        System.out.println(formattedCoins);

    }

}
