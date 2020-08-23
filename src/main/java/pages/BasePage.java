package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BasePage {
    protected WebDriver driver;
    protected Logger LOG;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.LOG = LogManager.getLogger(getClass().getName());
    }

    protected void validateTrue(WebElement element) {
        LOG.debug("Validating element isEnabled() for: " + element.toString());
        Assert.assertTrue(element.isEnabled());
    }
}
