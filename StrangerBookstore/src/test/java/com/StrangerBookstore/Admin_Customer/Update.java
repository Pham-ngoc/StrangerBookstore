package com.StrangerBookstore.Admin_Customer;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Update {


    @Test
    public void update(){
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
        driver.findElement(By.id("edit")).sendKeys(Keys.ENTER);
//        driver.findElement(By.cssSelector("#edit")).sendKeys(Keys.ENTER);
//        driver.findElement(By.xpath("//*[@id=\"edit\"]")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("fullName")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("09873746345");
        driver.findElement(By.id("fullName")).sendKeys("administration");
//        driver.findElement(By.id("email")).sendKeys("admin123@gmail.com");

        WebElement Admin = driver.findElement(By.xpath("//*[@id=\"enable\"]"));
        WebElement User = driver.findElement(By.xpath("//*[@id=\"disable\"]"));

        if(Admin.isSelected()==true){
            User.click();
        }else {
            Admin.click();
        }

        driver.findElement(By.xpath("//div[@id='upload']//div[@id='file-upload']//input[@id='file-input']"))
                .sendKeys(System.getProperty("user.dir") + "/src/main/resources/static/img/avata.jpg");
        driver.findElement(By.name("update")).sendKeys(Keys.ENTER);
    }

}