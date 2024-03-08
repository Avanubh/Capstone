package com.xyz.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xyz.qa.base.TestBase;
import com.xyz.qa.pages.Customer_Account_Page;
import com.xyz.qa.pages.Customer_Withdraw_Page;
import com.xyz.qa.pages.Customer_Login_Page;
import com.xyz.qa.pages.Customer_Deposit_Page;
import com.xyz.qa.pages.Customer_Transaction_History_Page;

public class Customer_Transaction_Page_Test extends TestBase {
	public static long IMPLICIT_WAIT = 30;
    Customer_Login_Page loginPage;
    Customer_Account_Page accountPage;
    Customer_Withdraw_Page WithdrawPage;
    Customer_Deposit_Page depositPage;
    Customer_Transaction_History_Page transactionPage;
    
    public Customer_Transaction_Page_Test() {
        super();
    }
    
    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new Customer_Login_Page(); 
        accountPage = loginPage.loginAsCustomer("Hermoine Granger"); 
    }
    
    @Test(priority=5)
    public void depositWithdrawAndVerifyTransactions() {
        // Deposit
        
        // Go to transactions page
        transactionPage = accountPage.clickTransactionButton();
        
        //filter the date
        transactionPage.setStartDate("01-01-2015 00:00");

        transactionPage.setEndDate("03-01-2015 00:00");
    
        transactionPage.pageClick();
    }
    //@AfterMethod
    //public void tearDown() {
     //   driver.quit();
   // }
}
