package stepDefinitions.mobile;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseMobileHelper;
import com.domain.app.mobilePages.Footer;
import com.domain.app.mobilePages.Header;
import com.domain.app.mobilePages.HomePage;

import java.net.MalformedURLException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Mobile_HomeSteps extends BaseMobileHelper {

    Footer footer;
    Header header;
    HomePage homePage;

    public Mobile_HomeSteps(TestContext context) throws MalformedURLException {
        super(context);
        footer=testContext.getMobilePageRegistry().getFooter();
        header = testContext.getMobilePageRegistry().getHeader();
        homePage=testContext.getMobilePageRegistry().getHomePage();
    }

    @Then("Academy Footer contains Home and Library")
    public void verify_academy_app_footer() {
        footer.verify_home_displayed();
        footer.verify_library_displayed();
        footer.verify_myLearning_displayed();
        footer.verify_teams_displayed();
    }

    @Then("Academy Header contains Menu, logo and Language")
    public void verify_academy_app_header() {
        header.verify_logo_displayed();
        header.verify_menu_displayed();
        header.verify_languageIcon_displayed();
    }

    @Then("Verify Recommended Learning sections displayed")
    public void verify_Recommended_Learning_sections_displayed() throws MalformedURLException {
        homePage.verify_RecommendedLearning_displayed();
        homePage.verify_viewingHistory_displayed();
//        homePage.verify_youMayAlsoLike_displayed();
        homePage.verify_TrendingNow_displayed();
    }

    @When("I close Badge Certification Request pop up if present")
    public void close_badge_certification_request(){
        homePage.click_closeBtn_badgeCertification_modal();
    }
}
