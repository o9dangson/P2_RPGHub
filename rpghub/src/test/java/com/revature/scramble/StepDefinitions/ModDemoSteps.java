package com.revature.scramble.StepDefinitions;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.revature.scramble.models.pagefactory.AccountPageFactory;
import com.revature.scramble.models.pagefactory.LoginPageFactory;
import com.revature.scramble.models.pagefactory.BannedPageFactory;
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
    public BannedPageFactory bannedPageFactory;

    List<WebElement> listings;
    List<WebElement> updated_listings;
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
    //     bannedPageFactory = new BannedPageFactory(driver);
    // }

    @Given("a mod is logged in")
    public void a_mod_is_logged_in() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        loginPageFactory.login("user2","pass");
        Assert.assertEquals(driver.getTitle(), "Account");
    }

    @When("a mod clicks on mod menu")
    public void a_mod_clicks_on_mod_menu(){
        accountPageFactory.mod_menu_button.click();
    }

    @When("a mod selects a user")
    public void a_mod_selects_a_user_user_id() {
        accountPageFactory.select_category(accountPageFactory.mod_user_category);
    }

    @When("clicks to ban")
    public void clicks_to_ban() {
        accountPageFactory.mod_freeze_btn.click();
    }
    @When("clicks to unban")
    public void clicks_to_unban() {
        accountPageFactory.mod_unfreeze_btn.click();
    }

    @When("mod logs out and user logs in")
    public void mod_logs_out_and_user_id_logs_in() {
        accountPageFactory.logout_button.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        loginPageFactory.login("user1","pass");
    }

    @Then("banned user is on the ban page")
    public void user_is_on_ban_page() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Banned!");
    }

    @Then("unbanned user is on the account page")
    public void user_is_on_unbanned_page() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "Account");
    }

//    @After
//    public void teardown(){
//        if(!driver.getTitle().equals("Homepage")){
//            WebElement logoutButton = driver.findElement(By.id("logout-btn"));
//            logoutButton.click();
//        }
//        this.driver.quit();
//    }
}

