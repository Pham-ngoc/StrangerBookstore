package com.StrangerBookstore.User;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class test7 {


    @Test
    public void test(){
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String url="http://localhost:8080/home";
        String url2="http://localhost:8080/login";
        String url3="http://localhost:8080/profile/personal";
        WebDriver driver=new ChromeDriver(options);
        driver.get(url);
        driver.get(url2);
        driver.findElement(By.name("username")).sendKeys("ductaitruongcr7@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        driver.get(url3);
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/form/div[2]/div[2]/input")).clear();

        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/form/div[2]/div[2]/input")).sendKeys("0395747952");
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/form/div[2]/div[4]/button")).click();



    }

}