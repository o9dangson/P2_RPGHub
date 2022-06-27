package com.revature.scramble.models.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
    WebDriver webdriver;

    @FindBy(id = "username")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "sign-in-btn")
    WebElement loginButton;

    public LoginPageFactory(WebDriver webDriver){
        this.webdriver = webDriver;

        PageFactory.initElements(webDriver, this);
    }


    public void login(String username, String password){
        this.inputUsername(username);
        this.inputPassword(password);
        this.clickLoginButton();
    }


    public void clickLoginButton() {
        loginButton.click();
    }


    public void inputPassword(String password) {
        passwordInput.sendKeys(password);
    }


    public void inputUsername(String username) {
        usernameInput.sendKeys(username);
    }
  
}