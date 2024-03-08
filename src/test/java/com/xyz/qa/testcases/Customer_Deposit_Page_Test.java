/*
 * This class contains test cases for the DepositPage functionalities.
 * It includes methods for:
 *   - Setting up the test environment before each test method execution
 *   - Depositing an amount and verifying the transaction
 */

package com.xyz.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xyz.qa.base.TestBase;
import com.xyz.qa.pages.Customer_Account_Page;
import com.xyz.qa.pages.Customer_Deposit_Page;
import com.xyz.qa.pages.Customer_Login_Page;

public class Customer_Deposit_Page_Test extends TestBase {
    Customer_Login_Page loginPage;
    Customer_Account_Page accountPage;
    Customer_Deposit_Page depositPage;
    
    public Customer_Deposit_Page_Test() {
        super();
    }
    
    // Setting up the test environment before each test method execution
    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new Customer_Login_Page(); 
        accountPage = loginPage.loginAsCustomer("Harry Potter"); 
    }
    
    public static boolean containsOnlyDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
    // Depositing an amount and verifying the transaction
    @Test
    public void depositAmountAndVerifyTransaction() {
    	

        WebElement balanceElement = driver.findElement(By.xpath("//div[@class='center']/strong[2]"));
        
        // Click on deposit button
        depositPage = accountPage.clickDepositButton();
        
        // Enter deposit amount
        depositPage.enterDepositAmount("1000"); 
        
        // Click on deposit button
        depositPage.clickDepositButton();
        
        // Verify deposit status message
        String depositStatusMessage = depositPage.getDepositStatusMessage();
        Assert.assertEquals(depositStatusMessage, "Deposit Successful");
        
        
        // Negative Scenarios 1 Check input does not take alphabet value
        
        
        depositPage.enterDepositAmount("abcd");

        // Verify that the input box does not contain any alphabet characters
        @SuppressWarnings("static-access")
		String amountEntered = depositPage.getDepositAmount();

        System.out.println("Input value: " + amountEntered);
        Assert.assertTrue(containsOnlyDigits(amountEntered), "Alphabets should not be allowed in the withdrawal amount input box");
    
        
     // Negative Scenarios 2 Get the initial balance
        
        balanceElement = driver.findElement(By.xpath("//div[@class='center']/strong[2]"));
        String initialBalanceText = balanceElement.getText();
        int initialBalance = Integer.parseInt(initialBalanceText);

        // Try to withdraw a negative amount
        depositPage.enterDepositAmount("-1000");
        depositPage.clickDepositButton();

        // Verify that the balance remains the same
        String currentBalanceText = balanceElement.getText();
        int currentBalance = Integer.parseInt(currentBalanceText);
        Assert.assertEquals(currentBalance, initialBalance, "Balance should not change when withdrawing a negative amount");
        
        //Verify input box is not blank
        @SuppressWarnings("static-access")
		String requiredAttribute = depositPage.depositAmountInput.getAttribute("required");
        assert requiredAttribute != null : "Required attribute not found";
    }
    
    // Closing the browser after each test method execution
    @AfterMethod
    public void tearDown() {
        driver.quit();
   }
}
