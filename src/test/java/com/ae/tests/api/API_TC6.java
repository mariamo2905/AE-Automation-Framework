package com.ae.tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC6 {

 @Test(description = "API TC06: Verify Login")
 public void verifyLogin() {
  RestAssured.given()
          .baseUri("https://automationexercise.com/api")
          .contentType("application/x-www-form-urlencoded; charset=UTF-8")
          .formParam("email", "test@example.com") // replace with valid test data
          .formParam("password", "123456")        // replace with valid test data
          .when()
          .post("/verifyLogin")
          .then()
          .statusCode(200)
          .body(notNullValue());
 }
}
