package com.revature.scramble.models.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(className = "listing-submit")
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



}
