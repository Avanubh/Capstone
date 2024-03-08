/*
 * This utility class provides common functions and methods used throughout the test automation framework.
 * It includes methods for:
 *   - Switching to frames
 *   - Retrieving test data from Excel sheets
 *   - Taking screenshots at the end of tests
 *   - Providing runtime information using JavaScriptExecutor
 */

package com.xyz.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.xyz.qa.base.TestBase;

public class TestUtil extends TestBase {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 30;
    public static String TESTDATA_SHEET_PATH = "C:\\Users\\Anubhav\\Downloads\\bank\\src\\main\\java\\com\\xyz\\qa\\testdata\\FreeCrmTestData.xlsx";

    static Workbook book;
    static Sheet sheet;
    static JavascriptExecutor js;

    // Method to switch to frame
    public void switchToFrame() {
        driver.switchTo().frame("mainpanel");
    }

    // Method to retrieve test data from Excel sheet
    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
            }
        }
        return data;
    }

    // Method to take screenshot at the end of test
    public static void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
    }

    // Method to provide runtime information using JavaScriptExecutor
    public static void runTimeInfo(String messageType, String message) throws InterruptedException {
        js = (JavascriptExecutor) driver;
        js.executeScript("if (!window.jQuery) {"
                + "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
                + "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
                + "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
        Thread.sleep(5000);

        js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");
        js.executeScript("$('head').append('<link rel=\"stylesheet\" "
                + "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
        Thread.sleep(5000);

        js.executeScript("$.growl({ title: 'GET', message: '/' });");

        if (messageType.equals("error")) {
            js.executeScript("$.growl.error({ title: 'ERROR', message: '" + message + "' });");
        } else if (messageType.equals("info")) {
            js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
        } else if (messageType.equals("warning")) {
            js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
        } else
            System.out.println("no error message");
        Thread.sleep(5000);
    }
}
