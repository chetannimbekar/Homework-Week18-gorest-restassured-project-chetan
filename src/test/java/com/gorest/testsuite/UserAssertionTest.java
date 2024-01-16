package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
    }
    //1. Verify the if the total record is 20
    @Test
    public void test001(){
        response.body("size()",equalTo(20));
    }
//2. Verify the if the name of id = 5914197 is equal to ”Bhilangana Dhawan”
    @Test
    public void test002(){
        response.body("[2].name",equalTo("Chandini Prajapat"));
    }
//3. Check the single ‘Name’ in the Array list (Dev Bhattacharya)
    @Test
    public void test003(){
        response.body("[4].name",equalTo("Ekalavya Embranthiri"));
    }
//4. Check the multiple ‘Names’ in the ArrayList (Usha Kaul Esq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test004(){
        response.body("name",hasItems("Abani Butt","Tanirika Khan","Chidambaram Talwar"));
    }
//5. Verify the emai of userid = 5914185 is equal “tandon_iv_aanandinii@prosacco.example”
    @Test
    public void test005(){
        response.body("[4].email",equalTo("tanirika_khan@okon.example"));
    }
//6. Verify the status is “Active” of user name is “Amaresh Rana”
    @Test
    public void test006(){
        response.body("[8].status",equalTo("active"));
    }
//7. Verify the Gender = male of user name is “Dhanalakshmi Pothuvaal”
    @Test
    public void test007(){
        response.body("[8].gender",equalTo("male"));
    }
}
