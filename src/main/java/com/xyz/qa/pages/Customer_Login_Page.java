
package com.xyz.qa.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.xyz.qa.base.TestBase;

public class Customer_Login_Page extends TestBase {

    // Page Factory - Object Repository:
    @FindBy(xpath="//button[contains(text(),'Customer Login')]")
    WebElement customerLoginBtn;
    
    @FindBy(xpath="//select[@id='userSelect']")
    WebElement namedropdown;

    @FindBy(xpath="//button[normalize-space()='Login']")
    WebElement loginButton;  
    
    @FindBy(xpath="//button[normalize-space()='Logout']")
    WebElement logoutButton;
    
 


    // Initializing the Page Objects:
    public Customer_Login_Page(){
        PageFactory.initElements(driver, this);
    }
    
    // Method to perform customer login
    public void customerLogin(){
        customerLoginBtn.click();
    }
    
    // Method to get the login page title
    public String loginPageTitle() {
        return driver.getTitle();
    }
    
    // Method to select a customer from the drop-down
    public void selectCustomer(String customerName){
        Select select = new Select(namedropdown);
        select.selectByVisibleText(customerName);
    }
    
    // Method to click the login button
    public void clickLoginButton(){
        loginButton.click();
    }
    
    // Method to login as a customer and return the customer account page
    public Customer_Account_Page loginAsCustomer(String customerName) {
        customerLogin();
        selectCustomer(customerName);
        clickLoginButton();
        return new Customer_Account_Page();
    }  
    //Faced lot of challenge the x-path is even available when the button is not visible.
    public boolean isLoginButtonDisplayed() {
        try {
            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
            String classes = loginButton.getAttribute("class");
            System.out.println(classes);
            return !classes.contains("primary");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    

}
