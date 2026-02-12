package com.ae.tests.ui;

import com.ae.utils.DriverManager;
import com.ae.utils.ScreenshotUtil;
import com.ae.utils.WaitUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TC6_VerifyProducts {

    @Description("UI TC06: Verify all products are displayed on homepage.")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testVerifyProducts() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://automationexercise.com");

        // Locator for products
        By productLocator = By.cssSelector(".features_items .product-image-wrapper");

        // Wait until all products are visible
        List<WebElement> products = WaitUtils.waitForAllVisible(driver, productLocator);

        // Verify that at least one product is displayed
        Assert.assertTrue(products.size() > 0, "Expected at least one product on homepage");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            ScreenshotUtil.takeScreenshot(DriverManager.getDriver());
        }
        DriverManager.quitDriver();
    }
}
