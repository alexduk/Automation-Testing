package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageFactory.HomePage;
import pageFactory.LoginPage;
import common.Infra;

public class TestLoginWithPageFactory extends Infra{
    LoginPage objLogin;
    HomePage objHome;

    @BeforeTest
    public void setup() {
        setupWebDriver();
        objHome = new HomePage();
        objLogin = new LoginPage();
    }

    @Test
    public void login_test(){
        objHome.goToLogin();
        objLogin.login("alexalexd@gmail.com", "alexalex");

        String actualUsername = objHome.getLoggedInUser();
        String expectedUsername = "alexalexd";
        Assert.assertEquals(actualUsername, expectedUsername);

        String actualHomePageTitle = objHome.getHomePageTitle();
        String expectedHomePageTitle = "conduit";
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
    }

    @AfterTest
    public void close(){
        driver.close();
    }
}
