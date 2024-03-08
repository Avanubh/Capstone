/*
 * This class represents the Transaction Page in the XYZ application.
 * It contains methods to interact with elements on the page, such as the start date icon,
 * end date icon, and a method to click on the page to remove focus from the date picker.
 */

package com.xyz.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.xyz.qa.base.TestBase;

public class Customer_Transaction_History_Page extends TestBase {

    // Page Factory - Object Repository:
    @FindBy(xpath = "//input[@id='start']")
    WebElement startDateIcon;

    @FindBy(xpath = "//input[@id='end']")
    WebElement endDateIcon;
    
    @FindBy(xpath = "//body//div[@class='ng-scope']//div[@class='ng-scope']//div[2]")
    WebElement pageclick;
    
    // Initializing the Page Objects:
    public Customer_Transaction_History_Page(){
        PageFactory.initElements(driver, this);
    }

    // Method to set the start date in the date picker
    public void setStartDate(String startDate) {
        startDateIcon.sendKeys(startDate);
    }
    
    // Method to set the end date in the date picker
    public void setEndDate(String endDate) {
        endDateIcon.sendKeys(endDate);
    }

    // Method to click on the page to remove focus from the date picker
    public void pageClick() {
        pageclick.click();
    }
}
