package com.revature.scramble.StepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.revature.scramble.models.pagefactory.BannedPageFactory;
import com.revature.scramble.models.pagefactory.ListingPageFactory;
import com.revature.scramble.repository.entities.Entry;
import com.revature.scramble.service.EntryService;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ModDemoSteps {

    public WebDriver driver;
    public LoginPageFactory loginPageFactory;
    public AccountPageFactory accountPageFactory;
    public ListingPageFactory listingPageFactory;
    public BannedPageFactory bannedPageFactory;

    List<WebElement> listings = new ArrayList<>();
    List<WebElement> updated_listings= new ArrayList<>();;
    List<WebElement> all_entries= new ArrayList<>();;
    List<WebElement> updated_entries= new ArrayList<>();;
    Map<String, Integer> map = new HashMap<String, Integer>();
    Map<String, Integer> map2 = new HashMap<String, Integer>();
    int entry_id_temp;
    int list_id_temp;
    int amt_of_listings;
    int updated_list_size;

    // @Before
    // public void setup(){
    //     System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
    //     driver = new ChromeDriver();
    //     driver.manage().window().maximize();
    //     driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    //     driver.get("http://localhost:9090/");
    //     if(!driver.getTitle().equals("Homepage")){
    //         WebElement logoutButton = driver.findElement(By.id("logout-btn"));
    //         logoutButton.click();
    //     }
    //     loginPageFactory = new LoginPageFactory(driver);
    //     accountPageFactory = new AccountPageFactory(driver);
    //     listingPageFactory = new ListingPageFactory(driver);
    //     bannedPageFactory = new BannedPageFactory(driver);
    // }

    //Given
    @Given("a mod is logged in")
    public void a_mod_is_logged_in() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        loginPageFactory.login("user2","pass");
        Assert.assertEquals(driver.getTitle(), "Account");
        listings.clear();
        all_entries.clear();
        updated_listings.clear();
        updated_entries.clear();
        map.clear(); map2.clear();
    }

    //When
    @When("a mod clicks on mod menu")
    public void a_mod_clicks_on_mod_menu(){
        sleep(1);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.mod_menu_button.click();
    }

    @When("a mod selects a user")
    public void a_mod_selects_a_user_user_id() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.select_category(accountPageFactory.mod_user_category);
    }

    @When("clicks to ban")
    public void clicks_to_ban() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.mod_freeze_btn.click();
        sleep(1);
    }
    @When("clicks to unban")
    public void clicks_to_unban() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.mod_unfreeze_btn.click();
        sleep(1);
    }

    @When("mod logs out and user logs in")
    public void mod_logs_out_and_user_logs_in() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.logout_button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        loginPageFactory.login("user1","pass");
        //Assert.assertEquals(driver.getTitle(), "Account");
    }

    @When("a user creates a listing")
    public void a_user_creates_a_listing(){
        listings = driver.findElements(By.className("listing-row-div"));
        map.clear();
        accountPageFactory.initiate_map(listings, map);
        System.out.println("------------------- Listings ------------------");
        System.out.println(map);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.create_listing_button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.input_and_create_listing();
        //new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @When("they navigate to listing")
    public void they_navigate_to_listing(){
        sleep(1);
        System.out.println("------------------- New Listings ------------------");
        updated_listings = driver.findElements(By.className("listing-row-div"));
        sleep(1);
        System.out.println("--------------------- Navigate to Listing Page --------------------");
        int list_id = list_id_temp = accountPageFactory.get_new_listing(updated_listings, map);
        sleep(1);
        System.out.println("list_id: " + list_id);
        accountPageFactory.view_listing_using_element(list_id);
        sleep(1);
        String title = driver.getTitle();
        Assert.assertEquals(title, "Listing");
    }

    @When("a user creates an entry")
    public void a_user_creates_an_entry(){
        sleep(1);
        all_entries = driver.findElements(By.className("entry-row-div"));
        listingPageFactory.initiate_map(all_entries, map2);
        System.out.println("------------------- Entries  ------------------");
        System.out.println(map2);
        listingPageFactory.joinListingButton.click();
        sleep(1);
        listingPageFactory.input_and_create_entry();
        sleep(1);
        System.out.println("------------------- New Entries ------------------");
        updated_entries = driver.findElements(By.className("entry-row-div"));
        sleep(1);
        System.out.println(listingPageFactory.get_new_entry_button(updated_entries, map2)); 
    }

    @When("a user logs out and mod logs in")
    public void a_user_logs_out_and_mod_logs_in(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        accountPageFactory.logout_button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        loginPageFactory.login("user2","pass");
        Assert.assertEquals(driver.getTitle(), "Account");
    }

    @When("mod removes listing")
    public void mod_removes_listing(){
        sleep(1);
        //Setup
        updated_listings.clear();
        updated_listings = driver.findElements(By.className("listing-row-div"));
        sleep((long).5);
        int list_id = list_id_temp = accountPageFactory.get_new_listing(updated_listings, map);
        //Remove Listing
        listings.clear();
        listings = driver.findElements(By.className("listing-row-div"));
        sleep((long).5);
        int size = updated_listings.size();
        updated_listings.clear();
        accountPageFactory.remove_listing_using_element(list_id);
        updated_listings = driver.findElements(By.className("listing-row-div"));
        sleep(1);
        boolean hasChanged = updated_listings.size() < size;
        Assert.assertTrue(hasChanged);
    }

    @When("mod selects entry")
    public void mod_selects_entry(){
        sleep(2);
        updated_entries.clear();
        updated_entries = driver.findElements(By.className("entry-row-div"));
        WebElement button = listingPageFactory.get_new_entry_button(updated_entries, map2);
        sleep(1);
        button.click();
        sleep(1);
        System.out.println("--------------------- Navigate to Listing Page --------------------");
        //System.out.println("entry_id: " + entry_id_temp);
        // entry_id_temp = listingPageFactory.get_new_entry(updated_entries, map2);
        // listingPageFactory.click_select_element(entry_id_temp);
        sleep(2);
    }

    @When("mod opens update menu")
    public void mod_opens_update_menu(){
        sleep(1);
        listingPageFactory.update_selected_entry_btn.click();
        sleep(1);
    }

    @When("mod accepts entry")
    public void mod_accepts_entry(){
        System.out.println("I got here");
        updated_entries.clear();
        updated_entries = driver.findElements(By.className("entry-row-div"));
        sleep(1);
        entry_id_temp = listingPageFactory.get_new_entry(updated_entries, map2);
        sleep(1);
        //Update entry
        listingPageFactory.accept_btn.click();
        sleep(1);
        // //Check entries after
        System.out.println("Entry_id_temp: " + entry_id_temp);
        boolean result = listingPageFactory.check_entry_status(entry_id_temp, "Accepted");
        sleep(1);
        Assert.assertTrue(result);
        updated_entries.clear();
        updated_entries = driver.findElements(By.className("entry-row-div"));
    }

    @When("mod rejects entry")
    public void mod_rejects_entry(){
        System.out.println("I got here");
        updated_entries.clear();
        updated_entries = driver.findElements(By.className("entry-row-div"));
        sleep(1);
        entry_id_temp = listingPageFactory.get_new_entry(updated_entries, map2);
        sleep(1);
        //Update entry
        listingPageFactory.reject_btn.click();
        sleep(1);
        // //Check entries after
        System.out.println("Entry_id_temp: " + entry_id_temp);
        boolean result = listingPageFactory.check_entry_status(entry_id_temp, "Rejected");
        sleep(1);
        Assert.assertTrue(result);
        updated_entries.clear();
        updated_entries = driver.findElements(By.className("entry-row-div"));
    }

    @When("mod kicks entry")
    public void mod_kicks_entry(){
        System.out.println("I got here");
        updated_entries.clear();
        updated_entries = driver.findElements(By.className("entry-row-div"));
        sleep(1);
        entry_id_temp = listingPageFactory.get_new_entry(updated_entries, map2);
        sleep(1);
        //Update entry
        listingPageFactory.kick_btn.click();
        sleep(1);
        // //Check entries after
        updated_entries.clear();
        updated_entries = driver.findElements(By.className("entry-row-div"));
    }

    //Then
    @Then("banned user is on the ban page")
    public void user_is_on_ban_page() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        String title = driver.getTitle();
        Assert.assertEquals(title, "Banned!");
    }

    @Then("unbanned user is on the account page")
    public void user_is_on_unbanned_page() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        String title = driver.getTitle();
        Assert.assertEquals(title, "Account");
    }

    @Then("mod sees listing is gone")
    public void mod_sees_listing_is_gone(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        boolean hasChanged = listings.size() > updated_listings.size();
        Assert.assertTrue(hasChanged);
    }

    @Then("mod sees entry is gone")
    public void mod_sees_entry_is_gone(){
        //Go back to account
        listingPageFactory.accountPageButton.click();
        sleep(1);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        boolean hasChanged = updated_listings.size() > listings.size();
        Assert.assertTrue(hasChanged);
        //Remove listing
        //Setup
        updated_listings.clear();
        updated_listings = driver.findElements(By.className("listing-row-div"));
        int list_id = accountPageFactory.get_new_listing(updated_listings, map);
        //Remove Listing
        System.out.println("----------------Removing Listing------------------\nlist_id: " + list_id);
        sleep(1);
        listings.clear();
        listings = driver.findElements(By.className("listing-row-div"));
        sleep(1);
        int size = updated_listings.size();
        updated_listings.clear();
        accountPageFactory.remove_listing_using_element(list_id);
        updated_listings = driver.findElements(By.className("listing-row-div"));
        sleep(1);
        hasChanged = updated_listings.size() < size;
        Assert.assertTrue(hasChanged);
    }

    // @After
    // public void teardown(){
    //     if(!driver.getTitle().equals("Homepage")){
    //         WebElement logoutButton = driver.findElement(By.id("logout-btn"));
    //         logoutButton.click();
    //     }
    //     this.driver.quit();
    // }

    public void sleep(long x){
        try {
            TimeUnit.SECONDS.sleep(x);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

