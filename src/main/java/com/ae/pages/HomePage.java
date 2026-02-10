package com.ae.pages;
import org.openqa.selenium.WebDriver;
public class HomePage {
 private WebDriver driver;
 public HomePage(WebDriver d){this.driver=d;}
 public void open(){driver.get("https://automationexercise.com");}
}
