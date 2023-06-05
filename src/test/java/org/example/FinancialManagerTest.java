package org.example;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

class FinancialManagerTest {
    final String otherCategory = "{\"title\": \"книга\", \"date\": \"2022.02.08\", \"sum\": 300}";
    final String homeCategory = "{\"title\": \"мыло\", \"date\": \"2022.02.08\", \"sum\": 1000}";
    final String foodCategory1 = "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}";
    final String foodCategory2 = "{\"title\": \"колбаса\", \"date\": \"2022.02.08\", \"sum\": 300}";
    FinancialManager financialManager = new FinancialManager();
    Gson gsonTest = new Gson();


    @Test
    public void test_foodCategory() throws IOException {
        Basket basket = gsonTest.fromJson(foodCategory1, Basket.class);//булка 200
        financialManager.basketList.add(basket);
        Map actualResult = (financialManager.fromTsvMaxCategories(new File("categories.tsv")));

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("categories", "еда");
        expectedResult.put("sum", 200);
        System.out.println(expectedResult.get("sum"));
        System.out.println(actualResult.get("sum"));
        Assertions.assertEquals(expectedResult.get("sum"), actualResult.get("sum"));

    }
    @Test
    public void test_sumFoodCategory() throws IOException {
        Basket basket1 = gsonTest.fromJson(foodCategory1, Basket.class);//булка 200
        Basket basket2 = gsonTest.fromJson(foodCategory2, Basket.class);//колбаса 300

        financialManager.basketList.add(basket1);
        financialManager.basketList.add(basket2);
        Map actualResult = (financialManager.fromTsvMaxCategories(new File("categories.tsv")));

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("categories", "еда");
        expectedResult.put("sum", 500);
        System.out.println(expectedResult.get("sum"));
        System.out.println(actualResult.get("sum"));
        Assertions.assertEquals(expectedResult.get("sum"), actualResult.get("sum"));

    }
    @Test
    public void test_twoCategories() throws IOException {
        Basket basket1 = gsonTest.fromJson(foodCategory1, Basket.class);//булка 200
        Basket basket2 = gsonTest.fromJson(homeCategory, Basket.class);//мыло 1000

        financialManager.basketList.add(basket1);
        financialManager.basketList.add(basket2);
        Map actualResult = (financialManager.fromTsvMaxCategories(new File("categories.tsv")));

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("categories", "быт");
        expectedResult.put("sum", 1000);
        System.out.println(expectedResult.get("sum"));
        System.out.println(actualResult.get("sum"));
        Assertions.assertEquals(expectedResult.get("sum"), actualResult.get("sum"));
    }

    @Test
    public void test_otherCategory() throws IOException {
        Basket basket1 = gsonTest.fromJson(otherCategory, Basket.class);//книга 300


        financialManager.basketList.add(basket1);

        Map actualResult = (financialManager.fromTsvMaxCategories(new File("categories.tsv")));

        JSONObject expectedResult = new JSONObject();
        expectedResult.put("categories", "другое");
        expectedResult.put("sum", 300);
        System.out.println(expectedResult.get("sum"));
        System.out.println(actualResult.get("sum"));
        Assertions.assertEquals(expectedResult.get("sum"), actualResult.get("sum"));
    }



}