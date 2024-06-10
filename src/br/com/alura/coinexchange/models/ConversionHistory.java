package br.com.alura.coinexchange.models;

import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {

    private List<String> coins = new ArrayList<>();

    public String getCoins() {

        if (coins.size() > 0) {

            int coinHistoricNumber = 0;
            String message = "";

            for (String coin : coins) {
                coinHistoricNumber++;
                message += """
                ***************************************
                %d :
                %s
                
                """.formatted(coinHistoricNumber, coin);
            }

            return message;
        } else {
            System.out.println("Não há histórico");
            return null;
        }
    }

    public void addCoins(String coins) {
        this.coins.add(coins);
    }

}
