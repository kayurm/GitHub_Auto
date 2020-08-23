package pages;

import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final By usernameField = By.id("login_field");
    private final By passwordField = By.id("password");
    private final By signInButton = By.name("commit");
    private final By errorMessage = By.xpath("//div[@class = 'container-lg px-2']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public MainPage login(){

        validateTrue(this.driver.findElement(usernameField));
        validateTrue(this.driver.findElement(passwordField));
        validateTrue(this.driver.findElement(signInButton));

        LOG.debug("Negative login test...");
        this.driver.findElement(usernameField).sendKeys(System.getProperty("username"));
        this.driver.findElement(passwordField).sendKeys(System.getProperty("password"));
        this.driver.findElement(signInButton).click();
        return new MainPage(this.driver);
    }

    public LoginPage login(String username, String password){

        LOG.debug("Positive login test...");
        this.driver.findElement(usernameField).sendKeys(username);
        this.driver.findElement(passwordField).sendKeys(password);
        this.driver.findElement(signInButton).click();
        return this;
    }

    public void validateError(String errorText){

        Assert.assertEquals(errorText, driver.findElement(errorMessage).getText());
    }

}
