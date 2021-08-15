package common;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class HtmlCommons {
    protected static void writeStylesheet(PrintWriter writer) {
        writer.println("<style type='text/css'>");
        writer.println("table {margin-bottom:10px;border-collapse:collapse;empty-cells:show}");
        writer.println("h1 {font-size:30px}");
        writer.println("body {width:100%;}");
        writer.println("th,td {padding: 8px}");
        writer.println("th {vertical-align:bottom}");
        writer.println("td {vertical-align:top}");
        writer.println("table a {font-weight:bold;color:#0D1EB6;}");
        writer.println(".overview {margin-left: auto; margin-right: auto;} ");
        writer.println(".selenium.test-overview tr:first-child {background-color:#D3D3D3}");
        writer.println("</style>");
    }

    protected static void writeBeforeBody(PrintWriter writer, String reportTitle) {
        writer.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.1//EN' 'http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd'>");
        writer.println("<html xmlns='http://www.w3.org/1999/xhtml'>");

        writer.println("<head>");
        writer.println("<meta content='text/html; charset=UTF-8'/>");
        writer.println("<title>TestNG Custom Report</title>");
        writeStylesheet(writer);
        writer.println("</head>");

        writer.println("<body>");
        writer.println("<center><h1>" + reportTitle + " - " + getCurrentDateTime() + "</h1></center>");
    }

    protected static void writeAfterBody(PrintWriter writer) {
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }

    protected static void writeTable(PrintWriter writer, List<String[]> testsDetails) {
        writer.println("<div class='selenium.test-overview'>");
        writer.println("<table class='table-bordered overview'>");
        writer.println("<tr>");
        writer.println("<th>Suite Name</th>");
        writer.println("<th>Test Name</th>");
        writer.println("<th>Class Name</th>");
        writer.println("<th>Method Name</th>");
        writer.println("<th>Test ID</th>");
        writer.println("<th>Test Description</th>");
        if (testsDetails.get(0).length == 7) {
            writer.println("<th>Exception</th>");
        }
        writer.println("</tr>");

        for (int i = 0; i < testsDetails.size(); i++) {
            writer.println("<tr>");
            writer.println("<th>" + testsDetails.get(i)[0] + "</th>");
            writer.println("<th>" + testsDetails.get(i)[1] + "</th>");
            writer.println("<th>" + testsDetails.get(i)[2] + "</th>");
            writer.println("<th>" + testsDetails.get(i)[3] + "</th>");
            writer.println("<th>" + testsDetails.get(i)[4] + "</th>");
            writer.println("<th>" + testsDetails.get(i)[5] + "</th>");
            if (testsDetails.get(0).length == 7) {
                writer.println("<th>" + testsDetails.get(i)[6] + "</th>");
            }
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.println("</div>");
    }

    protected static PrintWriter createWriter(String outputDirectory, String reportFileName) throws IOException {
        new File(outputDirectory).mkdirs();
        return new PrintWriter(new BufferedWriter(new FileWriter(new File(outputDirectory, reportFileName))));
    }


    public static String getCurrentDateTime() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
        return formatter.format(currentDate.getTime());
    }
}
