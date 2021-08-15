package selenium.test.article;

import selenium.common.Infra;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import selenium.pageFactory.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Test(dataProvider = "testData", dataProviderClass = common.GetData.class)
public class NewArticle extends Infra {
    LoginPage objLogin;
    HomePage objHome;
    ArticlePage objArticle;
    SpecificArticlePage objSpecificArticle;
    ProfilePage objProfile;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        setupWebDriver(browser);
        objHome = new HomePage();
        objLogin = new LoginPage();
        objArticle = new ArticlePage();
        objSpecificArticle = new SpecificArticlePage();
        objProfile = new ProfilePage();
        objHome.goToLoginPage();
        objLogin.login(email, password);
        objHome.goToNewArticlePage();
    }

    public void newArticleTest(String testID, String description, String articleTitle, String articleAbout, String articleBody, String articleTags) {
        objArticle.publishArticle(articleTitle, articleAbout, articleBody, articleTags);

        String actualArticleTitle = objSpecificArticle.getArticleTitle();
        Assert.assertEquals(actualArticleTitle, articleTitle);

        String actualArticleBody = objSpecificArticle.getArticleBody();
        Assert.assertEquals(actualArticleBody, articleBody);

        List<String> expectedArticleTags = Arrays.asList(articleTags.toLowerCase().split("\n"));
        Collections.reverse(expectedArticleTags);
        Assert.assertEquals(objSpecificArticle.getTags(), expectedArticleTags);
    }

    public void invalidNewArticleTest(String testID, String description, String articleTitle, String articleAbout, String articleBody, String articleTags, String expectedError) {
        objArticle.publishArticle(articleTitle, articleAbout, articleBody, articleTags);

        List<String> expectedErrorText = Arrays.asList(expectedError.split("\n"));
        List<String> actualErrorText = objArticle.getErrorMessages();
        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    public void newArticleTestWithoutTags(String testID, String description, String articleTitle, String articleAbout, String articleBody, String articleTags) {
        objArticle.publishArticle(articleTitle, articleAbout, articleBody, articleTags);
        objSpecificArticle.verifyTagsElementExists();
    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}