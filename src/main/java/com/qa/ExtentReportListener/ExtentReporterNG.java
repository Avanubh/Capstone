/*
 * This class implements the IReporter interface provided by TestNG to generate extent reports.
 * It creates an ExtentReports instance and generates a report in HTML format.
 * The report includes information about passed, failed, and skipped tests.
 */
package com.qa.ExtentReportListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;

    // Generates the report based on the provided XML suites and suites
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
            String outputDirectory) {
        // Initializes the ExtentReports instance and specifies the output directory and file name
        extent = new ExtentReports(outputDirectory + File.separator
                + "Extent.html", true);

        // Iterates over the suites to collect test results
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            // Iterates over the results of each suite
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                // Builds test nodes for passed, failed, and skipped tests
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }

        // Flushes and closes the ExtentReports instance
        extent.flush();
        extent.close();
    }

    // Builds test nodes for a specific result map (passed, failed, or skipped tests)
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;

        // Checks if there are any tests in the result map
        if (tests.size() > 0) {
            // Iterates over the test results
            for (ITestResult result : tests.getAllResults()) {
                // Starts a new test node in the extent report
                test = extent.startTest(result.getMethod().getMethodName());

                // Sets the start and end times for the test
                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));

                // Assigns test categories based on groups
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                // Logs the test status and any associated throwable
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase()
                            + "ed");
                }

                // Ends the test node in the extent report
                extent.endTest(test);
            }
        }
    }

    // Converts milliseconds to a Date object
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
