package com.StrangerBookstore.Sign_up;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Sign_up_2_no_fullname {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\HK7\\StrangerBookstore\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:8080/login"); // Thay thế URL của trang web cần kiểm thử
    }

    @Test
    public void case2_Sign_up() {
        driver.findElement(By.id("signup")).sendKeys(Keys.ENTER);
        // Điền thông tin vào các trường input
        WebElement fullNameInput = driver.findElement(By.xpath("//input[@placeholder='Full name']"));
        fullNameInput.sendKeys("");

        WebElement phoneNumberInput = driver.findElement(By.xpath("//input[@placeholder='Phone number']"));
        phoneNumberInput.sendKeys("0395774296");

        WebElement emailInput = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        emailInput.sendKeys("ductaicr9@gmail.com");

        WebElement emailconfirmInput = driver.findElement(By.xpath("//input[@placeholder='Email Confirm']"));
        emailconfirmInput.sendKeys("ductaicr9@gmail.com");

        WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordInput.sendKeys("12346");

        WebElement passwordconfirmInput = driver.findElement(By.xpath("//input[@placeholder='Password Confirm']"));
        passwordconfirmInput.sendKeys("12346");

        // Submit form
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn sign-up3']"));
        submitButton.sendKeys(Keys.ENTER);

    }


}
