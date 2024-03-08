/*
 * This class represents the page for managing customers by the bank manager in the XYZ application.
 * It contains methods to interact with elements on the page, such as the bank manager login button,
 * customers button, search input field, customer details like first name, last name, post code,
 * account number, and the delete button.
 */

package com.xyz.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.xyz.qa.base.TestBase;

public class Manager_Customers_Page extends TestBase {

    // Page Factory - Object Repository:
    @FindBy(xpath = "//button[normalize-space()='Bank Manager Login']")
    WebElement bankManagerLoginButton;

    @FindBy(xpath = "//button[normalize-space()='Customers']")
    WebElement customersButton;

    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    WebElement searchCustomerInput;

    @FindBy(xpath = "//td[normalize-space()='Albus']")
    WebElement firstName;

    @FindBy(xpath = "//td[normalize-space()='Dumbledore']")
    WebElement lastName;

    @FindBy(xpath = "//td[normalize-space()='E55656']")
    WebElement postCode;

    @FindBy(xpath = "//tbody//td[4]")
    WebElement accountNumber;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    WebElement deleteButton;
    
    
    @FindBy(tagName="tbody")
    public static WebElement tbody;

    // Initializing the Page Objects:
    public Manager_Customers_Page() {
        PageFactory.initElements(driver, this);
    }

    // Method to click the bank manager login button
    public void clickBankManagerLoginButton() {
        bankManagerLoginButton.click();
    }

    // Method to click the customers button
    public void clickCustomersButton() {
        customersButton.click();
    }

    // Method to search for a customer using the search input field
    public void searchCustomer(String customerName) {
        searchCustomerInput.sendKeys(customerName);
    }

    // Method to get the first name of the customer
    public String getFirstName() {
        return firstName.getText();
    }

    // Method to get the last name of the customer
    public String getLastName() {
        return lastName.getText();
    }

    // Method to get the post code of the customer
    public String getPostCode() {
        return postCode.getText();
    }

    // Method to get the account number of the customer
    public String getAccountNumber() {
        return accountNumber.getText();
    }

    // Method to click the delete button to delete the customer
    public void clickDeleteButton() {
        deleteButton.click();
        
    }
}
