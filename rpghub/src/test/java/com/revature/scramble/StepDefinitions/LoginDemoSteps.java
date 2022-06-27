package com.revature.scramble.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.revature.scramble.models.pagefactory.LoginPageFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginDemoSteps {
    
    public WebDriver driver;
    public LoginPageFactory loginPageFactory;
    
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("http://localhost:9090/");
        loginPageFactory = new LoginPageFactory(driver);
    }

    @Given("a user is on the login page")
    public void a_user_is_on_the_login_page() {
        Assert.assertEquals(driver.getTitle(), "Homepage");
    }

    @When("a user enters the correct username and correct password")
    public void a_user_enters_the_correct_username_and_correct_password() {
        loginPageFactory.inputUsername("user1");
        loginPageFactory.inputPassword("pass");
    }
    @When("clicks on login button")
    public void clicks_on_login_button() {
        loginPageFactory.clickLoginButton();
    }
    @Then("a user is navigated to the homepage")
    public void a_user_is_navigated_to_the_homepage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        Assert.assertEquals(driver.getTitle(), "Account");
    }

    @After
    public void teardown(){
        WebElement logoutButton = driver.findElement(By.id("logout-btn"));
        logoutButton.click();
        this.driver.quit();
    }
}