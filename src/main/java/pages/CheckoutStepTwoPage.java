package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutStepTwoPage extends BasePage {

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    private List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

    @FindBy(className = "summary_total_label")
    private WebElement expectedTotalPrice;

    @FindBy(className = "summary_subtotal_label")
    private WebElement item_total;

    @FindBy(className = "summary_tax_label")
    private WebElement total_tax;

    public double cartItemsTotalPrice(){

        double totalPrice = 0.0;

        System.out.println(cartItems);
        for (WebElement cartItem : cartItems) {
            double itemPrice = Double.parseDouble(cartItem.findElement(By.className("inventory_item_price")).getText().split("\\$")[1]);
            System.out.println("price: " + itemPrice);
            totalPrice += itemPrice;
        }
        return totalPrice;
    }

    public double getTaxPrice(){
        return Double.parseDouble(total_tax.getText().split("\\$")[1]);
    }

    public boolean countFloatingPointDigits(double number) {
        String numberAsString = Double.toString(number);
        int decimalIndex = numberAsString.indexOf('.');

        if (decimalIndex == -1) {
            return true;
        }

        return numberAsString.length() - decimalIndex - 1 <= 2;
    }

    public double getItemTotalPrice(){
        return Double.parseDouble(item_total.getText().split("\\$")[1]);
    }

    public double getTotalPrice(){
        return Double.parseDouble(expectedTotalPrice.getText().split("\\$")[1]);
    }
}
