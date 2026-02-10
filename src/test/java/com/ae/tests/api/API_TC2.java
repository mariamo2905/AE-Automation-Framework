package com.ae.tests.api;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
public class API_TC2 {
 @Test
 public void test() {
  RestAssured.given().baseUri("https://automationexercise.com/api")
   .when().post("/productsList")
   .then().statusCode(200);
 }
}
