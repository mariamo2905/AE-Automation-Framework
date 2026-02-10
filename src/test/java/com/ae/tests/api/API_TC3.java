package com.ae.tests.api;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
public class API_TC3 {
 @Test
 public void test() {
  RestAssured.given().baseUri("https://automationexercise.com/api")
   .when().get("/brandsList")
   .then().statusCode(200);
 }
}
