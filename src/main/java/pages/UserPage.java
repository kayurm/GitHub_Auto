package pages;

import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage extends BasePage {

    private WebElement elem;
    private String g44projectURL;


    // LOCATORS

    private final By searchField = By.xpath("//input[@name='q']");
    private final By userDropdownMenu = By.xpath("//details[@class='details-overlay " +
            "details-reset js-feature-preview-indicator-container']//summary[@class='Header-link']");
    private final By signOutButton = By.xpath("//button[@class='dropdown-item dropdown-signout']");
    //    private final By searchField = By.xpath("//input[@placeholder='Search or jump to…']");
    private final By signInAfterSignedOutButton = By.id("user[login]");

    // CONSTRUCTOR
    public UserPage(WebDriver driver) {
        super(driver);
    }

    //GETTERS
    public String getG44projectURL() {
        return this.g44projectURL;
    }

    //METHODS
    // general method - perform search
    public void searchForRepo(By searchField, String searchCriteria) {
        LOG.debug("Locating a search field: " + searchField.getClass().getName());
        this.elem = this.driver.findElement(searchField);
        Assert.assertTrue(elem.isDisplayed());
        LOG.debug("Inserting search criteria: " + searchCriteria);
        this.elem.sendKeys(searchCriteria);
        LOG.debug("Searching..");
        this.elem.sendKeys(Keys.ENTER);
    }

    // search for G44 project and jump to it. Returns G44Page
    public G44Page searchAndGoToG44Repo() {

        searchForRepo(searchField, "g44automation");
        G44Page g44Page = new G44Page(this.driver);
        this.elem = this.driver.findElement(g44Page.getG44RepoName());
        Assert.assertTrue(elem.isDisplayed());
        this.elem.click();
        this.elem = this.driver.findElement(g44Page.getProjectNameCapture());
        Assert.assertTrue(elem.isDisplayed());

        // getting g44 project's url to use in in the @Before on the Issues tab test
        this.g44projectURL=this.driver.getCurrentUrl();
        LOG.info("g44 URL is:"+this.g44projectURL);
        return g44Page;
    }

    public UserPage signOut() {

        this.driver.findElement(userDropdownMenu).click();
        validateTrue(this.driver.findElement(signOutButton));
        this.driver.findElement(signOutButton).click();
        return this;
    }

    public void validateIsSignedOut() {

        validateTrue(this.driver.findElement(signInAfterSignedOutButton));
    }
}
