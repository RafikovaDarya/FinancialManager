package org.example;
import java.util.List;


public class ProductCategories {

    private String productName;
    private String categoriesOfProduct;


    private ProductCategories(String productName, String categoriesOfProduct) {
        this.categoriesOfProduct = categoriesOfProduct;
        this.productName = productName;
    }

    public ProductCategories() {

    }

    public String getProductName() {
        return productName;
    }

    public String getCategoriesOfProduct() {
        return categoriesOfProduct;
    }

    public List<ProductCategories> addListOfProducts() {

        List<ProductCategories> listOfProducts = List.of(
                new ProductCategories("булка", "еда"),
                new ProductCategories("колбаса", "еда"),
                new ProductCategories("сухарики", "еда"),
                new ProductCategories("курица", "еда"),
                new ProductCategories("тапки", "одежда"),
                new ProductCategories("шапка", "одежда"),
                new ProductCategories("мыло", "быт"),
                new ProductCategories("акции", "финансы")
        );

        return listOfProducts;
    }


}
