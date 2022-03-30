package stepDefinitions.web;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseWebHelper;
import com.domain.app.utility.ConfigFileReader;
import com.domain.app.webPages.AuthorizationPage;

import java.net.MalformedURLException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseWebHelper {
    AuthorizationPage authorizationPage;
    ConfigFileReader configFileReader;

    public LoginSteps(TestContext context) throws MalformedURLException {
        super(context);
        authorizationPage = testContext.getPageRegistry().getAuthorizationPage();
        configFileReader = new ConfigFileReader();
    }

    @Given("Login Page of Academy is displayed")
    public void loginPage_displayed() {
       authorizationPage.openAcademyApplication(configFileReader.getApplicationUrl());

    }

    @When("I login to Academy Web App with Username:{string}, Password:{string} and Store:{string}")
    public void login_to_academyWebApp(String userId, String password,String storeNum) {
        authorizationPage.enter_UserID(userId);
        authorizationPage.enter_Password(password);
        authorizationPage.select_Location("Store/Club");
        authorizationPage.enter_StoreId(storeNum);
        authorizationPage.click_SignIn();
    }

    @Given("HomePage is displayed after login with Username:{string}, Password:{string} and Store:{string}")
    public void login_to_academyWebApp_success(String userId, String password,String storeNum) {
        authorizationPage.enter_UserID(userId);
        authorizationPage.enter_Password(password);
        authorizationPage.select_Location("Store/Club");
        authorizationPage.enter_StoreId(storeNum);
        authorizationPage.click_SignIn();
        authorizationPage.wait_for_loaderToDisapper();

    }
}
