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
    public void registerWithInvalidEmailUserNameAndPassword(){
    registrationPage.registeringInApp("acharyasadikshya.gmail.com", "Sadikshya Acharya", "sadikshya");
    driver.findElement(registrationPage.signUpLocator).click();
    WebElement errorMessage = driver.findElement(registrationPage.errorMessage);
    Assertions.assertTrue(errorMessage.isDisplayed(), "Test Failed!!!");

//    boolean eyeStatus = driver.findElement(registrationPage.openEyeLocator).isEnabled();
//    Assertions.assertTrue(eyeStatus == false, "Test Passed!!!");
    }

}
