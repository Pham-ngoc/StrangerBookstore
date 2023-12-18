package com.StrangerBookstore.User;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class test17 {


    @Test
    public void test(){
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String url="http://localhost:8080/home";
        String url2="http://localhost:8080/login";
//        String url3="http://localhost:8080/login?logout=true";
        WebDriver driver=new ChromeDriver(options);
        driver.get(url);
        driver.get(url2);
        driver.findElement(By.name("username")).sendKeys("ductaitruongcr7@gmail.com");
        driver.findElement(By.name("password")).sendKeys("1310");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[1]/form/input[1]")).sendKeys("Những Đòn Tâm Lý Trong Thuyết Phục");
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[1]/form/input[1]")).sendKeys(Keys.ENTER);
//




    }

}