package test.article;

import common.Infra;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFactory.*;

import java.util.Arrays;
import java.util.List;

@Test(dataProvider="testData")
public class NewArticle extends Infra {
    LoginPage objLogin;
    HomePage objHome;
    ArticlePage objArticle;
    SpecificArticlePage objSpecificArticle;
    ProfilePage objProfile;

    @BeforeMethod
    public void setup() {
        setupWebDriver();
        objHome = new HomePage();
        objLogin = new LoginPage();
        objArticle = new ArticlePage();
        objSpecificArticle = new SpecificArticlePage();
        objProfile = new ProfilePage();
        objHome.goToLoginPage();
        objLogin.login(email, password);
        objHome.goToNewArticlePage();
    }

    public void newArticleTest(String testID, String description, String articleTitle, String articleAbout, String articleBody, String articleTags){
        objArticle.publishArticle(articleTitle, articleAbout, articleBody, articleTags);

        String actualArticleTitle = objSpecificArticle.getArticleTitle();
        String expectedArticleTitle = articleTitle;
        Assert.assertEquals(actualArticleTitle, expectedArticleTitle);

        String actualArticleBody = objSpecificArticle.getArticleBody();
        String expectedArticleBody = articleBody;
        Assert.assertEquals(actualArticleBody, expectedArticleBody);
    }

    public void invalidNewArticleTest(String testID, String description, String articleTitle, String articleAbout, String articleBody, String articleTags, String expectedError){
        objArticle.publishArticle(articleTitle, articleAbout, articleBody, articleTags);

        List<String> expectedErrorText = Arrays.asList(expectedError.split("\n"));
        List<String> actualErrorText = objArticle.getErrorMessages();
        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @AfterMethod
    public void close(){
        driver.close();
    }
}
