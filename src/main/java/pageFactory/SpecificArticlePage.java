package pageFactory;

import common.Extensions;
import common.Infra;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class SpecificArticlePage extends Infra {
    @FindBy(xpath = "//h1[@class='ng-binding']")
    WebElement articleTitle;
    @FindBy(xpath = "//div[@class='ng-binding']/p")
    WebElement articleBody;
    @FindBy(xpath = "(//button[@class='btn btn-outline-danger btn-sm'])[1]")
    WebElement firstDeleteButton;
    @FindBy(xpath = "(//button[@class='btn btn-outline-danger btn-sm'])[2]")
    WebElement secondDeleteButton;
    @FindBy(xpath = "(//a[@class='btn btn-outline-secondary btn-sm'])[1]")
    WebElement firstEditButton;
    @FindBy(xpath = "(//a[@class='btn btn-outline-secondary btn-sm'])[2]")
    WebElement secondEditButton;
    @FindBy(xpath = "//a[@class='nav-link ng-binding']")
    WebElement username;
    @FindBy(xpath = "//ul[@class='tag-list']/li")
    List<WebElement> articleTags;

    public void verifyTagsElementExists() {
        Infra.verifyElementExists(By.xpath("//ul[@class='tag-list']/li"));
    }

    public SpecificArticlePage() {
        PageFactory.initElements(driver, this);
    }

    public String getArticleTitle() {
        return Extensions.getText(articleTitle);
    }

    public String getArticleBody() {
        return Extensions.getText(articleBody);
    }

    public void deleteArticle(String description) {
        if (description.contains("first")) {
            Extensions.click(firstDeleteButton);
        } else {
            Extensions.click(secondDeleteButton);
        }
    }

    public void goToEditArticlePage(String description) {
        if (description.contains("first")) {
            Extensions.click(firstEditButton);
        } else {
            Extensions.click(secondEditButton);
        }
    }

    public void goToProfilePage() {
        Extensions.click(username);
    }

    public List<String> getTags() {
        return Extensions.GetElementsText(articleTags);
    }
}
