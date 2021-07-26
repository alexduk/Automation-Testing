package pageFactory;

import common.Extensions;
import common.Infra;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ArticlePage extends Infra {
    @FindBy(xpath = "//input[@placeholder='Article Title']")
    WebElement articleTitle;
    @FindBy(xpath = "/html/body/div/div/div/div/div/div/form/fieldset/fieldset[2]/input")
    WebElement articleAbout;
    @FindBy(xpath = "//textarea[@placeholder='Write your article (in markdown)']")
    WebElement articleBody;
    @FindBy(xpath = "//input[@placeholder='Enter tags']")
    WebElement articleTags;
    @FindBy(css = "button[type='button']")
    WebElement publishArticleButton;
    @FindBy(css = "li.ng-binding.ng-scope[ng-repeat$='errors']")
    List<WebElement> errorElements;

    public ArticlePage(){
        PageFactory.initElements(driver, this);
    }

    public void setArticleTitle(String strArticleTitle){
        Extensions.sendKeys(articleTitle, strArticleTitle);
    }

    public void setArticleAbout(String strArticleAbout){
        Extensions.sendKeys(articleAbout, strArticleAbout);
    }

    public void setArticleBody(String strArticleBody){
        Extensions.sendKeys(articleBody, strArticleBody);
    }

    public void setArticleTags(String strArticleTags){
        Extensions.sendKeys(articleTags, strArticleTags);
    }

    public void clickPublishArticle(){
        Extensions.scriptClick(publishArticleButton);
    }

    public void publishArticle(String articleTitle, String articleAbout, String articleBody, String articleTags){
        this.setArticleTitle(articleTitle);
        this.setArticleAbout(articleAbout);
        this.setArticleBody(articleBody);
        this.setArticleTags(articleTags);
        this.clickPublishArticle();
    }

    public void editArticle(String articleTitle, String articleAbout, String articleBody, String articleTags){
        publishArticle(articleTitle, articleAbout, articleBody, articleTags);
    }

    public void isEdited(String actualArticleTitle, String expectedArticleTitle, String actualArticleBody, String expectedArticleBody){
        Assert.assertEquals(actualArticleTitle, expectedArticleTitle);
        Assert.assertEquals(actualArticleBody, expectedArticleBody);
    }

    public List<String> getErrorMessages(){
        return Extensions.GetElementsText(errorElements);
    }
}
