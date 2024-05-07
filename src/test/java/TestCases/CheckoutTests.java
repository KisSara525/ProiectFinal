package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.CheckoutPage;
import pages.LoginPage;
public class CheckoutTests extends BasePage {

    private CheckoutPage checkoutPage;
    private LoginPage loginPage;
    @BeforeMethod
    public void setUp(){
        super.setUp();
        loginPage = new LoginPage(driver);
        // login with a valid user
        loginPage.login("standard_user","secret_sauce");
        driver.get(getBaseURL()+"checkout-step-one.html");

        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void testPageTitle() {
        Assert.assertEquals(checkoutPage.getPageTitle(), "Swag Labs", "Page title is incorrect");
    }
    @Test
    public void testRequiredFieldsPresence() {
        Assert.assertTrue(checkoutPage.isFirstNameFieldPresent(), "First name field is not present");
        Assert.assertTrue(checkoutPage.isLastNameFieldPresent(), "Last name field is not present");
        Assert.assertTrue(checkoutPage.isPostalCodeFieldPresent(), "Postal code field is not present");
    }

    @Test
    public void testInvalidInputInFirstNameField() {
        checkoutPage.enterFirstName("!@#$");
        checkoutPage.enterLastName("John");
        checkoutPage.enterPostalCode("142526");
        checkoutPage.clickContinue();
        Assert.assertNotEquals(driver.getCurrentUrl(), getBaseURL()+"checkout-step-two.html", "Invalid characters allowed in the first name field");
    }

    @Test
    public void testInvalidInputInLastNameField() {
        checkoutPage.enterFirstName("Doe");
        checkoutPage.enterLastName("525125");
        checkoutPage.enterPostalCode("142526");
        checkoutPage.clickContinue();
        Assert.assertNotEquals(driver.getCurrentUrl(), getBaseURL()+"checkout-step-two.html", "Invalid characters allowed in the first name field");
    }

    @Test
    public void testInvalidInputInPostalCodeField() {
        checkoutPage.enterFirstName("Doe");
        checkoutPage.enterLastName("John");
        checkoutPage.enterPostalCode("ABCDEFGHI");
        checkoutPage.clickContinue();
        Assert.assertNotEquals(driver.getCurrentUrl(), getBaseURL()+"checkout-step-two.html", "Invalid characters allowed in the first name field");
    }

    @Test
    public void testChangeLastNameField(){
        Assert.assertTrue(checkoutPage.canYouChangeLastNameField("test"),"You cannot modify form input!");
    }
    @Test
    public void testChangeFirstNameField(){
        // check with error user, it fails there ;)
        Assert.assertTrue(checkoutPage.canYouChangeFirstNameField("test"),"You cannot modify form input!");
    }
    @Test
    public void testChangePostalCodeField(){
        Assert.assertTrue(checkoutPage.canYouChangePostalCodeField("12345"),"You cannot modify form input!");
    }
    @Test
    public void testCancelCheckout(){
        checkoutPage.clickCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), getBaseURL()+"cart.html", "Cancel Button does not navigate correctly!");

    }

}
