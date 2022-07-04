package com.revature.scramble.models.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

public class ListingPageFactory {
    WebDriver webdriver;

    @FindBy(id = "account-btn")
    public WebElement accountPageButton;

    @FindBy(id = "leave-btn")
    public WebElement leaveGroupButton;
    
    @FindBy(id = "logout-btn")
    public WebElement logoutButton;

    //Update Selected Collapse
    @FindBy(id = "accept-reject-collapse-btn")
    public WebElement update_selected_entry_btn;

    @FindBy(id = "accept-button")
    public WebElement accept_btn;

    @FindBy(id = "reject-button")
    public WebElement reject_btn;
    
    @FindBy(id = "kick-button")
    public WebElement kick_btn;

    //Join Listing Collapse
    @FindBy(id = "create-collapse-btn")
    public WebElement joinListingButton;

    @FindBy(id = "entry-role")
    public WebElement roleCategoryDropdown;

    @FindBy(id = "desc-input")
    public WebElement noteCategory;

    @FindBy(id = "create-button")
    public WebElement submitButton;

    public ListingPageFactory(WebDriver webDriver){
        this.webdriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    
    public void clickLogoutButton(){
        logoutButton.click();
    }

    public void clickAccountPageButton(){
        accountPageButton.click();
    }

    public void select_role(){
        Select user_role_select = new Select(roleCategoryDropdown);
        List<WebElement> categories = user_role_select.getOptions();

        int random_role = (int)Math.random()*categories.size();
        user_role_select.selectByIndex(random_role);
    }

    public void input_user_note(String user_note){
        noteCategory.sendKeys(user_note);
    }


    public void clickLeaveGroupButton(){
        leaveGroupButton.click();
    }

    public void clickJoinListingButton(){
        joinListingButton.click();
    }

    public void clickSubmitButton(){
        submitButton.click();
    }
} 