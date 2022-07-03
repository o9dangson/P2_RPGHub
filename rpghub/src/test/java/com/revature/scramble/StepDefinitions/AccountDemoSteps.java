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

    @Given("a user is logged in and on the Account page")
    public void a_user_is_logged_in_and_on_the_account_page() {
        loginPageFactory.login("user1","pass");
        Assert.assertEquals(driver.getTitle(), "Account");
    }
    @When("user clicks Filter Listing button")
    public void user_clicks_filter_listing_button() {
        accountPageFactory.click_filter_button();
    }
    @When("user chooses a category to filter by")
    public void user_chooses_a_category_to_filter_by() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        accountPageFactory.select_category();
        }
    @When("user inputs a specific filter")
    public void user_inputs_a_specific_filter() {
        accountPageFactory.input_specific_filter("any string less than 50 chars");
    }
    @When("user clicks Filter button")
    public void user_clicks_filter_button() {
        accountPageFactory.apply_filter();
    }

    @When("user clicks Create Listing button")
    public void user_clicks_create_listing_button() {
        accountPageFactory.click_create_listing_button();
    }
    @When("user inputs a list name")
    public void user_inputs_a_list_name() {
        accountPageFactory.enter_listing_name("new list name less than 50 char");
    }
    @When("user inputs a dungeon name")
    public void user_inputs_a_dungeon_name() {
        accountPageFactory.input_dungeon("dungeon name");
    }
    @When("user inputs max party size")
    public void user_inputs_max_party_size() {
        accountPageFactory.list_max_party_size();
    }
    @When("user clicks Submit button")
    public void user_clicks_submit_button() {
        accountPageFactory.click_listing_submit_button();
    }
    @Then("the listing should be shown")
    public void the_listing_should_be_shown() {
        Assert.assertEquals(driver.getTitle(), "Account");
    }
    @Then("there should be a new listing")
    public void there_should_be_a_new_listing() {
        Assert.assertEquals(driver.getTitle(), "Account");
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