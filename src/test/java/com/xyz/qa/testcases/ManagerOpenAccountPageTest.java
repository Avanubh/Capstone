package com.xyz.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xyz.qa.base.TestBase;
import com.xyz.qa.pages.Manager_Open_Account_Page;

public class ManagerOpenAccountPageTest extends TestBase {
    Manager_Open_Account_Page openAccountPage;
    
    public ManagerOpenAccountPageTest() {
        super();
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        openAccountPage = new Manager_Open_Account_Page(); 
    }
    
    @Test
    public void verifyOpenAccountAlertMessage() {
        // Click on Bank Manager Login
        openAccountPage.clickBankManagerLoginButton();
        
        // Click on Open Account
        openAccountPage.clickOpenAccountButton();
        
        // Select customer name from drop-down
        openAccountPage.selectCustomerName("Ron Weasly");
        
        // Select currency from drop-down
        openAccountPage.selectCurrency("Rupee");
        
        // Click on Process button
        openAccountPage.clickProcessButton();
        
        // Get the alert message text
        String alertMessage = openAccountPage.getAlertMessageText();
        
        // Assert the alert message text
        Assert.assertTrue(alertMessage.contains("Account created successfully"));
        
        // Click OK on the alert
        openAccountPage.acceptAlert();
        
        //Validate customer
        String requiredAttribute = Manager_Open_Account_Page.customerNameDropdown.getAttribute("required");
        assert requiredAttribute != null : "Required attribute not found";
        
        //Validate Currency
        requiredAttribute = Manager_Open_Account_Page.currencyDropdown.getAttribute("required");
        assert requiredAttribute != null : "Required attribute not found";
        
        
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
