package com.domain.app.webPages;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseWebHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class AuthorizationPage extends BaseWebHelper {

    public AuthorizationPage(TestContext context) throws MalformedURLException {
        super(context);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='username1']")
    private WebElement userIdTextbox;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordTxtbox;

    @FindBy(xpath = "//select[@id='BU']")
    private WebElement locationDrpdown;

    @FindBy(xpath = "//select[@id='domainName']")
    private WebElement countryDrpdown;

    @FindBy(xpath = "//a[@title='SIGN IN']")
    private WebElement signInBtn;

    @FindBy(id = "store")
    private WebElement storeNumTxtbox;

    @FindBy(xpath ="//div[@data-testid='loadScreenCircularProgressBar']")
    private WebElement loginLoader;

    public  void openAcademyApplication(String url){
        driver.get(url);
//        waitForPageLoad();
        waitForTitle("Sign in");
        waitForVisibility(signInBtn);
    }

    /**
     * This method enters the value in the username field
     *
     * @param userID the username of the user
     */
    public void enter_UserID(String userID) {
         setTextBoxValue(userIdTextbox, userID, "Login UserId");
    }

    /**
     * This method enters the value in the password field
     *
     * @param password the password of the user
     */
    public void enter_Password(String password) {
         setTextBoxValue(passwordTxtbox, password, "Password");
    }

    /**
     * This method clicks on the login button
     */
    public void select_Location(String location) {
         selectDropdownByText(locationDrpdown,"Location",location);
    }

    /**
     * This method enters the value in the store field
     *
     * @param storeId the storeID to login To
     */
    public void enter_StoreId(String storeId) {
         setTextBoxValue(storeNumTxtbox, storeId, "Store Num");
    }


    /**
     * This method clicks on the SignIn Button
     */
    public void click_SignIn() {
         clickCustomize(signInBtn,"Sign In",3000);
    }

    /**
     * This method clicks on the SignIn Button
     */
    public void wait_for_loaderToDisapper() {
         waitForInVisibility(loginLoader);
    }

}
