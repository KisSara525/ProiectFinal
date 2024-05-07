package TestCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class CartTests extends BasePage {

    private CartPage cartPage;
    private HomePage homePage;
    private LoginPage loginPage;
    int inventoryCartItemsLength;
    @BeforeMethod
    public void setUp(){
        super.setUp();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        // login with a valid user
        loginPage.login("standard_user","secret_sauce");
        homePage.addMultipleItems();
        inventoryCartItemsLength = homePage.getCartItems();
        driver.get(getBaseURL()+"cart.html");

        cartPage = new CartPage(driver);
    }
    @Test
    public void testItemWasRemoved(){
        Assert.assertTrue(cartPage.wasItemRemoved(),"Item as not removed after clicking on Remove button");
    }

    @Test
    public void testAllItemsGotToTheCart(){
        Assert.assertEquals(inventoryCartItemsLength,cartPage.numberOfCartItems(),"Missing Items In The Cart!");
    }
    @Test
    public void testLogout(){
        cartPage.clickOnLogout();
        Assert.assertEquals(driver.getCurrentUrl(),getBaseURL(),"Logout unsuccessful!");

    }
    @Test
    public void testAbout(){
        cartPage.clickOnAbout();
        Assert.assertEquals(driver.getCurrentUrl(),"https://saucelabs.com/","Logout unsuccessful!");

    }

    @Test
    public void testCheckoutButtonClick(){
        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(),getBaseURL()+"checkout-step-one.html","Checkout Button Does not navigates correctly!");

    }

    @Test
    public void testContinueShoppingButton(){
        cartPage.clickOnContinueShoppingButton();
        Assert.assertEquals(driver.getCurrentUrl(),getBaseURL()+"inventory.html","Continue Shopping Button Does not navigates correctly!");
    }
}
