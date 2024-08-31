import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.RegistrationPage;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private Properties properties;

    // Send the driver to the Registration Page

    @BeforeEach
    public void setUp(){

        // Properties

        properties = new Properties();
        try (InputStream is = getClass().getResourceAsStream("application.properties")) {
            try {
                properties.load(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        // Launch the browser
        driver = new ChromeDriver();

        // Implicitly Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Hit the URL
        driver.get(properties.getProperty("baseURL"));

        // Window Maximize
        driver.manage().window().maximize();


        // Sending the driver to the Registration page
        registrationPage = new RegistrationPage(driver);

        // Locating Sign In Button
        driver.findElement(registrationPage.signInLocator).click();

        // Locating don't have an account text
        driver.findElement(registrationPage.dontHaveAccLocator).click();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void registerWithInvalidEmailUserNameAndPassword() {
        registrationPage.registeringInApp("acharyasadikshya.gmail.com", "Sadikshya Acharya", "sadikshya");

    /*
        // Check the initial state of the password visibility (should be hidden)
        boolean isEyeSlashVisible = driver.findElement(registrationPage.eyeSlashLocator).isDisplayed();
        Assertions.assertTrue(isEyeSlashVisible, "Eye slash icon should be visible");


        WebElement passwordInput = driver.findElement(registrationPage.passwordLocator);
        String passwordType = passwordInput.getAttribute("type");
        Assertions.assertEquals("password", passwordType, "Password should be hidden...");

        // Click the eye slash icon
        driver.findElement(registrationPage.eyeSlashLocator).click();

        // Check if the eye icon is visible and password is shown
        boolean isEyeVisible = driver.findElement(registrationPage.openEyeLocator).isDisplayed();
        Assertions.assertTrue(isEyeVisible, "Eye icon should be visible after clicking eye-slash icon.");

*/


        driver.findElement(registrationPage.signUpLocator).click();
        WebElement errorMessage = driver.findElement(registrationPage.errorMessage);
        Assertions.assertFalse(errorMessage.isDisplayed(), "Test Passed!!!");

    }

    @Test
    public void verifyingValidEmailNameAndPassword(){
    registrationPage.registeringInApp("acharya.sadikshya11@gmail.com", "Sadikshya Acharya", "Sadikshya");
    driver.findElement(registrationPage.signUpLocator).click();


    String originalWindow = driver.getWindowHandle();
    for(String windowHandles: driver.getWindowHandles()){
        if(!originalWindow.equals(windowHandles)){
            driver.switchTo().window(windowHandles);
            break;
        }
    }


    String expectedURL = "https://www.odoo.com/my";
    String actualURL = driver.getCurrentUrl();
    Assertions.assertEquals(expectedURL, actualURL, "Test Passed!!!");


//    WebElement errorMessage = driver.findElement(registrationPage.errorMessage);
//    Assertions.assertFalse(errorMessage.isDisplayed(), "Test Passed...");


    }


}
