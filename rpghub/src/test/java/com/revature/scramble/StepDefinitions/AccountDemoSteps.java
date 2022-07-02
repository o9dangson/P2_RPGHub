package com.revature.scramble.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.revature.scramble.models.pagefactory.AccountPageFactory;
import com.revature.scramble.models.pagefactory.LoginPageFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountDemoSteps {

    public WebDriver driver;
    public LoginPageFactory loginPageFactory;
    public AccountPageFactory accountPageFactory;
    
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("http://localhost:9090/");
        loginPageFactory = new LoginPageFactory(driver);
        accountPageFactory = new AccountPageFactory(driver);
    }

    @After
    public void teardown(){
        if(!driver.getTitle().equals("Homepage")){
            WebElement logoutButton = driver.findElement(By.id("logout-btn"));
            logoutButton.click();
        }
        this.driver.quit();
    }

}