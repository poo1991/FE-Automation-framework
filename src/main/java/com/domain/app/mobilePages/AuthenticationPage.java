package com.domain.app.mobilePages;

import com.domain.app.enums.Device;
import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseMobileHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AuthenticationPage extends BaseMobileHelper {

    public AuthenticationPage(TestContext context) throws MalformedURLException {
        super(context);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }


    @iOSXCUITFindBy(accessibility = "Continue")
    private WebElement signInAlertContinue;

    @iOSXCUITFindBy(iOSNsPredicate = "value BEGINSWITH 'User'")
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username1']")
    private WebElement userId;

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Password'")
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='password']")
    private WebElement password;

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'United States'")
    @AndroidFindBy(id = "domainName")
    private WebElement countryRegion;

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Homeoffice' or value == 'Store/Club'")
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='BU']")
    private WebElement location;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='{$textValue}']")
    private WebElement locationOptions;

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'Store/Club Number'")
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='store']")
    private WebElement storeClubNumber;

    @iOSXCUITFindBy(iOSNsPredicate = "value == 'SIGN IN'")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='SIGN IN']")
    private WebElement signIn;

    @AndroidFindBy(xpath = "//android.widget.ProgressBar")
    private WebElement loaderProgressBar;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username1']//parent::android.view.View//preceding-sibling::android.view.View")
    private WebElement errorMsg;

    @iOSXCUITFindBy(accessibility = "Store/Club")
    private WebElement storeClubLocationOption;

    /**
     * This method enters the UserID in the textbox
     *
     * @param userID user ID
     */
    public void enterUserId(String userID) {
        setValue(userId, "UserID", userID);
    }

    /**
     * This method enters the password in the text box
     *
     * @param password login password
     */
    public void enterPassword(String password) {
        setValue(this.password, "Password", password);
    }

    /**
     * This method enters the password in the text box
     *
     * @param location login location
     */
    public void selectLocation(String location) throws InterruptedException {
        if (mobileParams.getMobileDeviceType().equals(Device.IOS)) {
            click(this.location, "Location",storeClubLocationOption);
            click(storeClubLocationOption, "Location Option - Selected" + location);
        } else {
            click(this.location, "Location");
            Thread.sleep(1000);
            WebElement locationOption = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='" + location + "']"));
            click(locationOption, "Location Options");
        }
    }

    /**
     * This method enters the store Number value in the text box
     *
     * @param storeNum login store num
     */
    public void enterStoreNumber(String storeNum) {
        setValue(storeClubNumber, "Store/Club Num", storeNum);
    }

    /**
     * This method clicks on the SignIn button
     */
    public void clickSignIn() {
        click(signIn, "Sign In");
        waitForInVisibility(loaderProgressBar);
    }

    /**
     * This method clicks on the SignIn button
     */
    public String getErrorText() {
        return getText(errorMsg, "Login Error Message");
    }

    /**
     * This method clicks continue in the Sign In alert
     */
    public void click_continue_signInAlert() {
        click(signInAlertContinue, "Continue, Sign In Alert");
    }


}
