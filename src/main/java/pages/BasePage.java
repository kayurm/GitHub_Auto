package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BasePage {
    protected WebDriver driver;
    protected final Logger log = LogManager.getLogger(getClass().getName());

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void validateTrue(WebElement element) {
        log.debug("Start test for enabled true for element: " + element);
        Assert.assertTrue(element.isEnabled());
        log.debug("Test finished");
    }
}
