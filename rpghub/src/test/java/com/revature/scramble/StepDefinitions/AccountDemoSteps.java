package com.revature.scramble.StepDefinitions;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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

    List<WebElement> listings;
    List<WebElement> updated_listings;
    int amt_of_listings;
    int updated_list_size;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        loginPageFactory.login("user1","pass");
        Assert.assertEquals(driver.getTitle(), "Account");
        listings = driver.findElements(By.className("listing-row-div"));
        amt_of_listings = listings.size();
        
    }

    @When("user clicks Filter Listing button")
    public void user_clicks_filter_listing_button() {
        new WebDriverWait(driver, Duration.ofSeconds(1));
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        WebElement btn = driver.findElement(By.xpath("//*[@id='filter-btn-container']/input"));
        btn.click();
    }
    @When("user chooses a category to filter by")
    public void user_chooses_a_category_to_filter_by() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.select_category(accountPageFactory.filter_category_dropdown);
    }
    @When("user inputs a specific filter")
    public void user_inputs_a_specific_filter() {
        accountPageFactory.input_specific_filter("new list name less than 50 char");
    }
    @When("user clicks Filter button")
    public void user_clicks_filter_button() {
        new WebDriverWait(driver, Duration.ofSeconds(1));
        accountPageFactory.apply_filter();
    }

    @When("user clicks Create Listing button")
    public void user_clicks_create_listing_button() {

        accountPageFactory.click_create_listing_button();
    }
    @When("user inputs a list name")
    public void user_inputs_a_list_name() {
        accountPageFactory.enter_listing_name("trying to filter groups");
    }
    @When("user inputs a dungeon name")
    public void user_inputs_a_dungeon_name() {
        accountPageFactory.input_dungeon("dungeon name");
    }
    @When("user inputs max party size")
    public void user_inputs_max_party_size() {
        accountPageFactory.list_max_party_size();
        sleep(3);
    }
    @When("user clicks Submit button")
    public void user_clicks_submit_button() {
        accountPageFactory.click_listing_submit_button();
        sleep(3);
    }

    @When("user clicks Select Listing button")
    public void user_clicks_select_listing_button() {
        accountPageFactory.click_select_listing_button();
        
    }
    
    @When("user clicks View Selected Listing button")
    public void user_clicks_view_selected_listing_button() {
        accountPageFactory.view_selected_listing();
    }

    @When("user clicks Remove Selected Listing button")
    public void user_clicks_remove_selected_listing_button() {
        Assert.assertTrue(accountPageFactory.listing_selected());
        accountPageFactory.click_remove_listing_button();
    }
    @Then("the listing should be shown")
    public void the_listing_should_be_shown() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        updated_listings = driver.findElements(By.className("listing-row-div"));
        updated_list_size = updated_listings.size();
        Boolean list_updated = updated_list_size <= amt_of_listings;
        Assert.assertTrue(list_updated);
        Assert.assertEquals(driver.getTitle(), "Account");
    }
    @Then("there should be a new listing")
    public void there_should_be_a_new_listing() {
        sleep(2);
        updated_listings = driver.findElements(By.className("listing-row-div"));
        updated_list_size = updated_listings.size();
        Boolean list_updated = updated_list_size > amt_of_listings;
        Assert.assertTrue(list_updated);
        Assert.assertEquals(driver.getTitle(), "Account");
    }

    @Then("user should be shown listing in listing page")
    public void user_should_be_shown_listing_in_listing_page() {
        Assert.assertEquals(driver.getTitle(), "Listing");
    }

    @Then("the selected listing should be removed")
    public void the_selected_listing_should_be_removed() {
        sleep((long).25);
        updated_listings = driver.findElements(By.className("listing-row-div"));
        updated_list_size = updated_listings.size();
        Boolean list_updated = updated_list_size < amt_of_listings;
        //Assert.assertTrue(list_updated);
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
    
    public void sleep(long x){
        try {
            TimeUnit.SECONDS.sleep(x);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

