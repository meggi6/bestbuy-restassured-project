package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    //21. Extract the limit
    @Test
    public void test0021() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void test0022() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void test0023() {
        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th product is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void test0024() {
        List<String> name = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the products : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test0025() {
        List<Integer> ids = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product Id of all the products : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void test0026() {
        int size = response.extract().path("data.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list: " + size);
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test0027() {
        List<HashMap<String, Object>> value = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the value of the store St Cloud are: " + value);
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test0028() {
        Object model = response.extract().path("data.findAll{it.name== 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack): " + model);
        System.out.println("------------------End of Test---------------------------");
    }

    //29. Get all the categories of 8th products
    @Test
    public void test0029() {
        List<HashMap<String, Object>> categories = response.extract().path("data[8].categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the categories of 8th products: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test0030() {
        List<HashMap<String, Object>> categories = response.extract().path("data.findAll{it.id==150115}.categories");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("categories of the store where product id = 150115: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test0031() {
        List<HashMap<String, Object>> description = response.extract().path("data.description");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the descriptions of all the products: " + description);
        System.out.println("------------------End of Test---------------------------");
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test0032() {
        List<Object> ids = response.extract().path("data.categories.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("id of all the all categories of all the products: " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test0033() {
        List<String> names = response.extract().path("data.findAll{it.type=='HardGood'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("product names Where type = HardGood: " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test0034() {
        int totalCategories = response.extract().path("data.findAll{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of categories for the product where product name = Duracell - AA: " + totalCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test0035() {
        List<Object> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the createdAt for all products whose price < 5.49: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test0036() {
        List<String> name = response.extract().path("data.findAll{it.name =='Energizer - MAX Batteries AA (4-Pack)'}.categories.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("name of all categories Where product name = Energizer - MAX Batteries AA (4-Pack): " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test0037() {
        List<String> manufacturer = response.extract().path("data.manufacturer");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("manufacturer of all the products: " + manufacturer);
        System.out.println("------------------End of Test---------------------------");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test0038() {
        List<Object> image = response.extract().path("data.findAll{it.manufacturer =='Energizer'}.image");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("image of products whose manufacturer is = Energizer: " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test0039() {
        List<Object> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("createdAt for all categories products whose price > 5.99: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the uri of all the product
    @Test
    public void test0040() {
        List<Object> url = response.extract().path("data.url");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("url of all the product: " + url);
        System.out.println("------------------End of Test---------------------------");
    }
}
