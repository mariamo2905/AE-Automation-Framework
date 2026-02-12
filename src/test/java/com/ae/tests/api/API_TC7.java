package com.ae.tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC7 {

 @Test(description = "API TC07: Verify Login Duplicate")
 public void verifyLoginDuplicate() {
  RestAssured.given()
          .baseUri("https://automationexercise.com/api")
          .contentType("application/x-www-form-urlencoded; charset=UTF-8")
          .formParam("email", "duplicate@example.com") // test data
          .formParam("password", "123456")
          .when()
          .post("/verifyLogin")
          .then()
          .statusCode(200)
          .body(notNullValue());
 }
}
