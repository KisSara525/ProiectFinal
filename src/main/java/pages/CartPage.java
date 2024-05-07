package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(id="remove-sauce-labs-bike-light")
    private WebElement removeButton;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id="react-burger-menu-btn")
    private WebElement burgerButton;

    @FindBy(id="checkout")
    private WebElement checkoutButton;

    @FindBy(id="continue-shopping")
    private WebElement continueShoppingButton;


    public boolean wasItemRemoved(){
        return driver.findElements(By.id("sauce-labs-bike-light")).isEmpty();
    }

    public void clickOnLogout(){

        burgerButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        link.click();
    }
    public void clickOnAbout(){
        burgerButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));
        link.click();
    }

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }

    public void clickOnContinueShoppingButton(){
        continueShoppingButton.click();
    }


    public int numberOfCartItems(){
        return cartItems.size();
    }

}
