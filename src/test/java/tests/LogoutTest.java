package tests;

import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.UserPage;

public class LogoutTest extends BaseTest{

    private UserPage userPage;

    @Before
    public void init(){

        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login()
                .validateSuccess("Learn Git and GitHub without any code!");
        this.userPage = new UserPage(this.driver);
    }

    @Test
    public void signOut(){

        this.userPage.signOut().validateIsSignedOut();
    }
}
