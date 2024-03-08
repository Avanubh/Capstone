/*
 * This class contains test cases for the CustomerAccountPage functionalities.
 * It includes methods for:
 *   - Setting up the test environment before each test method execution
 *   - Verifying customer account details such as header name and customer name
 *   - Clicking on deposit and withdraw buttons
 */

package com.xyz.qa.testcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.xyz.qa.base.TestBase;
import com.xyz.qa.pages.Customer_Account_Page;
import com.xyz.qa.pages.Customer_Login_Page;

public class Customer_Account_Page_Test extends TestBase {
	TestBase testBase;
    Customer_Login_Page loginPage;
    Customer_Account_Page accountPage;
    
    public Customer_Account_Page_Test() {
        super();
    }
    
    // Setting up the test environment before each test method execution
    @BeforeMethod
    @Parameters("browser")
    public void setUp() {
        initialization();
        loginPage = new Customer_Login_Page();
        accountPage = loginPage.loginAsCustomer("Harry Potter");
    }
    
    // Verifying customer account details such as header name and customer name
    @Test
    public void verifyCustomerAccountDetails() {

    	//Verify name 
        String customerName = accountPage.getCustomerName();
        Assert.assertEquals(customerName, "Harry Potter");
        
        //Change the account number
        Customer_Account_Page.selectAccount("1005");
        
        WebElement Account = Customer_Account_Page.AccountNumber;
        String AccountNumberr = Account.getText();
        System.out.println("AccountNumberr"+Account);
        
        Assert.assertEquals(AccountNumberr, "1005");
        
       
        
    }
    
    // Closing the browser after each test method execution
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
