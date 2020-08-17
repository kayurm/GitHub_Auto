package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class G44Page extends BasePage{

    private final By projectNameCapture = By.xpath("//a[contains(text(),'G44Automation')]");
    private final By pomXmlFile = By.xpath("//a[@title='pom.xml']");
    private final By pomSeleniumVersion = By.xpath("//td[@id='LC27']");

    public G44Page(WebDriver driver) {
        super(driver);
    }

    public By getProjectNameCapture() {
        return projectNameCapture;
    }

    public String checkPomSeleniumVersion(){
        this.driver.findElement(pomXmlFile).click();
        String version = this.driver.findElement(pomSeleniumVersion).getText().trim();
        Assert.assertNotNull(version);
        log.debug("Pom.xml has selenium version: " + version);
        return version;
    }

}
