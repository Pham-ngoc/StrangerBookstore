package com.StrangerBookstore.Admin_Category;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Update {


    @Test
    public void create_success(){
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String url="http://localhost:8080/home";
        String url2="http://localhost:8080/login";
        String url3="http://localhost:8080/#!/admin";
        String url4="http://localhost:8080/#!/admin/categories";
        WebDriver driver=new ChromeDriver(options);
        driver.get(url);
        driver.get(url2);
        driver.findElement(By.name("username")).sendKeys("admin@gmail.com");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        driver.get(url3);
//
        driver.get(url4);
//        driver.findElement(By.id("edit")).sendKeys(Keys.ENTER);
//        driver.findElement(By.cssSelector("#edit")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div/div/ng-view/div/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[3]/div/button[1]")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("categoryName")).clear();
        driver.findElement(By.id("categoryName")).sendKeys("ductfsdfsdfad");
        driver.findElement(By.name("update")).sendKeys(Keys.ENTER);


    }

}