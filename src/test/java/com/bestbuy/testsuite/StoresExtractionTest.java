package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> names = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all store are : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> storeIds = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeIds of all store are : " + storeIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        int size = response.extract().path("data.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + size);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, Object>> value = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the value of the store St Cloud are: " + value);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        String address = response.extract().path("data[8].address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The address of the store Rochester is: " + address);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String, Object>> services = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all services of 8th store are: " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test0010() {
        List<HashMap<String, Object>> storeservices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of service name 'Windows Store': " + storeservices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all Ids of all the store
    @Test
    public void test0011() {
        List<Integer> ids = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of of all the store are: " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test0012() {
        List<Integer> ids = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ids of of all the store are: " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test0013() {
        List<String> storeNames = response.extract().path("data.findAll{it.state == 'ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeNames of Where state = ND are: " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test0014() {
        int number = response.extract().path("data.findAll{it.name == 'Rochester'}.services.size()");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services of Where store name = Rochester: " + number);
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test0015() {
        List<?> createdAt = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name = “Windows Store”: " + createdAt);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test0016() {
        List<String> name = response.extract().path("data.findAll{it.name == 'Fargo'}.services*.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name = “Fargo”: " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test0017() {
        List<Integer> zip = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store" + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test0018() {
        int zip = response.extract().path("data.findAll{it.name =='Roseville'}.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville" + zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test0019() {
        List<HashMap<String, Object>> storeservices = response.extract().path("data.services*.findAll{it.name =='Magnolia Home Theater'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater" + storeservices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test0020() {
        List<Double> lat = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores" + lat);
        System.out.println("------------------End of Test---------------------------");
    }

}
