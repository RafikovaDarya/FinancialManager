package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 8989);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            //подключение к серверу
            System.out.println(reader.readLine());

            writer.println(choiceOfProductsJSON());
            System.out.println(reader.readLine());

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


    }

    public static String choiceOfProductsJSON() throws ParseException {

        Scanner scanner = new Scanner(System.in);
        String[] parts = scanner.nextLine().split(" ");

        Date current = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        String date = sdf.format(current);

       String jsonText = "{\"title\": \""+ parts[0] +"\", \"date\": \""+ date+"\", \"sum\": "+parts[1]+"}";

        return jsonText;
    }

}
