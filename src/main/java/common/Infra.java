package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Infra {
    public static WebDriver driver;
    public static WebDriverWait webDriverWait;
    public static JavascriptExecutor executor;

    public static String websiteUrl = "https://demo.productionready.io/#/";
    public static String email = "alexalexd@gmail.com";
    public static String password = "alexalex";
    public static String sArticleTitle = "static article title";
    public static String sArticleAbout = "static article about";
    public static String sArticleBody = "static article body";
    public static String sArticleTags = "static article tag";

    public void setupWebDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(websiteUrl);
        webDriverWait = new WebDriverWait(driver, 5);
        executor = (JavascriptExecutor) driver;
    }

    @DataProvider(name = "testData")
    public Object[][] getData(Method m) {
        String testFileName = m.getDeclaringClass().getSimpleName();
        String packageName = m.getDeclaringClass().getPackageName().substring(m.getDeclaringClass().getPackageName().indexOf(".") + 1);
        ExcelUtils excel = new ExcelUtils("src/test/java/dataProvider/" + packageName + "/" + testFileName + ".xlsx", m.getName());
        int rowCount = excel.getRowCount();
        int colCount = excel.getColCount();
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (int i = 1; i < rowCount; i++) {
            if (excel.getCellDataString(i, 0).equals("yes")) {
                ArrayList<String> row = new ArrayList<String>();
                for (int j = 1; j < colCount; j++) {
                    String cellData = excel.getCellDataString(i, j);
                    row.add(cellData);
                }
                data.add(row);
            }
        }
        String[][] data2d = data.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        return data2d;
    }

    protected static void verifyElementExists(By selector) {
        Boolean isPresent = driver.findElements(selector).size() > 0;
        Assert.assertFalse(isPresent);
    }
}
