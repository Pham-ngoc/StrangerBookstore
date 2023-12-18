package com.StrangerBookstore.Sign_in;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Sign_in_7_both_empty {


    @Test
    public void case7_Sign_in(){
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String url="http://localhost:8080/login";
        WebDriver driver=new ChromeDriver(options);
        driver.get(url);
        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
    }

}