package com.xyz.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xyz.qa.base.TestBase;
import com.xyz.qa.pages.Manager_Add_Customer_Page;

public class Manager_Add_Customer_Page_Test extends TestBase {
    Manager_Add_Customer_Page addCustomerPage;
    
    public Manager_Add_Customer_Page_Test() {
        super();
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        addCustomerPage = new Manager_Add_Customer_Page(); 
    }
    
    @Test
    public void verifyAddCustomerAlertMessage() {
        // Click on Bank Manager Login
        addCustomerPage.clickBankManagerLoginButton();
        
        // Click on Add Customer
        addCustomerPage.clickAddCustomerButton();
        
        //First Name is required
        String requiredAttribute = Manager_Add_Customer_Page.firstNameInput.getAttribute("required");
        assert requiredAttribute != null : "Required attribute not found";
        
        //Last name is required
        requiredAttribute = Manager_Add_Customer_Page.lastNameInput.getAttribute("required");
        assert requiredAttribute != null : "Required attribute not found";
        
        
        //Post-code is required
        requiredAttribute = Manager_Add_Customer_Page.postCodeInput.getAttribute("required");
        assert requiredAttribute != null : "Required attribute not found";
        
        // Enter customer details
        addCustomerPage.enterFirstName("Anubhav");
        addCustomerPage.enterLastName("Jha");
        addCustomerPage.enterPostCode("800020");
        
        // Click on Add Customer button
        addCustomerPage.clickSubmitButton();
        
        // Get the alert message text
        String alertMessage = addCustomerPage.getAlertMessageText();
        
        // Assert the alert message text
        Assert.assertTrue(alertMessage.contains("Customer added successfully"));

        
        // Click OK on the alert
        addCustomerPage.acceptAlert();
    }
    @Test
    public void verifyAddCustomerAlertMessageInvalidName() {
        // Click on Bank Manager Login
        addCustomerPage.clickBankManagerLoginButton();
        
        // Click on Add Customer
        addCustomerPage.clickAddCustomerButton();
        
        
        
        // Enter invalid customer details
        addCustomerPage.enterFirstName("12345");
        addCustomerPage.enterLastName("12345");
        addCustomerPage.enterPostCode("12345");
        
        // Click on Add Customer button
        addCustomerPage.clickSubmitButton();
        
        // Get the alert message text
        String alertMessage = addCustomerPage.getAlertMessageText();
        
        // Assert the alert message text
        Assert.assertTrue(alertMessage.contains("Numeric are not allowed in name"), "Alert message should indicate that numeric are not allowed in name fields");
        
        // Click OK on the alert
        addCustomerPage.acceptAlert();
    }
    
   
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
