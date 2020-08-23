package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.G44Page;
import pages.UserPage;

import java.util.ArrayList;
import java.util.Arrays;

public class G44Test extends BaseTest{

    private G44Page g44Page;

    @Before
    public void init(){

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login()
                .validateSuccess("Learn Git and GitHub without any code!");
        UserPage userPage = new UserPage(this.driver);
        g44Page = userPage.searchAndGoToG44Repo();
    }

    @Test
    public void verifyPOMVersionTest(){

        Assert.assertEquals("3.141.59",g44Page.checkPomSeleniumVersion());
    }

    @Test
    public void getAllPomVersionsTest(){

        g44Page.getAllPomVersions();
    }

    @Test
    public void getAllTabs(){
        g44Page.getAllTabs();
        ArrayList<String> expectedTabsList = new ArrayList<>(Arrays.asList(
                "Code",
                "Issues",
                "Pullrequests",
                "Actions",
                "Projects",
                "Wiki",
                "Security",
                "Insights"));
//        Assert.assertArrayEquals(expectedTabsList,g44Page.getAllTabs());
    }
}
