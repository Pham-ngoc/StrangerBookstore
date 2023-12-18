package com.StrangerBookstore.Admin_Product;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class Create_fail_Publisher {

    @Test
    public void create(){
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String url="http://localhost:8080/home";
        String url2="http://localhost:8080/login";
        String url3="http://localhost:8080/#!/admin";
        String url4="http://localhost:8080/#!/admin/product";
        WebDriver driver=new ChromeDriver(options);
        driver.get(url);
        driver.get(url2);
        driver.findElement(By.name("username")).sendKeys("admin@gmail.com");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        driver.get(url3);
        driver.get(url4);
        driver.findElement(By.id("productName")).sendKeys("thiên tài bên trái, kẻ điên bên phải");
        driver.findElement(By.id("author")).sendKeys("Trương Đức Tài");
        driver.findElement(By.id("publisher")).sendKeys("");
        driver.findElement(By.id("language")).sendKeys("Vietnamese");
//        driver.findElement(By.cssSelector("#condition")).sendKeys("ductai dep trai");
//        driver.findElement(By.id("condition")).sendKeys("ductai dep trai");
        driver.findElement(By.xpath("//*[@id=\"condition \"]")).sendKeys("ductai dep trai");
//        driver.findElement(By.cssSelector("#quantityInStock")).sendKeys("566");
//        driver.findElement(By.id("quantityInStock")).sendKeys("566");
        driver.findElement(By.xpath("//*[@id=\"quantityInStock \"]")).sendKeys("566");
        driver.findElement(By.id("isbn")).sendKeys("874638463");
        driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("knfsmdfksdfansfasd");
        driver.findElement(By.id("price")).sendKeys("100000");
        Select select = new Select(driver.findElement(By.id("categoryId")));
        select.selectByIndex(1);
        driver.findElement(By.xpath("//div[@id='file-upload']//input[@id='product_img']"))
                .sendKeys(System.getProperty("user.dir") + "/src/main/resources/static/img/avata.jpg");
        driver.findElement(By.name("create")).sendKeys(Keys.ENTER);
    }

}