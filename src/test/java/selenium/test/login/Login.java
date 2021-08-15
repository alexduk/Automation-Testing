package selenium.test.login;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import selenium.common.Infra;
import selenium.pageFactory.HomePage;
import selenium.pageFactory.LoginPage;
import selenium.pageFactory.SettingsPage;

@Test(dataProvider = "testData", dataProviderClass = common.GetData.class)
public class Login extends Infra {
    LoginPage objLogin;
    HomePage objHome;
    SettingsPage objSettings;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) {
        setupWebDriver(browser);
        objHome = new HomePage();
        objLogin = new LoginPage();
        objSettings = new SettingsPage();
    }

    public void loginTest(String testID, String description, String email, String password, String expectedLoginUsername) {
        objHome.goToLoginPage();
        objLogin.login(email, password);

        String actualUsername = objHome.getLoggedInUser();
        Assert.assertEquals(actualUsername, expectedLoginUsername);

        String actualHomePageTitle = objHome.getHomePageTitle();
        String expectedHomePageTitle = "conduit";
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
    }

    public void invalidLoginTest(String testID, String description, String email, String password, String expectedInvalidLogin) {
        objHome.goToLoginPage();
        objLogin.login(email, password);

        String actualInvalidLoginText = objLogin.getInvalidLoginText();
        Assert.assertEquals(actualInvalidLoginText, expectedInvalidLogin);
    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}
