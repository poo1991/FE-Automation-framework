package stepDefinitions.web;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseWebHelper;
import com.domain.app.webPages.HeaderPage;
import com.domain.app.webPages.HomePage;

import java.net.MalformedURLException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeSteps extends BaseWebHelper {

    HomePage homePage;
    HeaderPage headerPage;

    public HomeSteps(TestContext context) throws MalformedURLException {
        super(context);
        homePage = testContext.getPageRegistry().getHomePage();
        headerPage = testContext.getPageRegistry().getHeaderPage();
    }

    @Then("Academy Homepage is displayed")
    public void verify_academy_homepage_displayed() {
        homePage.wait_for_HomePageLoad();
    }

    @When("I close the Badge Certification modal in Academy Homepage")
    public void close_badgeCert_popup_academy_homepage() {
        homePage.wait_for_HomePageLoad();
        homePage.click_badgeCertification_closeBtn();
    }

}
