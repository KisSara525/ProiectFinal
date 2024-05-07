package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class BasePage {
    public static WebDriver driver;
    private String BASE_URL =
            "https://www.saucedemo.com/";
    public BasePage() {
        // Default constructor
    }
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    public String getBaseURL(){
        return BASE_URL;
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
        driver.quit(); }
    }
}