package com.revature.scramble.models.pagefactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountPageFactory {

    public WebDriver webdriver;

    @FindBy(id = "view-btn")
    public WebElement view_selected_listing_button;

    //Moderator
    @FindBy(id= "mod-collapse-btn")
    public WebElement mod_menu_button;

    @FindBy(id= "account-category")
    public WebElement mod_user_category;

    @FindBy(id= "mod-freeze-btn")
    public WebElement mod_freeze_btn;
    
    @FindBy(id= "mod-unfreeze-btn")
    public WebElement mod_unfreeze_btn;

    //Filter Listings
    @FindBy(id = "filter-collapse-btn")
    public WebElement filter_button;

    @FindBy(id = "filter-category")
    public WebElement filter_category_dropdown;

    @FindBy(id = "specific-filter-input")
    public WebElement specific_filter_input;

    @FindBy(id = "filter-submit")
    public WebElement filter_listing_submit_button;

    //Create Listings
    @FindBy(id = "create-collapse-btn")
    public WebElement create_listing_button;

    @FindBy(id = "create-list-name")
    public WebElement list_name_field;

    @FindBy(id = "create-dungeon-name")
    public WebElement dungeon_name_field;

    @FindBy(id = "create-max-size")
    public WebElement max_size_field;

    @FindBy(id = "listing-submit")
    public WebElement listing_submit_button;


    //Other Buttons

    @FindBy(id = "delete-btn")
    public WebElement delete_button;

    @FindBy(id = "logout-btn")
    public WebElement logout_button;

    
    List<WebElement> list_of_listings;

    public AccountPageFactory(WebDriver webDriver){
        this.webdriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }

    private WebElement get_select_listing_element(){
        WebElement select_listing = webdriver.findElement(By.xpath("/html/body/div/div[6]/div[2]/div/div[1]/div[8]/input"));
        return select_listing;
    }

    public void click_select_listing_button(){
        get_select_listing_element().click();
    }


    public void select_category(WebElement dropdownMenu){
        Select category_dropdown = new Select(dropdownMenu);
        List<WebElement> categories = category_dropdown.getOptions();
        
        int random_category = (int)Math.random()*categories.size();
        category_dropdown.selectByIndex(random_category);
    }

    public void input_specific_filter(String specific_filter){
        specific_filter_input.sendKeys(specific_filter);
    }

    public void apply_filter(){
        filter_button.click();
    }

    public void click_create_listing_button() {
        create_listing_button.click();
    }

    public void enter_listing_name(String list_name){
        list_name_field.sendKeys(list_name);
    }

    public void input_dungeon(String dungeon_name) {
        dungeon_name_field.sendKeys(dungeon_name);
    }

    public void list_max_party_size() {
        Random rand = new Random();
        int max_size = 8;
        int min_size = 2;
        int rand_max_size = rand.nextInt(max_size)+min_size;
        max_size_field.sendKeys(String.valueOf(rand_max_size));
    }

    public void click_listing_submit_button(){
        listing_submit_button.click();
    }

    public void view_selected_listing() {
        view_selected_listing_button.click();
    }

    public Boolean listing_selected(){
        String remove_button_class = delete_button.getAttribute("class");
        if(remove_button_class.equals("btn btn-danger")){
            return true;
        }
        else{
            return false;
        }
    }
    public void click_remove_listing_button() {
        delete_button.click();
    }
}
