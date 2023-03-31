package com.Usersignup;

//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;


import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;


@SuppressWarnings("unused")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginMvc1ApplicationTests {

    @Test
    @Order(1)
    public void testSignup() {
        String jsonbody="{\"firstname\":\"Heena\",\"lastname\":\"Shahanaz\",\"email\":\"heenashahanaz@gmail.com\",\"password\":\"heena\",\"phonenumber\":\"9440952699\"}";
        String res=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonbody)
                .when()
                .post("http://localhost:8081/user/add")
                .then()
                .assertThat().statusCode(201)
                .extract().response().asString();
    }
    

    @Test
    @Order(2)
    public void testupdate() {
        String jsonbody="{\"firstname\":\"heena\",\"lastname\":\"Shahanaz\",\"email\":\"heenashahanaz@gmail.com\",\"password\":\"heena\",\"phonenumber\":\"9440952687\"}";
        String res=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonbody)
                .when()
                .put("http://localhost:8081/user/update/heenashahanaz@gmail.com")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
    
    
    @Test
    @Order(3)
    public void testgetuser() {
        String result=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get("http://localhost:8081/user/get/heenashahanaz@gmail.com")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
    
    @Test
    @Order(4)
    public void testdeleteuser() {
        String jsonbody= "{\"email\" : \"heenashahanaz@gmail.com\",\"password\" : \"heena\"}";
        String token=given()
                .header("Content-type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(jsonbody)
                .when()
                .delete("http://localhost:8081/user/delete/heenashahanaz@gmail.com")
                .then()
                .assertThat().statusCode(200)
                .extract().response().asString();
    }
}