/*
 * This class implements the WebDriverEventListener interface, allowing to listen to events triggered by the WebDriver.
 * It includes methods for:
 *   - Navigating to a URL
 *   - Changing the value of a web element
 *   - Clicking on a web element
 *   - Navigating back and forward
 *   - Handling exceptions and taking screenshots
 *   - Finding elements by various locators
 *   - Handling alerts and window switches
 */

package com.xyz.qa.util;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import com.xyz.qa.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {

    // Before navigating to a URL
    public void beforeNavigateTo(String url, WebDriver driver) {
        System.out.println("Before navigating to: '" + url + "'");
    }

    // After navigating to a URL
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("Navigated to:'" + url + "'");
    }

    // Before changing the value of a web element
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        System.out.println("Value of the:" + element.toString() + " before any changes made");
    }

    // After changing the value of a web element
    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        System.out.println("Element value changed to: " + element.toString());
    }

    // Before clicking on a web element
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("Trying to click on: " + element.toString());
    }

    // After clicking on a web element
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("Clicked on: " + element.toString());
    }

    // Before navigating back
    public void beforeNavigateBack(WebDriver driver) {
        System.out.println("Navigating back to previous page");
    }

    // After navigating back
    public void afterNavigateBack(WebDriver driver) {
        System.out.println("Navigated back to previous page");
    }

    // Before navigating forward
    public void beforeNavigateForward(WebDriver driver) {
        System.out.println("Navigating forward to next page");
    }

    // After navigating forward
    public void afterNavigateForward(WebDriver driver) {
        System.out.println("Navigated forward to next page");
    }

    // Handling exceptions
    public void onException(Throwable error, WebDriver driver) {
        System.out.println("Exception occured: " + error);
        try {
            TestUtil.takeScreenshotAtEndOfTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Before finding elements by locator
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Trying to find Element By : " + by.toString());
    }

    // After finding elements by locator
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Found Element By : " + by.toString());
    }



	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}


}
