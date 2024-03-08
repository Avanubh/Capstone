package com.xyz.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xyz.qa.base.TestBase;
import com.xyz.qa.pages.Customer_Account_Page;
import com.xyz.qa.pages.Customer_Withdraw_Page;
import com.xyz.qa.pages.Customer_Login_Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Customer_Withdraw_Page_Test extends TestBase {
    private Customer_Login_Page loginPage;
    private Customer_Account_Page accountPage;
    private Customer_Withdraw_Page withdrawPage;

    public Customer_Withdraw_Page_Test() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new Customer_Login_Page(); 
        accountPage = loginPage.loginAsCustomer("Hermoine Granger"); 
    }
    
    public static boolean containsOnlyDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


    @SuppressWarnings("static-access")
	private void performWithdrawal(int balance, int amountToWithdraw) {
    	withdrawPage = accountPage.clickWithdrawButton();
        Customer_Withdraw_Page.enterWithdrawAmount(String.valueOf(amountToWithdraw));
        withdrawPage.WithdrawButton();

        String withdrawStatusMessage = withdrawPage.getWithdrawStatusMessage();
        if (balance >= amountToWithdraw) {
            Assert.assertEquals(withdrawStatusMessage, "Transaction successful");
        } else {
            Assert.assertEquals(withdrawStatusMessage, "Transaction Failed. You cannot withdraw more than the balance.");
        }
    }

    @Test
    public void testWithdrawalFunctions() {
        // Get the current balance
        WebElement balanceElement = driver.findElement(By.xpath("//div[@class='center']/strong[2]"));
        String balanceText = balanceElement.getText();
        int balance = Integer.parseInt(balanceText);

        System.out.println("Amount available is " + balanceText);

        // Perform a withdrawal within the available balance
        int amountToWithdraw = 20;
        performWithdrawal(balance, amountToWithdraw);

        // Perform a withdrawal exceeding the available balance
        int largeAmount = balance + 1;
        performWithdrawal(largeAmount, amountToWithdraw);

        // Verify the remaining balance
        balanceText = balanceElement.getText();
        System.out.println("The remaining amount is " + balanceText);
        
        // Negative Scenarios 1 Check input does not take alphabet value
        
        
        Customer_Withdraw_Page.enterWithdrawAmount("abcd");

        // Verify that the input box does not contain any alphabet characters
        String amountEntered = Customer_Withdraw_Page.getWithdrawAmount();

        System.out.println("Input value: " + amountEntered);
        Assert.assertTrue(containsOnlyDigits(amountEntered), "Alphabets should not be allowed in the withdrawal amount input box");
    
        
     // Negative Scenarios 2 Get the initial balance
        
        balanceElement = driver.findElement(By.xpath("//div[@class='center']/strong[2]"));
        String initialBalanceText = balanceElement.getText();
        int initialBalance = Integer.parseInt(initialBalanceText);

        // Try to withdraw a negative amount
        Customer_Withdraw_Page.enterWithdrawAmount("-20");
        Customer_Withdraw_Page.WithdrawButton();

        // Verify that the balance remains the same
        String currentBalanceText = balanceElement.getText();
        int currentBalance = Integer.parseInt(currentBalanceText);
        Assert.assertEquals(currentBalance, initialBalance, "Balance should not change when withdrawing a negative amount");
        
        @SuppressWarnings("static-access")
		String requiredAttribute = withdrawPage.withdrawAmountInput.getAttribute("required");
        assert requiredAttribute != null : "Required attribute not found";
    }
    

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
