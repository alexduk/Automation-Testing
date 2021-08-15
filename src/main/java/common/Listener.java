package common;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static common.HtmlCommons.*;

public class Listener implements IReporter {

    protected PrintWriter writer;
    protected PrintWriter passedTestsWriter;
    protected PrintWriter failedTestsWriter;
    protected PrintWriter skippedTestsWriter;
    private final String reportTitle = "TestNG Customized Report";
    private final String passedTestsReportFileName = "testng-custom-passed-tests-report.html";
    private final String failedTestsReportFileName = "testng-custom-failed-tests-report.html";
    private final String skippedTestsReportFileName = "testng-custom-skipped-tests-report.html";

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        try {
            String reportFileName = "testng-custom-report.html";
            writer = createWriter(outputDirectory, reportFileName);
            passedTestsWriter = createWriter(outputDirectory, passedTestsReportFileName);
            failedTestsWriter = createWriter(outputDirectory, failedTestsReportFileName);
            skippedTestsWriter = createWriter(outputDirectory, skippedTestsReportFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int totalTestsCount = 0;
        int totalPassedTests = 0;
        int totalSkippedTests = 0;
        int totalFailedTests = 0;

        List<String[]> passedTestsDetails = new ArrayList<>();
        List<String[]> failedTestsDetails = new ArrayList<>();
        List<String[]> skippedTestsDetails = new ArrayList<>();

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            String suiteName = suite.getName();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                String testName = tc.getName();
                int passedTests = tc.getPassedTests().getAllResults().size();
                int skippedTests = tc.getSkippedTests().getAllResults().size();
                int failedTests = tc.getFailedTests().getAllResults().size();

                totalTestsCount += passedTests + skippedTests + failedTests;
                totalPassedTests += passedTests;
                totalSkippedTests += skippedTests;
                totalFailedTests += failedTests;

                Set<ITestResult> passedTestResults = tc.getPassedTests().getAllResults();
                Set<ITestResult> failedTestResults = tc.getFailedTests().getAllResults();
                Set<ITestResult> skippedTestResults = tc.getSkippedTests().getAllResults();

                List<String[]> suitePassedTestsDetails = getTestsDetails(passedTestResults, suiteName, testName);
                List<String[]> suiteFailedTestsDetails = getTestsDetails(failedTestResults, suiteName, testName);
                List<String[]> suiteSkippedTestsDetails = getTestsDetails(skippedTestResults, suiteName, testName);

                passedTestsDetails.addAll(suitePassedTestsDetails);
                failedTestsDetails.addAll(suiteFailedTestsDetails);
                skippedTestsDetails.addAll(suiteSkippedTestsDetails);
            }
        }

        writeReportHome(writer, totalTestsCount, totalPassedTests, totalSkippedTests, totalFailedTests);
        writePassedTests(passedTestsWriter, passedTestsDetails);
        writeFailedTests(failedTestsWriter, failedTestsDetails);
        writeSkippedTests(skippedTestsWriter, skippedTestsDetails);
    }

    private void writeReportHome(PrintWriter writer, int totalTestsCount, int totalPassedTests, int totalSkippedTests, int totalFailedTests) {

        writeBeforeBody(writer, reportTitle);

        writer.println("<div class='selenium.test-overview'>");
        writer.println("<table class='table-bordered overview'>");
        writer.print("<tr>");

        writer.print("<th>All Tests</th>");
        writer.print("<th>Count</th>");

        writer.println("</tr>");

        writer.print("<tr>");
        writer.print("<th style=\"background-color:#32CD32;\"><a href=" + passedTestsReportFileName + ">Passed Tests</a></th>");
        writer.print("<th style=\"background-color:#32CD32;\">" + totalPassedTests + "</th>");
        writer.println("</tr>");

        writer.print("<tr>");
        writer.print("<th style=\"background-color:#FF4500;\"><a href=" + failedTestsReportFileName + ">Failed Tests</a></th>");
        writer.print("<th style=\"background-color:#FF4500;\">" + totalFailedTests + "</th>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.print("<th><a href=" + skippedTestsReportFileName + ">Skipped Tests</a></th>");
        writer.print("<th>" + totalSkippedTests + "</th>");
        writer.print("</tr>");

        writer.print("<tr>");
        writer.print("<th>Total Tests</th>");
        writer.print("<th>" + totalTestsCount + "</th>");
        writer.print("</tr>");

        writer.println("</table>");
        writer.println("</div>");

        writeAfterBody(writer);
    }

    private void writePassedTests(PrintWriter writer, List<String[]> passedTestsDetails) {
        writeBeforeBody(writer, reportTitle);

        if (passedTestsDetails.isEmpty()) {
            writer.print("<center><h1>No Passed Tests!<h1></center>");
        }
        else {
            writeTable(writer, passedTestsDetails);
        }
        writeAfterBody(writer);
    }

    private void writeSkippedTests(PrintWriter writer, List<String[]> skippedTestsDetails) {
        writeBeforeBody(writer, reportTitle);

        if (skippedTestsDetails.isEmpty()) {
            writer.print("<center><h1>No Skipped Tests!<h1></center>");
        }
        else {
            writeTable(writer, skippedTestsDetails);
        }
        writeAfterBody(writer);
    }

    private void writeFailedTests(PrintWriter writer, List<String[]> failedTestsDetails) {
        writeBeforeBody(writer, reportTitle);

        if (failedTestsDetails.isEmpty()) {
            writer.print("<center><h1>No Failed Tests!<h1></center>");
        }
        else {
            writeTable(writer, failedTestsDetails);
        }
        writeAfterBody(writer);
    }

    private List<String []> getTestsDetails(Set<ITestResult> testResults, String suiteName, String testName){
        List<String[]> testsDetails = new ArrayList<>();
        for (ITestResult testResult : testResults) {
            Object[] testParameters = testResult.getParameters();
            String methodName = testResult.getName();
            String className = testResult.getTestClass().getName();
            if (testResult.getThrowable() == null){
                testsDetails.add(new String[]{suiteName, testName, className, methodName, testParameters[0].toString(), testParameters[1].toString()});
            }
            else {
                String exception = testResult.getThrowable().getMessage();
                testsDetails.add(new String[]{suiteName, testName, className, methodName, testParameters[0].toString(), testParameters[1].toString(), exception});
            }
        }
        return testsDetails;
    }
}
