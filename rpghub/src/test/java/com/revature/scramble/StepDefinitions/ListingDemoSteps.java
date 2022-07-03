package com.revature.scramble.StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.revature.scramble.models.pagefactory.AccountPageFactory;
import com.revature.scramble.models.pagefactory.ListingPageFactory;
import com.revature.scramble.models.pagefactory.LoginPageFactory;
public class ListingDemoSteps {
    
    public WebDriver driver;
    public LoginPageFactory loginPageFactory;
    public AccountPageFactory accountPageFactory;
    public ListingPageFactory listingPageFactory;


    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("http://localhost:9090/");
        loginPageFactory = new LoginPageFactory(driver);
    }


    @Given("User is logged in")
    public void user_is_logged_in() {
        // Write code here that turns the phrase above into concrete actions
        loginPageFactory.inputUsername("user1");
        loginPageFactory.inputPassword("pass");
        throw new io.cucumber.java.PendingException();
        
    }
    @When("user clicks select listing button")
    public void user_clicks_select_listing_button() {
        accountPageFactory.click_select_listing_button();
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("user clicks view selected listing button")
    public void user_clicks_view_selected_listing_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("user clicks leave group button")
    public void user_clicks_leave_group_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("user will be removed from listing")
    public void user_will_be_removed_from_listing() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }






}
