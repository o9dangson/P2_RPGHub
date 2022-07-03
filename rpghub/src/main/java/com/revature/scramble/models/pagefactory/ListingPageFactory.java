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

    @FindBy(id = "logout-btn")
    WebElement logoutButton;

    @FindBy(id = "leave-btn")
    WebElement leaveGroupButton;

    @FindBy(id = "account-btn")
    WebElement accountPageButton;

    @FindBy(xpath = "html/body/div/div/div[1]/input")
    WebElement joinListingButton;

    @FindBy(id = "entry-role")
    WebElement roleCategoryDropdown;

    @FindBy(id = "desc-input")
    WebElement noteCategory;

    @FindBy(id = "create-button")
    WebElement submitButton;

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

}