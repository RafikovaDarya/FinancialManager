package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class Main {
    public static File tsvFile = new File("categories.tsv");


    public static void main(String[] args) {
        //TSV file
        setTsvFile();

        //Server
        try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
            FinancialManager financialManager = new FinancialManager();
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    // обработка одного подключения
                    System.out.println("Подключен клиент" + socket.getPort());
                    out.println("Это менеджер финансов. Введите наименование покупки и сумму");

                    //читаем переданный JSON с покупкой

                    financialManager.addClientChoice(in);
                    out.println("{\"maxCategory\": " + financialManager.fromTsvMaxCategories(new File("categories.tsv")) + " }");

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }


    }

    public static void setTsvFile() {
        ProductCategories productCategories = new ProductCategories();
        if (!tsvFile.exists()) {
            List<ProductCategories> list = productCategories.addListOfProducts();
            try (Writer writer = new FileWriter(tsvFile)) {
                for (ProductCategories pc : list) {
                    writer.write(pc.getProductName() + " " + pc.getCategoriesOfProduct() + "\n");
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
