package test.article;

import common.Infra;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFactory.*;

@Test(dataProvider = "testData")
public class DeleteArticle extends Infra {
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
        objArticle.publishArticle(sArticleTitle, sArticleAbout, sArticleBody, sArticleTags);
    }

    public void deleteNewArticleTest(String testID, String description) {
        objSpecificArticle.deleteArticle(description);

        String expectedSectionText = "Your Feed";
        String actualSectionText = objHome.yourFeedSection();
        Assert.assertEquals(actualSectionText, expectedSectionText);
    }

    public void deleteOldArticleTest(String testID, String description) {
        objSpecificArticle.goToProfilePage();
        objProfile.findArticleElement(sArticleTitle);
        objProfile.goToArticlePage();
        objSpecificArticle.deleteArticle(description);

        String expectedSectionText = "Your Feed";
        String actualSectionText = objHome.yourFeedSection();
        Assert.assertEquals(actualSectionText, expectedSectionText);
    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}
