package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CheckoutStepTwoPage;
import pages.HomePage;
import pages.LoginPage;

public class CheckoutStepTwoTests extends BasePage {

    private CheckoutStepTwoPage checkoutPage;
    private LoginPage loginPage;
    private HomePage homePage;
    @BeforeMethod
    public void setUp(){
        super.setUp();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        // login with a valid user
        loginPage.login("standard_user","secret_sauce");
        homePage.addMultipleItems();
        driver.get(getBaseURL()+"checkout-step-two.html");
        checkoutPage = new CheckoutStepTwoPage(driver);
    }

    @Test(priority = 1)
    public void testItemTotal(){
        Assert.assertEquals(checkoutPage.cartItemsTotalPrice(),checkoutPage.getItemTotalPrice(),"Price's Differ on Item Total, Check For Bugs!");
    }

    @Test
    public void testItemTotalPrecision(){
        // how many floating point the itemTotalPrice has? ( should have max 2, like this: 9.95 | Not like 9.95999999999)
        Assert.assertTrue(checkoutPage.countFloatingPointDigits(checkoutPage.getItemTotalPrice()), "ItemTotal has more than 2 floating point digits");
    }

    @Test(priority = 2)
    public void testTotalPriceWithTax(){
        Assert.assertEquals(checkoutPage.cartItemsTotalPrice() + checkoutPage.getTaxPrice(),checkoutPage.getTotalPrice(),"Price's Differ on Total Price, Check For Bugs!");
    }

}
