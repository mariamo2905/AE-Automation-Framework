package com.ae.tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC8 {

 @Test(description = "API TC08: Create Account")
 public void createAccount() {
  RestAssured.given()
          .baseUri("https://automationexercise.com/api")
          .contentType("application/x-www-form-urlencoded; charset=UTF-8")
          .formParam("name", "John Doe")
          .formParam("email", "johndoe@example.com")
          .formParam("password", "123456")
          .when()
          .post("/createAccount")
          .then()
          .statusCode(200)
          .body(notNullValue());
 }
}
