
package com.xyz.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.xyz.qa.base.TestBase;

public class Customer_Account_Page extends TestBase {

    // Page Factory - Object Repository:
    @FindBy(xpath="//strong[@class='mainHeading']")
    WebElement headerName;

    @FindBy(xpath="//span[@class='fontBig ng-binding']")
    WebElement customerName;

    @FindBy(xpath="//button[normalize-space()='Transactions']")
    WebElement transactionButton;

    @FindBy(xpath="//button[normalize-space()='Deposit']")
    WebElement depositButton;
    
    @FindBy(xpath="//button[normalize-space()='Withdrawl']")
    WebElement withdrawalButton;
    
    @FindBy(xpath="//select[@id='accountSelect']")
	public static
    WebElement namedropdown;
    
    @FindBy(xpath="//div[@class='center']/strong[1]")
	public static
   	WebElement AccountNumber;
    
    
    public static void selectAccount(String AccountNumber){
        Select select = new Select(namedropdown);
        select.selectByVisibleText(AccountNumber);
    }

    // Initializing the Page Objects:
    public Customer_Account_Page(){
        PageFactory.initElements(driver, this);
    }

    // Method to get the header name
    public String getHeaderName() {
        return headerName.getText();
    }

    // Method to get the customer name
    public String getCustomerName() {
        return customerName.getText();
    }

    // Method to click on the transaction button and return to the transaction page
    public Customer_Transaction_History_Page clickTransactionButton(){
        transactionButton.click();
        return new Customer_Transaction_History_Page();
    }
    
    // Method to click on the deposit button and return to the deposit page
    public Customer_Deposit_Page clickDepositButton(){
        depositButton.click();
        return new Customer_Deposit_Page();
    }

    // Method to click on the withdrawal button and return to the withdrawal page
    public Customer_Withdraw_Page clickWithdrawButton(){
        withdrawalButton.click();
        return new Customer_Withdraw_Page();
    }
}
