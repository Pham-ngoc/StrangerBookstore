package com.StrangerBookstore.Admin_News;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class create_NewsFail_picture {


    @Test
    public void create_news_fail(){
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String url="http://localhost:8080/home";
        String url2="http://localhost:8080/login";
        String url3="http://localhost:8080/#!/admin";
        String url4="http://localhost:8080/#!/admin/news";
        WebDriver driver=new ChromeDriver(options);
        driver.get(url);
        driver.get(url2);
        driver.findElement(By.name("username")).sendKeys("admin@gmail.com");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        driver.get(url3);
//        driver.findElement(By.id("icon")).sendKeys(Keys.ENTER);
//        driver.findElement(By.id("admin")).sendKeys(Keys.ENTER);
        driver.get(url4);
        driver.findElement(By.cssSelector("#newsTitle")).sendKeys("fasdaf");
        driver.findElement(By.id("textarea-input")).sendKeys("djfnsjfdgghdfgsf");

        driver.findElement(By.xpath("//div[@id='file-upload']//input[@id='file-input']"));

        driver.findElement(By.name("create")).sendKeys(Keys.ENTER);


    }

}