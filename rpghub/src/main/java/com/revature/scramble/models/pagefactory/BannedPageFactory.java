package com.revature.scramble.models.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BannedPageFactory {
    WebDriver webdriver;

    @FindBy(id = "logout-btn")
    WebElement logoutButton;

    public BannedPageFactory(WebDriver webDriver){
        this.webdriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }

    public void clickLogoutButton(){
        logoutButton.click();
    }
}