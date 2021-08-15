package selenium.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

    protected static void verifyElementExists(By selector) {
        boolean isPresent = driver.findElements(selector).size() > 0;
        Assert.assertFalse(isPresent);
    }
}
