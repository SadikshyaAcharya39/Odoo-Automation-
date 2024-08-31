package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    WebDriver driver;

    // Catch the driver send from RegistrationTest
    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    // Locators
    public By signInLocator = By.xpath("//a[normalize-space()=\"Sign in\"]");
    public By emailLocator = By.xpath("//input[@id=\"login\"]");
    public By nameLocator = By.xpath("//input[@id=\"name\"]");
    public By passwordLocator = By.xpath("//input[@id=\"password\"]");
    public By dontHaveAccLocator = By.xpath("//a[@href=\"/web/signup\"]");
    public By openEyeLocator = By.xpath("//i[@class=\"fa fa-eye\"]");
    public By eyeSlashLocator = By.xpath("//i[@class=\"fa fa-eye-slash\"]");
    public By signUpLocator = By.xpath(" //button[normalize-space()=\"Sign up\"]");
    public By LoginButtonFromRegisterLocator = By.xpath(" //a[normalize-space()=\"I already have an account\"]");
    public By errorMessage = By.xpath("//p[@role=\"alert\"]");
    public By myAccountLocator = By.xpath("//h3[normalize-space()=\"My account\"]");


    // Method
    public void registeringInApp(String email, String name, String password){
      driver.findElement(emailLocator).sendKeys(email);
      driver.findElement(nameLocator).sendKeys(name);
      driver.findElement(passwordLocator).sendKeys(password);
    }
}
