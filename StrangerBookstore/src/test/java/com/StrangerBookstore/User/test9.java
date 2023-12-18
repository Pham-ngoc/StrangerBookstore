package com.StrangerBookstore.User;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class test9 {


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
        driver.findElement(By.name("password")).sendKeys("1310");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        driver.get(url3);
        driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[3]/a[2]")).click();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div[2]/a[1]")).click();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/form/div[3]/input")).clear();
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/form/div[3]/input")).
                sendKeys("566/105/72 nguyễn thái sơn");
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/form/div[5]/button[2]")).click();

    }

}