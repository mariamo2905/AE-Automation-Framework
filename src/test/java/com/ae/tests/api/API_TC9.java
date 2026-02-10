package com.ae.tests.api;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
public class API_TC9 {
 @Test
 public void test() {
  RestAssured.given().baseUri("https://automationexercise.com/api")
   .when().delete("/deleteAccount")
   .then().statusCode(200);
 }
}
