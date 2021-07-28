package pageFactory;

import common.Extensions;
import common.Infra;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends Infra {

    static WebElement article;

    public ProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public void findArticleElement(String articleTitle) {
        //@FindBy(xpath = "//h1[contains(text(), '" + articleTitle + "') and @class='ng-binding']") WebElement title;
        article = driver.findElement(By.xpath("//h1[contains(text(), '" + articleTitle + "') and @class='ng-binding']"));
    }

    public void goToArticlePage() {
        Extensions.click(article);
    }
}
