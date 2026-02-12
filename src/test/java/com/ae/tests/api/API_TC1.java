package com.ae.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC1 {

    @Test(description = "API TC01: Get All Products List")
    @Description("Calls /productsList and validates HTTP 200 and response is not empty.")
    @Severity(SeverityLevel.CRITICAL)
    public void getAllProductsList() {
        RestAssured.given()
                .filter(new AllureRestAssured())
                .baseUri("https://automationexercise.com/api")
                .when()
                .get("/productsList")
                .then()
                .statusCode(200)
                .body("products", notNullValue())
                .body("products.size()", greaterThan(0));
    }
}
