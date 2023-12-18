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


public class Sign_up_8_email_noNumber {

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
    public void case8_Sign_up() {
        driver.findElement(By.id("signup")).sendKeys(Keys.ENTER);
        // Điền thông tin vào các trường input
        WebElement fullNameInput = driver.findElement(By.xpath("//input[@placeholder='Full name']"));
        fullNameInput.sendKeys("ductaivipPro");

        WebElement phoneNumberInput = driver.findElement(By.xpath("//input[@placeholder='Phone number']"));
        phoneNumberInput.sendKeys("0395774677");

        WebElement emailInput = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        emailInput.sendKeys("tdtai@gmail.com");

        WebElement emailconfirmInput = driver.findElement(By.xpath("//input[@placeholder='Email Confirm']"));
        emailconfirmInput.sendKeys("tdtai@gmail.com");

        WebElement passwordInput = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        passwordInput.sendKeys("123");

        WebElement passwordconfirmInput = driver.findElement(By.xpath("//input[@placeholder='Password Confirm']"));
        passwordconfirmInput.sendKeys("123");

        // Submit form
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn sign-up3']"));
        submitButton.sendKeys(Keys.ENTER);

    }


}
