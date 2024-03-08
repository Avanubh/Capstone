package com.xyz.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xyz.qa.base.TestBase;
import com.xyz.qa.pages.Manager_Customers_Page;

public class Manager_Customers_Page_Test extends TestBase {
    Manager_Customers_Page customersPage;
    
    public Manager_Customers_Page_Test() {
        super();
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        customersPage = new Manager_Customers_Page(); 
    }
    
    @Test
    public void verifyCustomerDetailsAndDelete() {
        // Click on Bank Manager Login
        customersPage.clickBankManagerLoginButton();
        
        // Click on Customers
        customersPage.clickCustomersButton();
        
        // Search for customer by name
        customersPage.searchCustomer("Albus");
        
        // Verify customer details
        String firstName = customersPage.getFirstName();
        String lastName = customersPage.getLastName();
        String postCode = customersPage.getPostCode();
        String accountNumber = customersPage.getAccountNumber();
        
        Assert.assertEquals(firstName, "Albus");
        Assert.assertEquals(lastName, "Dumbledore");
        Assert.assertEquals(postCode, "E55656");
        Assert.assertEquals(accountNumber, "1010 1011 1012");
        
        
        // Click on Delete
        customersPage.clickDeleteButton();
        
     // Verify that no details are available after delete
        
        // Get all table rows in the table body
        @SuppressWarnings("static-access")
		java.util.List<WebElement> rows = customersPage.tbody.findElements(By.tagName("tr"));
        // Assert that there are no rows 
        boolean itemFound = false;
        for (WebElement row : rows) {
            if (row.getText().contains("Hermoine")) {
                itemFound = true;
                break;
            }
        }
        Assert.assertFalse(itemFound, "Item is still present after deletion");

        
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
