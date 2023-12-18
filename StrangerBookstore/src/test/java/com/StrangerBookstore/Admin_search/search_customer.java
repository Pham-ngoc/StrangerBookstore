package com.StrangerBookstore.Admin_search;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class search_customer {


    @Test
    public void search(){
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String url="http://localhost:8080/home";
        String url2="http://localhost:8080/login";
        String url3="http://localhost:8080/#!/admin";
        String url4="http://localhost:8080/#!/admin/customer";
        WebDriver driver=new ChromeDriver(options);
        driver.get(url);
        driver.get(url2);
        driver.findElement(By.name("username")).sendKeys("admin@gmail.com");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        driver.get(url3);
//
        driver.get(url4);
        driver.findElement(By.id("search")).sendKeys("Admin");
//        driver.findElement(By.cssSelector("#edit")).sendKeys(Keys.ENTER);
//        driver.findElement(By.xpath(""))
//                .sendKeys(Keys.ENTER);

        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);


    }

}
