package com.revature.scramble.StepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("http://localhost:9090/");
        if(!driver.getTitle().equals("Homepage")){
            WebElement logoutButton = driver.findElement(By.id("logout-btn"));
            logoutButton.click();
        }
        loginPageFactory = new LoginPageFactory(driver);
        accountPageFactory = new AccountPageFactory(driver);
        bannedPageFactory = new BannedPageFactory(driver);
    }

    @Given("a mod is logged in")
    public void a_mod_is_logged_in() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        loginPageFactory.login("user2","pass");
        Assert.assertEquals(driver.getTitle(), "Account");
    }

    @When("a mod clicks on mod menu")
    public void a_mod_clicks_on_mod_menu(){
        accountPageFactory.mod_menu_button.click()
    }

    @When("a mod selects a user \"(.*)\"")
    public void a_mod_selects_a_user_user_id(int user_id) {
        //
    }

    @When("clicks to \"(.*)\"")
    public void clicks_to_freeze_unfreeze(boolean freeze_unfreeze) {
        //
    }

    @When("mod logs out and \"(.*)\" logs in")
    public void mod_logs_out_and_user_id_logs_in(int user_id) {
        //
    }

    @Then("user is on \"(.*)\"")
    public void user_is_on_landing_page(String landing_page) {
        //
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

