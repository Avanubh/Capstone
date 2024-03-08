/*
 * This class contains test cases for the LoginPage functionalities.
 * It includes methods for:
 *   - Setting up the test environment before each test method execution
 *   - Testing the title of the login page
 *   - Performing customer login
 *   - Logging in with customer credentials and performing further verifications
 */

package com.xyz.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xyz.qa.base.TestBase;
import com.xyz.qa.pages.Customer_Login_Page;

public class Customer_Login_Page_Test extends TestBase {
    Customer_Login_Page loginPage;
    
    public Customer_Login_Page_Test() {
        super();
    }
    
    // Setting up the test environment before each test method execution
    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new Customer_Login_Page(); 
    }
    
    // Testing the title of the login page
    @Test
    public void loginPageTitleTest() {
        String title = loginPage.loginPageTitle();
        //btn btn-primary btn-lg
        Assert.assertEquals(title, "XYZ Bank");
    }
    

    // Logging in with customer credentials and performing further verifications
    @Test(priority=0)
    public void loginTest() {
    	String name = "Ron Weasly";
        loginPage.customerLogin();
        loginPage.selectCustomer(name);
        loginPage.clickLoginButton();
        
     // Wait until the login is completed and the specific element is visible
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='fontBig ng-binding']")));

        // Assertions or further verifications here to ensure successful login
        Assert.assertEquals(element.getText(), name, "Customer name does not match");

    }
    // Check that the login button is not displayed until a name is selected
    @Test(priority=1)
    public void loginButtonNotDisplayedTest() {
        // Verify that the login button is not displayed initially
        Assert.assertFalse(loginPage.isLoginButtonDisplayed(), "Login button should not be displayed initially");

        // Select a customer name
        loginPage.customerLogin();
        loginPage.selectCustomer("Ron Weasly");

        // Verify that the login button is displayed after selecting a name
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed after selecting a name");
    }

     //Closing the browser after each test method execution
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
