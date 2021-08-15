package selenium.common;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class Extensions extends Infra {

    public static void click(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static String getText(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public static void sendKeys(WebElement element, String text) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    public static void clickEnter(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Keys.ENTER);
    }

    public static void scriptClick(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        executor.executeScript("arguments[0].click();", element);
    }

    public static String scriptGetHrefText(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return (String) executor.executeScript("return arguments[0].innerHTML;", element);
    }

    public static String scriptGetText(WebElement element) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return (String) executor.executeScript("return arguments[0].text.trim();", element);
    }

    public static void scriptSendKeys(WebElement element, String value) {
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", element);
    }

    public static List<String> GetElementsText(List<WebElement> elements) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        List<String> elementsText = new ArrayList<>();
        for (WebElement element : elements) {
            elementsText.add(element.getText().toLowerCase());
        }
        return elementsText;
    }
}
