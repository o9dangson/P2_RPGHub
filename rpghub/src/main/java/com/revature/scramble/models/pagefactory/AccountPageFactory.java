package com.revature.scramble.models.pagefactory;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountPageFactory {

    WebDriver webdriver;

    @FindBy(id = "view-btn")
    WebElement view_selected_listing_button;

    @FindBy(xpath = "/html/body/div/div[2]/div[1]/input")
    WebElement filter_button;

    @FindBy(xpath = "/html/body/div/div[2]/div[2]/input")
    WebElement create_listing_button;

    @FindBy(xpath = "delete-btn")
    WebElement delete_button;

    @FindBy(id = "logout-btn")
    WebElement logout_button;

    @FindBy(id = "filter-category")
    WebElement filter_category_dropdown;

    @FindBy(id = "specific-filter-input")
    WebElement specific_filter_input;

    @FindBy(className = "filter-submit")
    WebElement filter_listing_submit_button;

    @FindBy(id = "create-list-name")
    WebElement list_name_field;

    @FindBy(id = "create-dungeon-name")
    WebElement dungeon_name_field;

    @FindBy(id = "create-max-size")
    WebElement max_size_field;

    @FindBy(id = "listing-submit")
    WebElement listing_submit_button;


    public AccountPageFactory(WebDriver webDriver){
        this.webdriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }

    private WebElement get_select_listing_element(){
        List<WebElement> all_select_buttons = webdriver.findElements(By.className("view-listing-button"));
        int btn_count = all_select_buttons.size();
        return all_select_buttons.get((int)Math.random()*btn_count);
    }

    public void click_select_listing_button(){
        get_select_listing_element().click();
    }

    public void click_filter_button() {
        filter_button.click();
    }

    public void select_category(){
        Select category_dropdown = new Select(filter_category_dropdown);
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
        int rand_max_size = rand.nextInt(max_size);
        max_size+=min_size;
        max_size_field.sendKeys(String.valueOf(rand_max_size));
    }

    public void click_listing_submit_button(){
        listing_submit_button.click();
    }





}
