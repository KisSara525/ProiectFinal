package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "btn")
    private List<WebElement> addToCartButton;

    @FindBy(className = "select_container")
    private WebElement selectContainer;

    @FindBy(tagName = "select")
    private WebElement selectElement;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "inventory_list")
    private WebElement productList;


    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartButton;

    @FindBy(id="about_sidebar_link")
    private WebElement aboutLink;

    @FindBy(id="react-burger-menu-btn")
    private WebElement burgerButton;

    public boolean isProductListDisplayed() {
        return productList.isDisplayed();
    }

    public void shoppingCartButtonClick(){
        shoppingCartButton.click();
    }

    public void clickAddToCartButton() {
        addToCartButton.get(0).click();
    }

    public void addMultipleItems(){
        for (WebElement webElement : addToCartButton) {
            webElement.click();
        }
    }

    public int getCartItems(){
        return inventoryItems.size();
    }

    public String getAddToCartButtonText() {
        return addToCartButton.get(0).getText();
    }

    public void clickRemoveFromCartButton() {
        addToCartButton.get(0).click();
    }

    public boolean allItemHasCurrency(){

        for(WebElement element: inventoryItems) {
            char currencyCharacter = element.findElement(By.className("inventory_item_price")).getText().charAt(0);
            if(currencyCharacter != '$'){
                return false;
            }
        }
        return true;
    }

    public boolean allItemHasTitle(){

        for(WebElement element: inventoryItems) {
            String currencyCharacter = element.findElement(By.className("inventory_item_name")).getText();
            if(currencyCharacter.isEmpty()){
                return false;
            }
        }
        return true;
    }

    public boolean checkSortByPriceHighToLow(){
        Select select = new Select(selectElement);
        select.selectByValue("hilo");
        boolean sorted = true;

        for(int i = 0; i < inventoryItems.size() -1; i++){
            double itemPrice1 = Double.parseDouble(inventoryItems.get(i).findElement(By.className("inventory_item_price")).getText().split("\\$")[1]);
            double itemPrice2 = Double.parseDouble(inventoryItems.get(i+1).findElement(By.className("inventory_item_price")).getText().split("\\$")[1]);

            if(itemPrice1 < itemPrice2){
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public boolean checkSortByPriceLowToHigh(){
        Select select = new Select(selectElement);
        select.selectByValue("lohi");
        boolean sorted = true;

        for(int i = 0; i < inventoryItems.size() -1; i++){
            double itemPrice1 = Double.parseDouble(inventoryItems.get(i).findElement(By.className("inventory_item_price")).getText().split("\\$")[1]);
            double itemPrice2 = Double.parseDouble(inventoryItems.get(i+1).findElement(By.className("inventory_item_price")).getText().split("\\$")[1]);

            if(itemPrice1 > itemPrice2){
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public boolean checkSortByNameAToZ(){
        Select select = new Select(selectElement);
        select.selectByValue("az");
        boolean sorted = true;

        for(int i = 0; i < inventoryItems.size() -1; i++){
            String itemName1 = inventoryItems.get(i).findElement(By.className("inventory_item_name")).getText();
            String itemName2 = inventoryItems.get(i+1).findElement(By.className("inventory_item_name")).getText();

            int result = itemName1.compareTo(itemName2);
            System.out.println(itemName1 + " " + itemName2 + " result:" + result);
            if(result > 0){
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public boolean checkSortByNameZToA(){
        Select select = new Select(selectElement);
        select.selectByValue("za");
        boolean sorted = true;

        for(int i = 0; i < inventoryItems.size() -1; i++){
            String itemName1 = inventoryItems.get(i).findElement(By.className("inventory_item_name")).getText();
            String itemName2 = inventoryItems.get(i+1).findElement(By.className("inventory_item_name")).getText();

            int result = itemName1.compareTo(itemName2);
            System.out.println(itemName1 + " " + itemName2 + " result:" + result);
            if(result < 0){
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public int navigateToItemDetailPage(){
        int id = 0;
        if(!inventoryItems.isEmpty()) {
            WebElement anchor = inventoryItems.get(0).findElement(By.tagName("a"));
            String[] id2 = anchor.getAttribute("id").split("_");
            id = Integer.parseInt(id2[1]);
            anchor.click();
        }
        return id;
    }

    public boolean AllItemsHasDifferentImage(){
        for(int i = 0; i < inventoryItems.size() -1; i++){
            String itemName1 = inventoryItems.get(i).findElement(By.tagName("img")).getAttribute("src");
            String itemName2 = inventoryItems.get(i+1).findElement(By.tagName("img")).getAttribute("src");
            int result = itemName1.compareTo(itemName2);
            if(result==0){
                return false;
            }
        }
        return true;
    }
}

