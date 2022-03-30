package com.domain.app.mobilePages;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseMobileHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.net.MalformedURLException;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Footer extends BaseMobileHelper {

    public Footer(TestContext context) throws MalformedURLException {
        super(context);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @iOSXCUITFindBy(accessibility = "Home")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Home']")
    private WebElement homeBtn;

    @iOSXCUITFindBy(accessibility = "Library")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Library']")
    private WebElement libraryBtn;

    @iOSXCUITFindBy(accessibility = "My Learning")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='My Learning']")
    private WebElement myLearningBtn;

    @iOSXCUITFindBy(accessibility = "Teams")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Teams']")
    private WebElement teamsBtn;

    /**
     * This method verifies if Home button is displayed
     */
    public void verify_home_displayed(){
        Assert.assertTrue(isElementDisplayed(homeBtn,"Home Button"));
    }

    /**
     * This method verifies if Library button is displayed
     */
    public void verify_library_displayed(){
        Assert.assertTrue(isElementDisplayed(libraryBtn,"Library Button"));
    }

    /**
     * This method verifies if My Learning button is displayed
     */
    public void verify_myLearning_displayed(){
        Assert.assertTrue(isElementDisplayed(myLearningBtn,"My Learning Button"));
    }

    /**
     * This method verifies if Teams button is displayed
     */
    public void verify_teams_displayed(){
        Assert.assertTrue(isElementDisplayed(teamsBtn,"Teams Button"));
    }


}
