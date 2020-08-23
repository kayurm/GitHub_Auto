package pages.tabsPanel;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class IssuesPage extends BasePage {

    // LOCATORS
    private final By newIssueButton = By.xpath("//span[contains(text(),'New issue')]");
    private final By titleField = By.xpath("//input[@placeholder='Title']");
    private final By submitNewIssueButton = By.xpath("//button[@class='btn btn-primary']");
    private final By issueCreatedTitle = By.xpath("//span[@class='js-issue-title']");

    public IssuesPage(WebDriver driver) {
        super(driver);
    }

    // GETTERS
    public By getNewIssueButton() {
        return newIssueButton;
    }

    // METHODS
    public IssuesPage reportNewIssue(String issueTitle){
        LOG.info("Reporting new issue");
        this.driver.findElement(newIssueButton).click();
        WebElement elem = this.driver.findElement(titleField);
        Assert.assertTrue(elem.isEnabled());
        elem.sendKeys(issueTitle);
        elem = this.driver.findElement(submitNewIssueButton);
        Assert.assertTrue(elem.isEnabled());
        elem.click();
        Assert.assertEquals(issueTitle,this.driver.findElement(issueCreatedTitle).getText());
        LOG.info("New issue is reported");
        return new IssuesPage(this.driver);
    }
}
