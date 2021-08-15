package selenium.pageFactory;

import selenium.common.Extensions;
import selenium.common.Infra;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends Infra {
    @FindBy(xpath = "//button[@class='btn btn-outline-danger']")
    WebElement logoutButton;

    public SettingsPage() {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        Extensions.scriptClick(logoutButton);
    }
}
