package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.tabsPanel.IssuesPage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class G44Page extends BasePage{

    private final By projectNameCapture = By.xpath("//a[contains(text(),'G44Automation')]");
    private final By pomXmlFile = By.xpath("//a[@title='pom.xml']");
    private final By pomSeleniumVersion = By.xpath("//td[@id='LC27']");
    private final By g44RepoName = By.xpath("//*[contains(text(), 'BKuso')]//*[text()='G44Automation']");
    private final By pomVersions = By.xpath("//span[text() = 'version']/..");
    private final By pomArtifacts = By.xpath("//span[text() = 'artifactId']/..");
    private final By tabs = By.xpath("//a[contains(@data-tab-item, 'tab')]/span/..");
    private final By issuesTab = By.xpath("//span[@data-content='Issues']");

    public G44Page(WebDriver driver) {
        super(driver);
    }

    //GETTERS
    public By getProjectNameCapture() {
        return projectNameCapture;
    }

    public By getG44RepoName(){
        return g44RepoName;
    }

    // METHODS
    public String checkPomSeleniumVersion(){

        this.driver.findElement(pomXmlFile).click();
        String version = this.driver.findElement(pomSeleniumVersion)
                .getText()
                .replaceAll(" ", "")
                .replaceAll("<version>","")
                .replaceAll("</version>","");

        Assert.assertNotNull(version);
        LOG.debug("Pom.xml has selenium version: " + version);
        return version;
    }

    public G44Page getAllPomVersions(){

        this.driver.findElement(pomXmlFile).click();
        LOG.info("Listing versions found in POM.xml:");
        Map<String,String> versionsMap = new HashMap<>();
        List<WebElement> versionsList = driver.findElements(pomVersions);
        driver.findElements(pomArtifacts).forEach(
                artifact -> {
                    Assert.assertTrue(artifact.isDisplayed());

                    // adding key,value using put func into versionsMap:
                    versionsMap.put(artifact.getText()
                                    .replaceAll(" ", "")
                                    .replaceAll("<artifactId>", "")
                                    .replaceAll("</artifactId>", ""),
                            versionsList.get(driver.findElements(pomArtifacts).indexOf(artifact))
                            .getText()
                            .replaceAll(" ", "")
                            .replaceAll("<version>", "")
                            .replaceAll("</version>", ""));
                });
        versionsMap.forEach((key, value) -> LOG.debug(key + ":" + value));
        return this;
    }

    public G44Page getAllTabs(){
        List<WebElement> tabsList = driver.findElements(tabs);
        List<String> tabsActualList = new ArrayList<>();
        Assert.assertNotNull(tabsList);
        LOG.info("Listing all project tabs:");
        tabsList.forEach(tab -> {
            LOG.info(tab.getText());
            tabsActualList.add(tab.getText()
                    .replaceAll("(\\d)","")
                    .replaceAll(" ","")
                    .replaceAll("\\s", ""));
        });
        LOG.info(tabsActualList);
        return this;
    }

    public IssuesPage goToIssuesTab(){

        Assert.assertTrue(this.driver.findElement(issuesTab).isDisplayed());
        this.driver.findElement(issuesTab).click();
        IssuesPage issuesPage = new IssuesPage(this.driver);
        Assert.assertTrue(this.driver.findElement(issuesPage.getNewIssueButton()).isDisplayed());
        LOG.info("On the Issues tab");
        return issuesPage;
    }
}
