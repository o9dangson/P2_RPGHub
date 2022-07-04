package com.revature.scramble.models.pagefactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.messages.types.Duration;

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

    public WebElement select_entry(int random){
        List<WebElement> entries = webdriver.findElements(By.className("entry-row-div"));
        return entries.get(random);
    }

    public WebElement selected_entry_status(int random){
        List<WebElement> entry_statuses = webdriver.findElements(By.className("entry-status"));
        return entry_statuses.get(random);
    }

    public void click_update_selected_entry(){
        update_selected_entry_btn.click();
    }

    public void click_accept_button(){
        
        accept_btn.click();
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

    public void initiate_map(List<WebElement> list, Map<String, Integer> map){
        map.clear();
        for(WebElement ele: list){
            String innerHTML = get_entry_row_id(ele);
            map.put(innerHTML, Integer.parseInt(innerHTML));
        }
    }

    public String get_entry_row_id(WebElement ele){
        String innerHTML = ele.findElement(By.xpath(".//div[7]/p")).getAttribute("innerHTML");
        System.out.println("\tentry: " + innerHTML);
        return innerHTML;
    }

    public void input_and_create_entry(){
        webdriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        Select dropdown = new Select(roleCategoryDropdown);
        dropdown.selectByIndex(1);
        noteCategory.sendKeys("I am the best");
        submitButton.click();
    }

    public WebElement get_new_entry_button(List<WebElement> updated_entries, Map<String, Integer> map){
        //int new_entry_id = -1;
        WebElement btn = update_selected_entry_btn;
        for(WebElement ele: updated_entries){
            String innerHTML = ele.findElement(By.xpath(".//div[6]/input")).getAttribute("innerHTML");
            if(!map.containsKey(get_entry_row_id(ele))){
                //new_entry_id = Integer.parseInt(get_entry_row_id(ele));
                btn = ele.findElement(By.xpath("..//div[6]/input"));
                break;
            }
        }
        //System.out.println("new_entry_id: " + new_entry_id);
        return btn;
    }

    public int get_new_entry(List<WebElement> updated_entries, Map<String, Integer> map){
        int new_entry_id = -1;
        for(WebElement ele: updated_entries){
            if(!map.containsKey(get_entry_row_id(ele))){
                new_entry_id = Integer.parseInt(get_entry_row_id(ele));
                break;
            }
        }
        System.out.println("new_entry_id: " + new_entry_id);
        return new_entry_id;
    }

    public void click_select_element(int entry_id){
        WebElement button = get_entry_row_button(entry_id);
        button.click();
        System.out.println("VIEW BUTTON ACTIVATED");
        webdriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        while(!entry_selected()){
            break;
        }
    }

    public WebElement get_entry_row_button(int entry_id){
        String button_id = String.format("select-entry-%s", entry_id);
        WebElement button = webdriver.findElement(By.id(button_id));
        System.out.println("\tbutton_id: " + button.getAttribute("id"));
        return button;
    }

    public WebElement get_entry_status(int entry_id){
        String entry_status_id = String.format("entry-status-%s", entry_id);
        WebElement status = webdriver.findElement(By.id(entry_status_id));
        System.out.println("\tstatus: " + status.getAttribute("id"));
        return status;
    }

    public Boolean entry_selected(){
        String accept_btn_class = accept_btn.getAttribute("class");
        if(accept_btn_class.equals("btn btn-success")){
            return true;
        }
        else{
            return false;
        }
    }

    public void wait_selected_to_load(){
        update_selected_entry_btn.click();
        while(!entry_selected()){
            break;
        }
    }

    public boolean check_entry_status(int entry_id, String status){
        boolean match = false;
        WebElement status_element = get_entry_status(entry_id);
        if(status_element.getAttribute("innerHTML").equals(status))
            match = true;
        return match;
    }
} 