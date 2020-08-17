package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.G44Page;
import pages.UserPage;

public class G44Test extends BaseTest{

    private G44Page g44page;
    private WebElement elem;

    @Before
    public void init(){

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login()
                .validateSuccess("Learn Git and GitHub without any code!");
    }

    @Test
    public void searchG44AutomationProjectTest(){
        UserPage userPage = new UserPage(this.driver);
        userPage.doSearch("g44automation");

        this.elem = this.driver.findElement(By.xpath("//*[contains(text(), 'BKuso')]//*[text()='G44Automation']"));
        Assert.assertTrue(this.elem.isDisplayed());
        this.elem.click();
        this.g44page = new G44Page(this.driver);
        this.elem = this.driver.findElement(g44page.getProjectNameCapture());
        Assert.assertTrue(elem.isDisplayed());
        Assert.assertEquals("<version>3.141.59</version>",g44page.checkPomSeleniumVersion());

    }

//    @Test
//    public void isItG44ProjectTest(){
//        this.elem = this.driver.findElement(g44page.getProjectNameCapture());
//        Assert.assertTrue(elem.isDisplayed());
//    }

//    @Test
//    public void seleniumVersionTest(){
//        Assert.assertEquals(g44page.checkPomSeleniumVersion(),"3.141.59");
//    }
}
