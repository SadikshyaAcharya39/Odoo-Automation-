import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.module.Input;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class LoginTest {
    WebDriver driver;
    Properties properties;
    RegistrationPage registrationPage;
    LoginPage loginPage;

    @BeforeEach
    public void setUp() {
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

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        driver.get(properties.getProperty("baseURL"));

        // Sending same driver to Login Page
        registrationPage = new RegistrationPage(driver);

        driver.findElement(registrationPage.signInLocator).click();


        // Sending same driver to Login Page
        loginPage = new LoginPage(driver);

    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void loginUsingIncorrectEmailAndIncorrectPassword(){
        loginPage.loginToApp("sadikshya@gmail.com", "dummy");
        driver.findElement(loginPage.signInButtonLocator).click();

        WebElement errorMessageStatus = driver.findElement(loginPage.errorMessage);
        Assertions.assertTrue(errorMessageStatus.isDisplayed(), "Test Passed!!!");

    }

    @Test
    public void loginUsingCorrectEmailAndPassword(){
        loginPage.loginToApp("acharya.sadikshya33@gmail.com", "Sadikshya");
        driver.findElement(loginPage.signInButtonLocator).click();


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
    }
}
