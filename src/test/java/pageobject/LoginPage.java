package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;
    RegistrationPage registrationPage;

    // Catch the driver

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // Locators
    public By emailInputLocator = By.xpath("//input[@id=\"login\"]");
    public By passwordInputLocator = By.xpath("//input[@id=\"password\"]");
    public By signInButtonLocator = By.xpath("//button[@class=\"btn btn-primary float-start\"]");
    public By errorMessage = By.xpath("//p[@role=\"alert\"]");

    // Method

    public void loginToApp(String email, String password){
        driver.findElement(emailInputLocator).sendKeys(email);
        driver.findElement(passwordInputLocator).sendKeys(password);
    }
}
