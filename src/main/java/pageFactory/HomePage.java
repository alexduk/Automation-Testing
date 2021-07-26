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
    @FindBy(partialLinkText = "Settings") WebElement settingsButton;
    @FindBy(partialLinkText = "New Article") WebElement newArticleButton;
    @FindBy(partialLinkText = "Your Feed") WebElement yourFeed;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public String getHomePageTitle(){
        return Extensions.scriptGetHrefText(title);
    }

    public void goToLoginPage(){
        Extensions.scriptClick(loginButton);
    }

    public void goToSettingsPage(){
        Extensions.scriptClick(settingsButton);
    }

    public void goToNewArticlePage(){
        Extensions.scriptClick(newArticleButton);
    }

    public String yourFeedSection(){
        return Extensions.getText(yourFeed).trim();
    }

    public String getLoggedInUser(){
        return Extensions.scriptGetText(username);
    }
}
