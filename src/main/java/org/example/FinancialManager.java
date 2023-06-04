package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class FinancialManager {

    protected List<Basket> basketList = new ArrayList<>();
    protected Map<String, String> tsvMap = new HashMap<>();


    public void addClientChoice(BufferedReader reader) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        basketList.add(gson.fromJson(reader.readLine(), Basket.class));
        // булка 02.08.2022 200

    }

    public JSONObject fromTsvMaxCategories(File tsv) throws IOException {

        List<String[]> categories = Files.lines(tsv.toPath())
                .map(line -> line.split(" "))
                .collect(Collectors.toList());
        for (String[] s : categories) {
            tsvMap.put(s[0], s[1]);
        }
        int sum;
        Map<String, Integer> sumCategories = new HashMap<>();
        for (Basket basket : basketList) {
            if (tsvMap.containsKey(basket.title)) {
                if (sumCategories.isEmpty()) {
                    sumCategories.put(tsvMap.get(basket.title), basket.sum);
                } else {
                    sum = sumCategories.containsKey(tsvMap.get(basket.title)) ? sumCategories.get(tsvMap.get(basket.title)) : 0;
                    sumCategories.put(tsvMap.get(basket.title), sum + basket.sum);
                }
            }
            String other = "другое";
            if (!tsvMap.containsKey(basket.title)) {
                if (!sumCategories.containsKey(other)) {
                    sumCategories.put(other, basket.sum);
                } else {
                    sum = sumCategories.get(other);
                    sumCategories.put(other, sum + basket.sum);
                }
            }
        }

        String maxCategory = Collections.max(sumCategories.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue)).getKey();
        int maxSum = sumCategories.get(maxCategory);
        JSONObject jsonMaxSum = new JSONObject();
        jsonMaxSum.put("categories", maxCategory);
        jsonMaxSum.put("sum", maxSum);
        return jsonMaxSum;
    }


}
