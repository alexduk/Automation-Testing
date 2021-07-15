package pageFactory;

import common.Extensions;
import common.Infra;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Infra {
    @FindBy(linkText = "Sign in") WebElement loginButton;
    @FindBy(linkText = "conduit") WebElement title;
    //@FindBy(xpath = "//a[contains(text(), 'conduit') and @class='navbar-brand ng-binding']") WebElement title;
    @FindBy(xpath = "//a[@class='nav-link ng-binding']") WebElement username;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public String getHomePageTitle(){
        return Extensions.scriptGetHrefText(title);
    }

    public void goToLogin(){
        Extensions.scriptClick(loginButton);
    }

    public String getLoggedInUser(){
        return Extensions.scriptGetText(username);
    }
}
