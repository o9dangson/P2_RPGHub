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
        if(!driver.getTitle().equals("Homepage")){
            WebElement logoutButton = driver.findElement(By.id("logout-btn"));
            logoutButton.click();
        }
        Assert.assertEquals(driver.getTitle(), "Homepage");
    }

    @When("a user enters the correct username and correct password")
    public void a_user_enters_the_correct_username_and_correct_password() {
        loginPageFactory.inputUsername("user1");
        loginPageFactory.inputPassword("pass");
    }

    @When("a user enters frozen login information")
    public void a_user_enters_frozen_login_information() {
        loginPageFactory.inputUsername("user3");
        loginPageFactory.inputPassword("pass");
    }
    @When("a user enters the incorrect username and password")
    public void a_user_enters_the_incorrect_username_and_password() {
        loginPageFactory.inputUsername("user");
        loginPageFactory.inputPassword("pass");
    }
    @When("clicks on login button")
    public void clicks_on_login_button() {
        loginPageFactory.clickLoginButton();
    }
    @Then("a user will be navigated to a page telling them they are banned")
    public void a_user_will_be_navigated_to_a_page_telling_them_they_are_banned() {
        // Write code here that turns the phrase above into concrete actions
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        Assert.assertEquals(driver.getTitle(), "Banned!");
}
    @Then("a user is navigated to the account page")
    public void a_user_is_navigated_to_the_account_page() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        Assert.assertEquals(driver.getTitle(), "Account");
    }
    @Then("a user is navigated to the homepage")
    public void a_user_is_navigated_to_the_homepage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        Assert.assertEquals(driver.getTitle(), "Homepage");
    }



    // @When("a user is logged in {logged_in}")
    // public void a_user_is_logged_in(Boolean logged_in){
    //     if(logged_in){
    //         loginPageFactory.login("user1", "pass");
    //         Assert.assertEquals(driver.getTitle(), "Account");
    //     }
    //     Assert.assertEquals(driver.getTitle(), "Homepage");
    // }
    // @When ("a user types in the url {url_path}")
    // public void a_user_types_in_the_url(String url_path){
    //     //driver.get(url);
    // }
    // @Then("they should be at {location}")
    // public void they_should_be_at_location(String location){
    //     //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    //     //Assert.assertEquals(driver.getTitle(), location);
    // }

    @After
    public void teardown(){
        if(!driver.getTitle().equals("Homepage")){
            WebElement logoutButton = driver.findElement(By.id("logout-btn"));
            logoutButton.click();
        }
        this.driver.quit();
    }
}