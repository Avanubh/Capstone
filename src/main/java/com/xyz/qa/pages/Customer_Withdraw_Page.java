/*
 * This class represents the Withdraw Page in the XYZ application.
 * It contains methods to interact with elements on the page, such as the withdraw amount input field,
 * withdraw button, and methods to retrieve the withdraw status message and last transaction amount.
 */

package com.xyz.qa.pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.xyz.qa.base.TestBase;

public class Customer_Withdraw_Page extends TestBase {

    // Page Factory - Object Repository:
    @FindBy(xpath="//input[@placeholder='amount']")
    public static WebElement withdrawAmountInput;
    
    @FindBy(xpath="//button[normalize-space()='Withdraw']")
    static WebElement withdrawButton;
    
    @FindBy(xpath="//span[@class='error ng-binding']")
    static WebElement withdrawStatusMessage;
    
    @FindBy(xpath="//button[normalize-space()='Transactions']")
    static WebElement transcationclick;
    
    @FindBy(xpath="//td[normalize-space()='100']")
    static WebElement lastTransactionAmount;

    // Initializing the Page Objects:
    public Customer_Withdraw_Page(){
        PageFactory.initElements(driver, this);
    }
    
    public static String getWithdrawAmount() {
        return withdrawAmountInput.getAttribute("value").replaceAll("\\D", "");
    }

    
    // Method to enter withdraw amount
    public static void enterWithdrawAmount(String amount) {
        withdrawAmountInput.clear(); // Clear the input field 
        withdrawAmountInput.sendKeys(amount);
    }
    
    // Method to click on withdraw button
    public static void WithdrawButton() {
        withdrawButton.click();
    }
    
    // Method to retrieve the withdraw status message
    public String getWithdrawStatusMessage() {
        return withdrawStatusMessage.getText();
    }
    
    // Method to click on the Transactions button
    public void clickTransaction() {
        transcationclick.click();
    }
    
    // Method to retrieve the last transaction amount
    public String getLastTransactionAmount() {
        return lastTransactionAmount.getText();
    }

    public boolean isWithdrawalFormDisplayed() {
        try {
            WebElement withdrawalForm = driver.findElement(By.xpath("//div[@class='container-fluid mainBox ng-scope']"));
            return withdrawalForm.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
