package com.ae.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC3 {

    @Test(description = "API TC03: Get All Brands List")
    @Description("Calls /brandsList and validates HTTP 200 and non-empty response.")
    @Severity(SeverityLevel.CRITICAL)
    public void getAllBrandsList() {
        RestAssured.given()
                .filter(new AllureRestAssured())
                .baseUri("https://automationexercise.com/api")
                .when()
                .get("/brandsList")
                .then()
                .statusCode(200)
                .body("brands.size()", greaterThan(0)) // ✅ corrected path
                .body("brands.brand", not(empty()));
    }
}
