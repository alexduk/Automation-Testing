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

@Test(dataProvider="testData", dataProviderClass=common.GetData.class)
public class EditArticleTags extends Infra {
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

    public void deleteArticleTagTest(String testID, String description, String initialArticleTags, String expectedTags) {
        objArticle.publishArticle(sArticleTitle, sArticleAbout, sArticleBody, initialArticleTags);
        objSpecificArticle.goToEditArticlePage(description);
        objArticle.deleteTag(2);
        objArticle.clickPublishArticle();

        List<String> expectedArticleTags = Arrays.asList(expectedTags.toLowerCase().split("\n"));
        Collections.reverse(expectedArticleTags);
        Assert.assertEquals(objSpecificArticle.getTags(), expectedArticleTags);
    }

    public void addArticleTagTest(String testID, String description, String initialArticleTags, String newArticleTag, String expectedTags) {
        objArticle.publishArticle(sArticleTitle, sArticleAbout, sArticleBody, initialArticleTags);
        objSpecificArticle.goToEditArticlePage(description);
        objArticle.setArticleTags(newArticleTag);
        objArticle.clickPublishArticle();

        List<String> expectedArticleTags = Arrays.asList(expectedTags.toLowerCase().split("\n"));
        Collections.reverse(expectedArticleTags);
        Assert.assertEquals(objSpecificArticle.getTags(), expectedArticleTags);
    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}

