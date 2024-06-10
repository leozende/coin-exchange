package br.com.alura.coinexchange.main;

import br.com.alura.coinexchange.api.SearchCoins;
import br.com.alura.coinexchange.models.ConversionHistory;
import br.com.alura.coinexchange.models.ConversionRates;
import br.com.alura.coinexchange.models.RatesAPI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        SearchCoins searchCoins = new SearchCoins();
        ConversionHistory historic = new ConversionHistory();
        String request = "";
        String ESCAPE = "SAIR";

            String initialMessage = """
                    **************************************************************
                    Seja bem-vindo/a ao Conversor de Moeda =]
                    **************************************************************
                    """;
            System.out.println(initialMessage);

        while (!request.equals(ESCAPE)) {

            System.out.println("Escolha uma moeda escrevendo sua sigla: (Exemplo: USD = Dólar)");
            System.out.println("Se desejar ver o histórico, digite 'historico'.");
            System.out.println("Digite 'sair' para deixar o programa.");

            request = scan.nextLine().toUpperCase();
            if (request.equals(ESCAPE)) break;
            if (request.equals("HISTORICO")) {
                System.out.println(historic.getCoins());
            } else {
            try {
                SearchCoins search = new SearchCoins();
                ConversionRates coins = search.requestCoin(request);
                System.out.println("Qual a quantidade desejada para a conversão?");
                int quantity = scan.nextInt();
                scan.nextLine();
                String coinValue = search.valueConverter(coins, quantity);
                System.out.println(coinValue);
                historic.addCoins(coinValue);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            }


        }
    }
}
