package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="user-name")
    private WebElement userNameInput;

    @FindBy(id="password")
    private WebElement userPasswordInput;

    @FindBy(id="login-button")
    private WebElement loginButton;

    public void login(String username, String password) {
        userNameInput.sendKeys(username);
        userPasswordInput.sendKeys(password);
        loginButton.click();
    }

}
