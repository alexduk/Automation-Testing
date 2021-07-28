package test.login;

import org.testng.Assert;
import org.testng.annotations.*;
import pageFactory.HomePage;
import pageFactory.LoginPage;
import common.Infra;
import pageFactory.SettingsPage;

@Test(dataProvider="testData")
public class Login extends Infra{
    LoginPage objLogin;
    HomePage objHome;
    SettingsPage objSettings;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        setupWebDriver(browser);
        objHome = new HomePage();
        objLogin = new LoginPage();
        objSettings = new SettingsPage();
    }

    public void loginTest(String testID, String description, String email, String password, String expectedLoginUsername) throws InterruptedException {
        objHome.goToLoginPage();
        objLogin.login(email, password);

        String actualUsername = objHome.getLoggedInUser();
        String expectedUsername = expectedLoginUsername;
        Assert.assertEquals(actualUsername, expectedUsername);

        String actualHomePageTitle = objHome.getHomePageTitle();
        String expectedHomePageTitle = "conduit";
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
    }

    public void invalidLogin_test(String testID, String description, String email, String password, String expectedInvalidLogin){
        objHome.goToLoginPage();
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
