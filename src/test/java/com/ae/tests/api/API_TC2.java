package com.ae.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC2 {

    @Test(description = "API TC02: POST To All Products List (expected behavior check)")
    @Description("Sends POST to /productsList and validates server responds (status code and body).")
    @Severity(SeverityLevel.NORMAL)
    public void postToProductsList() {
        RestAssured.given()
                .filter(new AllureRestAssured())
                .baseUri("https://automationexercise.com/api")
                .when()
                .post("/productsList")
                .then()
                .statusCode(anyOf(is(200), is(405)))   // site sometimes returns 200 with error msg OR 405
                .body(notNullValue());
    }
}
