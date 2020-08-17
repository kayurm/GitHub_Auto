package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class UserPage extends BasePage {

    protected final Logger log = LogManager.getLogger("UserPage");
    private final By searchField = By.xpath("//input[@name='q']");


    private final By userDropdownMenu = By.xpath("//details[@class='details-overlay " +
            "details-reset js-feature-preview-indicator-container']//summary[@class='Header-link']");
    private final By signOutButton = By.xpath("//button[@class='dropdown-item dropdown-signout']");
//    private final By searchField = By.xpath("//input[@placeholder='Search or jump to…']");
    private final By signInAfterSignedOutButton = By.id("user[login]");

    public UserPage(WebDriver driver) {

        super(driver);
    }

    public void doSearch(String searchCriteria){
        WebElement elem = this.driver.findElement(searchField);
        elem.sendKeys(searchCriteria);
        elem.sendKeys(Keys.ENTER);
    }





    public UserPage signOut(){

        this.driver.findElement(userDropdownMenu).click();
        validateTrue(this.driver.findElement(signOutButton));
        this.driver.findElement(signOutButton).click();
        return this;
    }

    public void validateIsSignedOut(){

        validateTrue(this.driver.findElement(signInAfterSignedOutButton));
    }
}
