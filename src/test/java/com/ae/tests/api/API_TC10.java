package com.ae.tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC10 {

 @Test(description = "API TC10: Update Account")
 public void updateAccount() {
  RestAssured.given()
          .baseUri("https://automationexercise.com/api")
          .contentType("application/x-www-form-urlencoded; charset=UTF-8")
          .formParam("email", "johndoe@example.com")
          .formParam("name", "John Updated")
          .formParam("password", "123456")
          .when()
          .put("/updateAccount")
          .then()
          .statusCode(200)
          .body(notNullValue());
 }
}
