package com.ae.tests.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC9 {

 @Test(description = "API TC09: Delete Account")
 public void deleteAccount() {
  RestAssured.given()
          .baseUri("https://automationexercise.com/api")
          .contentType("application/x-www-form-urlencoded; charset=UTF-8")
          .formParam("email", "johndoe@example.com")
          .formParam("password", "123456")
          .when()
          .delete("/deleteAccount")
          .then()
          .statusCode(200)
          .body(notNullValue());
 }
}
