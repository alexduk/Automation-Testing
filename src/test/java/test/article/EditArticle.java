package test.article;

import common.Infra;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFactory.*;

@Test(dataProvider = "testData")
public class EditArticle extends Infra {
    LoginPage objLogin;
    HomePage objHome;
    ArticlePage objArticle;
    SpecificArticlePage objSpecificArticlePage;
    ProfilePage objProfile;

    @BeforeMethod
    public void setup() {
        setupWebDriver();
        objHome = new HomePage();
        objLogin = new LoginPage();
        objArticle = new ArticlePage();
        objSpecificArticlePage = new SpecificArticlePage();
        objProfile = new ProfilePage();
        objHome.goToLoginPage();
        objLogin.login(email, password);
        objHome.goToNewArticlePage();
        objArticle.publishArticle(sArticleTitle, sArticleAbout, sArticleBody, sArticleTags);
    }

    public void editNewArticleTest(String testID, String description, String articleTitle, String articleAbout, String articleBody, String articleTags) {
        objSpecificArticlePage.goToEditArticlePage(description);
        objArticle.editArticle(articleTitle, articleAbout, articleBody, articleTags);

        String actualArticleTitle = objSpecificArticlePage.getArticleTitle();
        String actualArticleBody = objSpecificArticlePage.getArticleBody();
        objArticle.isEdited(actualArticleTitle, articleTitle, actualArticleBody, articleBody);
    }

    public void editOldArticleTest(String testID, String description, String articleTitle, String articleAbout, String articleBody, String articleTags) {
        objSpecificArticlePage.goToProfilePage();
        objProfile.findArticleElement(sArticleTitle);
        objProfile.goToArticlePage();
        objSpecificArticlePage.goToEditArticlePage(description);
        objArticle.editArticle(articleTitle, articleAbout, articleBody, articleTags);

        String actualArticleTitle = objSpecificArticlePage.getArticleTitle();
        String actualArticleBody = objSpecificArticlePage.getArticleBody();
        objArticle.isEdited(actualArticleTitle, articleTitle, actualArticleBody, articleBody);
    }

    public void invalidEditArticleTest(String testID, String description, String articleTitle, String articleAbout, String articleBody, String articleTags) {
        objSpecificArticlePage.goToEditArticlePage(description);
        String beforeEditArticleTitle = objSpecificArticlePage.getArticleTitle();
        String beforeEditArticleBody = objSpecificArticlePage.getArticleBody();
        objArticle.editArticle(articleTitle, articleAbout, articleBody, articleTags);

        String actualArticleTitle = objSpecificArticlePage.getArticleTitle();
        String actualArticleBody = objSpecificArticlePage.getArticleBody();
        objArticle.isEdited(actualArticleTitle, beforeEditArticleTitle, actualArticleBody, beforeEditArticleBody);
    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}
