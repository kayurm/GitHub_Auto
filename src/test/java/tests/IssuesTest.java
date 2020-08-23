package tests;

import org.junit.Before;
import org.junit.Test;
import pages.G44Page;
import pages.UserPage;
import pages.LoginPage;
import pages.tabsPanel.IssuesPage;

public class IssuesTest extends BaseTest{

    private IssuesPage issuesPage;

    @Before
    public void init(){

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login()
                .validateSuccess("Learn Git and GitHub without any code!");
        UserPage userPage = new UserPage(this.driver);
        userPage.searchAndGoToG44Repo();
        G44Page g44Page = new G44Page(this.driver);
        issuesPage = g44Page.goToIssuesTab();
    }

    @Test
    public void reportNewIssueTest(){

        issuesPage.reportNewIssue("Übung macht den Meister");
    }
}
