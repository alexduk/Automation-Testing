package pageFactory;

import common.Extensions;
import common.Infra;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Infra {
    @FindBy(css = "input[type='email']")
    WebElement email;
    @FindBy(css = "input[type='password']")
    WebElement password;
    @FindBy(css = "button[type='submit']")
    WebElement login;
    @FindBy(xpath = "//li[@class='ng-binding ng-scope']")
    WebElement invalidLogin;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void setEmail(String strEmail) {
        Extensions.sendKeys(email, strEmail);
    }

    public void setPassword(String strPassword) {
        Extensions.sendKeys(password, strPassword);
    }

    public void clickLogin() {
        Extensions.scriptClick(login);
    }

    public void login(String strEmail, String strPassword) {
        this.setEmail(strEmail);
        this.setPassword(strPassword);
        this.clickLogin();
    }

    public String getInvalidLoginText() {
        return Extensions.getText(invalidLogin).trim();
    }
}
