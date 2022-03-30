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

public class Header extends BaseMobileHelper {

    public Header(TestContext context) throws MalformedURLException {
        super(context);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @iOSXCUITFindBy(accessibility = "Logo")
    @AndroidFindBy(accessibility = "Logo Walmart Academy")
    private WebElement logoWalmartAcademy;

    @iOSXCUITFindBy(accessibility = "account of current user")
    @AndroidFindBy(xpath = "//android.view.View[@text='Menu']")
    private WebElement menuBtn;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == 'banner'`][1]/XCUIElementTypeOther[5]")
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='select']")
    private WebElement languageIcon;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='standard-basic']")
    private WebElement searchTextBox;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == 'menu'`]")
    @AndroidFindBy(xpath = "//android.view.View[@text='POPULAR SEARCHES']//preceding-sibling::android.view.View[2]//android.widget.Button")
    private WebElement searchTextGoBtn;

    /**
     * This method verifies if the Walmart Academy logo is present
     */
    public void verify_logo_displayed(){
        Assert.assertTrue(isElementDisplayed(logoWalmartAcademy,"Walmart Academy logo"));
    }

    /**
     * This method verifies if the Menu Button is present and displayed
     */
    public void verify_menu_displayed(){
        Assert.assertTrue(isElementDisplayed(menuBtn,"Menu Button"));
    }

    /**
     * This method verifies if the language icon is present and displayed
     */
    public void verify_languageIcon_displayed(){
        Assert.assertTrue(isElementDisplayed(languageIcon,"Language button"));
    }



}
