package com.revature.scramble.StepDefinitions;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.revature.scramble.models.pagefactory.AccountPageFactory;
import com.revature.scramble.models.pagefactory.ListingPageFactory;
import com.revature.scramble.models.pagefactory.LoginPageFactory;
import com.revature.scramble.repository.entities.Entry;
import com.revature.scramble.service.EntryService;
public class ListingDemoSteps {
    
    public WebDriver driver;
    public LoginPageFactory loginPageFactory;
    public AccountPageFactory accountPageFactory;
    public ListingPageFactory listingPageFactory;

    List<WebElement> all_entries;
    List<WebElement> updated_entries;
    List<WebElement> entry_statuses;
    WebElement selected_entry;
    WebElement selected_entry_status;
    int amt_of_entries;
    int updated_entry_amt;
    String session_id;
    String listing_leader;
    Random rand;
    int randIndex;
    

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.get("http://localhost:9090/");
        loginPageFactory = new LoginPageFactory(driver);
        accountPageFactory = new AccountPageFactory(driver);
        listingPageFactory = new ListingPageFactory(driver);
    }

    @Given("User is logged in")
    public void user_is_logged_in() {
        loginPageFactory.login("user1","pass");

    }
    @When("user clicks select listing button")
    public void user_clicks_select_listing_button() {
        
        accountPageFactory.click_select_listing_button();
        
    }
    @When("user clicks view selected listing button")
    public void user_clicks_view_selected_listing_button() {
        accountPageFactory.view_selected_listing();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
        all_entries = driver.findElements(By.className("entry-row-div"));
        amt_of_entries = all_entries.size();
        
    }
    @When("user clicks join listing button")
    public void user_clicks_join_listing_button() {
        listingPageFactory.clickJoinListingButton();
        
    }
    @When("user clicks a role")
        public void select_role(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        listingPageFactory.select_role();
    }

    @When("user inputs a note")
    public void input_user_note(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        listingPageFactory.input_user_note("test");
        listingPageFactory.clickSubmitButton();
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("user clicks leave group button")
    public void user_clicks_leave_group_button() {
        String hasJoined = driver.findElement(By.id("hasJoined")).getAttribute("innerHTML");
        if(hasJoined.equals("true")){
            listingPageFactory.clickLeaveGroupButton();
        }
    }

    @Then("user will be added to the list")
    public void user_will_be_added_to_the_list() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        updated_entries = driver.findElements(By.className("entry-row-div"));
        String hasApplied = driver.findElement(By.id("hasApplied")).getAttribute("innerHTML");
        if(hasApplied.equals("false")){
            System.out.println("updated entries");
            updated_entry_amt = updated_entries.size();
            Assert.assertTrue(updated_entry_amt>amt_of_entries);
            Assert.assertEquals(driver.getTitle(), "Listing");
        }
        else{
            Assert.assertEquals(hasApplied, "true");
        }
        
    }
    
    @Then("user will be removed from listing")
    public void user_will_be_removed_from_listing() {
        String hasJoined = driver.findElement(By.id("hasJoined")).getAttribute("innerHTML");
        if(hasJoined.equals("true")){
            updated_entries = driver.findElements(By.className("entry-row-div"));
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
            updated_entry_amt = updated_entries.size();
            Boolean list_updated = updated_entry_amt < amt_of_entries || updated_entry_amt == 0;
            Assert.assertTrue(list_updated);
        }
        Assert.assertEquals(driver.getTitle(), "Listing");
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