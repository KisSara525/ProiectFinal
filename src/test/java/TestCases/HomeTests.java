package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class HomeTests extends BasePage {

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        super.setUp();
        loginPage = new LoginPage(driver);
        // login with a valid user
        loginPage.login("standard_user","secret_sauce");
        driver.get(getBaseURL()+"inventory.html");

        homePage = new HomePage(driver);
    }

    @Test
    public void testProductListDisplayed() {
        Assert.assertTrue(homePage.isProductListDisplayed(), "Product list is not displayed on the inventory page");
    }

    @Test
    public void testAddToCartButtonFunctionality() {
        homePage.clickAddToCartButton();
        Assert.assertEquals(homePage.getAddToCartButtonText().toLowerCase(), "remove", "Add to Cart button text did not change to Remove");
    }

    @Test
    public void testRemoveFromCartButtonFunctionality() {
        homePage.clickAddToCartButton();
        homePage.clickRemoveFromCartButton();
        Assert.assertEquals(homePage.getAddToCartButtonText().toLowerCase(), "add to cart", "Remove from Cart button text did not change to Add to Cart");
    }

    @Test
    public void testShoppingCartButtonFunctionality(){
        homePage.shoppingCartButtonClick();
        Assert.assertEquals(driver.getCurrentUrl(),getBaseURL()+"cart.html","Shopping Cart Button Is Not Working!");
    }

    @Test
    public void testAllItemsHasCurrency(){
        Assert.assertEquals(homePage.allItemHasCurrency(),true,"Some Items Has No or Different Currency");
    }

    @Test
    public void testAllItemHasTitle(){
        Assert.assertEquals(homePage.allItemHasTitle(),true,"Some Items Has No Title");
    }

    @Test
    public void testHighToLowPriceSort(){
        Assert.assertEquals(homePage.checkSortByPriceHighToLow(),true,"Items sorted wrongly, in option High To Low!");
    }

    @Test
    public void testLowToHightPriceSort(){
        Assert.assertEquals(homePage.checkSortByPriceLowToHigh(),true,"Items sorted wrongly, in option Low To High!");
    }

    @Test
    public void testAToZNameSort(){
        Assert.assertEquals(homePage.checkSortByNameAToZ(),true,"Items sorted wrongly, in option A To Z!");
    }

    @Test
    public void testZtoANameSort(){
        Assert.assertEquals(homePage.checkSortByNameZToA(),true,"Items sorted wrongly, in option Z To A!");
    }

    @Test
    public void testNavigatesToItemDetailPage(){
       int id = homePage.navigateToItemDetailPage();
       Assert.assertEquals(driver.getCurrentUrl(),getBaseURL()+"inventory-item.html?id="+id,"Did not navigate to the items detail page!");
    }

    @Test
    public void testAllItemsHasDifferentImage(){
        Assert.assertTrue(homePage.AllItemsHasDifferentImage(),"There are items with the same iamges!");
    }
}
