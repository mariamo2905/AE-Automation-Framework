package com.ae.tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class API_TC5 {

    @Test(description = "API TC05: Search Product")
    @Description("POST /searchProduct with a search parameter and validate response.")
    @Severity(SeverityLevel.CRITICAL)
    public void searchProduct() {
        RestAssured.given()
                .filter(new AllureRestAssured())
                .baseUri("https://automationexercise.com/api")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("search_product", "top")
                .when()
                .post("/searchProduct")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }
}
