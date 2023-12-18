package com.StrangerBookstore.User;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class test3 {


    @Test
    public void test(){
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String url="http://localhost:8080/home";
        String url2="http://localhost:8080/login";
        WebDriver driver=new ChromeDriver(options);
        driver.get(url);
        driver.get(url2);
        driver.findElement(By.name("username")).sendKeys("admin@gmail.com");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[3]/a[1]")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/div[3]/div/div/div[2]/button")).sendKeys(Keys.ENTER);


    }

}