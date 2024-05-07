package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id="cancel")
    private WebElement cancelButton;

    public boolean isFirstNameFieldPresent() {
        return firstNameField.isDisplayed();
    }

    public boolean isLastNameFieldPresent() {
        return lastNameField.isDisplayed();
    }

    public boolean isPostalCodeFieldPresent() {
        return postalCodeField.isDisplayed();
    }

    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void clickContinue(){
        continueButton.click();
    }

    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public String getFirstNameFieldValue() {
        return firstNameField.getAttribute("value");
    }

    public String getLastNameFieldValue() {
        return lastNameField.getAttribute("value");
    }
    public String getPostalCodeFieldValue() {
        return postalCodeField.getAttribute("value");
    }

    public void enterPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void modifyFirstName(String text){
        firstNameField.sendKeys(text);
    }
    public void modifyLastName(String text){
        lastNameField.sendKeys(text);
    }
    public void modifyPostalCode(String text){
       postalCodeField.sendKeys(text);
    }

    public boolean canYouChangeFirstNameField(String testString){

        modifyFirstName(testString);
        String newValue = getFirstNameFieldValue();
        return testString.compareTo(newValue) == 0;
    }
    public boolean canYouChangeLastNameField(String testString){

        modifyLastName(testString);
        String newValue = getLastNameFieldValue();
        return testString.compareTo(newValue) == 0;
    }
    public boolean canYouChangePostalCodeField(String testString){

        modifyPostalCode(testString);
        int newValue = Integer.parseInt(getPostalCodeFieldValue());
        return Integer.parseInt(testString) == newValue;
    }

    public void clickCancelButton(){
        cancelButton.click();
    }

}
