package com.ae.tests.ui;
import com.ae.utils.DriverManager;
import org.testng.annotations.Test;
public class TC8_AddToCart {
 @Test
 public void test() {
  DriverManager.getDriver().get("https://automationexercise.com");
  DriverManager.quitDriver();
 }
}
