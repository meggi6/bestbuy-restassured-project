package com.bestbuy.crudtest;

import com.bestbuy.models.ProductPojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest {
    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/products";
    }

    static String name = TestUtils.getRandomValue() + "prime";
    static String type = "Battery";
    static int price = 1250;
    static int shipping = 1;
    static String upc = "JAVA";
    static String description = "API";
    static String manufacturer = "nishant manufacturer";
    static String model = "Testing";
    static String url = "prime" + TestUtils.getRandomValue() + "@gmail.com";
    static String image = "prime.jpg";
    static int productID;


    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);

        productID = response.then().extract().path("id");
        System.out.println("the id is: " + productID);
    }

    @Test
    public void test002() {
        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productID);
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void test003() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "abc");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .patch("/" + productID);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test004() {
        given()
                .when()
                .delete("/" + productID)
                .then()
                .statusCode(200);
        Response response = given()
                .header("Accept", "application/json")
                .when()
                .get("/" + productID);
        response.then().statusCode(404);
        response.prettyPrint();


    }
}

