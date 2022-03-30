package stepDefinitions.web;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseWebHelper;
import com.domain.app.webPages.HeaderPage;

import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

public class CommonSteps extends BaseWebHelper {

    HeaderPage headerPage;

    public CommonSteps(TestContext context) throws MalformedURLException {
        super(context);
        headerPage = testContext.getPageRegistry().getHeaderPage();
    }

    @When("I click on Hamburger Menu icon")
    public void click_hamburger_menu_icon() {
        headerPage.click_hamburgerMenuIcon();
    }

    @When("User information is displayed in the Hamburger Menu for {string}")
    public void verify_userInfo_hamburgerMenu(String userName) {
        String actualName = headerPage.get_userName_hamburgerMenu();
        Assert.assertEquals(actualName, userName, "User Name in Hamburger Menu not as expected");

    }

    @When("Verify links in the Main Navigation for {string}")
    public void verify_mainNavigation_hamburgerMenu(String userRole) {
        if (userRole.equalsIgnoreCase("TL")) {
            headerPage.verify_home_hamburgerMenu_displayed();
            headerPage.verify_library_hamburgerMenu_displayed();
            headerPage.verify_myLearning_hamburgerMenu_displayed();
            headerPage.verify_teams_hamburgerMenu_displayed();
        } else if (userRole.equalsIgnoreCase("TA")) {
            headerPage.verify_home_hamburgerMenu_displayed();
            headerPage.verify_library_hamburgerMenu_displayed();
        } else
            throw new IllegalArgumentException(userRole + "is not a valid input");
    }

    @When("Verify navigation on click of below links under Main Navigation in the Hamburger Menu")
    public void verify_clickOnLink_mainNavigation_hamburgerMenu(DataTable linkNames) {
        String baseUrl =reader.getApplicationUrl();
        List<Map<String, String>> links = linkNames.asMaps(String.class, String.class);
        for (Map<String, String> link : links) {
            switch (link.get("Hamburger_Menu_Links").toLowerCase()) {
                case "home":
                    headerPage.click_hamburgerMenuIcon();
                    headerPage.click_home_hamburgerMenu();
//                    Assert.assertTrue(driver.getCurrentUrl().contains("home"),"URL does not contain home");
                    Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/home");

                    break;

                case "library":
                    headerPage.click_hamburgerMenuIcon();
                    headerPage.click_library_hamburgerMenu();
//                    Assert.assertTrue(driver.getCurrentUrl().contains("library"),"URL does not contain library");
                    Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/library");
                    break;

                case "my learning":
                    headerPage.click_hamburgerMenuIcon();
                    headerPage.click_myLearning_hamburgerMenu();
//                    Assert.assertTrue(driver.getCurrentUrl().contains("mylearning"),"URL does not contain mylearning");
                    Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/mylearning");
                    break;

                case "teams":
                    headerPage.click_hamburgerMenuIcon();
                    headerPage.click_teams_hamburgerMenu();
                    Assert.assertTrue(driver.getCurrentUrl().contains("teams"), "URL does not contain teams");
                    Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/teams");
                    break;

                case "view profile":
                    headerPage.click_hamburgerMenuIcon();
                    headerPage.click_viewProfile_link();
                    Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/myprofile");
                    break;

                default:
                    throw new IllegalArgumentException(link + "is not a valid input");
            }
        }

    }

}
