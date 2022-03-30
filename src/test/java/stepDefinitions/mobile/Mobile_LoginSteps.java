package stepDefinitions.mobile;

import com.domain.app.enums.Device;
import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseMobileHelper;
import com.domain.app.mobilePages.AuthenticationPage;

import org.testng.Assert;

import java.net.MalformedURLException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Mobile_LoginSteps extends BaseMobileHelper {

    AuthenticationPage authenticationPage;

    public Mobile_LoginSteps(TestContext context) throws MalformedURLException {
        super(context);
        authenticationPage=testContext.getMobilePageRegistry().getAuthenticationPage();
    }

    @Given("I login to Academy with {string}, {string} and {string}")
    public void i_launch_app_on_android(String userId, String password, String storeNum) throws InterruptedException {
        if(mobileParams.getMobileDeviceType().equals(Device.IOS)){
            authenticationPage.click_continue_signInAlert();
        }
        authenticationPage.enterUserId(userId);
        authenticationPage.enterPassword(password);
        authenticationPage.selectLocation("Store/Club");
        authenticationPage.enterStoreNumber(storeNum);
        authenticationPage.clickSignIn();
    }

    @Then("Login Error message {string} is displayed")
    public void verify_login_error_nessage_displayed(String expErrorMessage) {
        Assert.assertEquals(authenticationPage.getErrorText(),expErrorMessage);
    }
}
