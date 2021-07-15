package test.login;

import common.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import pageFactory.HomePage;
import pageFactory.LoginPage;
import common.Infra;

import java.lang.reflect.Method;

public class Test1 extends Infra{
    LoginPage objLogin;
    HomePage objHome;

    @BeforeMethod
    public void setup() {
        setupWebDriver();
        objHome = new HomePage();
        objLogin = new LoginPage();
    }

    @Test(dataProvider="testData")
    public void login_test(String testID, String description, String email, String password, String expectedLoginUsername){
        objHome.goToLogin();
        objLogin.login(email, password);

        String actualUsername = objHome.getLoggedInUser();
        String expectedUsername = expectedLoginUsername;
        Assert.assertEquals(actualUsername, expectedUsername);

        String actualHomePageTitle = objHome.getHomePageTitle();
        String expectedHomePageTitle = "conduit";
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
    }

    @Test(dataProvider="testData")
    public void invalid_login_test(String testID, String description, String email, String password, String expectedInvalidLogin){
        objHome.goToLogin();
        objLogin.login(email, password);

        String actualInvalidLoginText = objLogin.getInvalidLoginText();
        String expectedInvalidLoginText= expectedInvalidLogin;
        Assert.assertEquals(actualInvalidLoginText, expectedInvalidLoginText);
    }

    @AfterMethod
    public void close(){
        driver.close();
    }
}
