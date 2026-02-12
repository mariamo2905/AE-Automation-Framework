package com.ae.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC4 {

    @Test(description = "API TC04: PUT To All Brands List")
    @Description("Sends PUT to /brandsList and validates server response (status code and body).")
    @Severity(SeverityLevel.NORMAL)
    public void putToBrandsList() {
        RestAssured.given()
                .filter(new AllureRestAssured())
                .baseUri("https://automationexercise.com/api")
                .when()
                .put("/brandsList")
                .then()
                .statusCode(anyOf(is(200), is(405)))
                .body(notNullValue());
    }
}
