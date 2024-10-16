import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppleStoreTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Set the path for the WebDriver executable
       // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void bookIphone16() {
        // Step 1: Go to the Apple Store and search for iPhone 16

            // Step 1: Go to the Apple Store and search for iPhone 16
        driver.get("https://www.apple.com/iphone/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Step 2: Locate iPhone 16 Pro product link
        List<WebElement> iphoneLinks = driver.findElements(By.cssSelector("a[href=\"/in/iphone-16-pro/\"]"));
        if (iphoneLinks.isEmpty()) {
            System.out.println("iPhone 16 Pro link not found.");
            return; // Exit if the link is not found
        }

        for (WebElement link : iphoneLinks) {
            String productLink = link.getAttribute("href");
            String productName = link.getText().trim();
            System.out.println("Navigating to: " + productName + " - " + productLink);

            // Step 3: Navigate to product details
            driver.get(productLink);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Step 4: Fetch the price (adjust selector as necessary)
            WebElement priceElement;
            try {
                // Update the XPath to correctly locate the price element
                priceElement = driver.findElement(By.xpath("//span[contains(@class, 'as-price')]")); // Adjust as needed
                System.out.println(productName + " Price: " + priceElement.getText());
            } catch (NoSuchElementException e) {
                System.out.println("Price not found for: " + productName);
            }

            // Go back to the main iPhone page
            driver.navigate().back();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }



    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
           // driver.quit();
        }
    }
}
