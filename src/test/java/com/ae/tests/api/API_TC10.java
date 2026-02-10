package com.ae.tests.api;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
public class API_TC10 {
 @Test
 public void test() {
  RestAssured.given().baseUri("https://automationexercise.com/api")
   .when().put("/updateAccount")
   .then().statusCode(200);
 }
}
