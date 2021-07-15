package common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Extensions extends Infra{

    public static void click(WebElement element){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static String getText(WebElement element){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        return element.getText();
    }

    public static void sendKeys(WebElement element, String text){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }

    public static void scriptClick(WebElement element){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        executor.executeScript("arguments[0].click();", element);
    }

    public static String scriptGetHrefText(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return (String) executor.executeScript("return arguments[0].innerHTML;", element);
    }

    public static String scriptGetText(WebElement element){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return (String) executor.executeScript("return arguments[0].text.trim();", element);
    }

    public static void scriptSendKeys(WebElement element, String value){
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", element);
    }
}
