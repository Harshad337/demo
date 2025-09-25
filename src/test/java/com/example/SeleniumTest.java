package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // This setup is crucial. It tells Selenium where your ChromeDriver is.
        // NOTE: For Jenkins, a better approach is to use WebDriverManager or have the driver in the system's PATH.
        // For local execution, change the path to where you have saved chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:/path/to/your/chromedriver.exe");
        
        // Use headless mode to run in environments without a GUI (like many Jenkins servers)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() {
        // Navigate to Google's homepage
        driver.get("https://www.google.com");

        // Find the search input element by its name attribute
        WebElement searchBox = driver.findElement(By.name("q"));

        // Type "Selenium" into the search box
        searchBox.sendKeys("Selenium");

        // Submit the search form
        searchBox.submit();
        
        // Simple check to see if the title of the results page contains "Selenium"
        // In a real test, you would use TestNG assertions here.
        assert driver.getTitle().contains("Selenium");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser window and quit the driver session
        if (driver != null) {
            driver.quit();
        }
    }
}
