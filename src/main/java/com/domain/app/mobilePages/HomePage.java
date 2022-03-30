package com.domain.app.mobilePages;

import com.domain.app.enums.ScrollDirection;
import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseMobileHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.net.MalformedURLException;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends BaseMobileHelper {

    public HomePage(TestContext context) throws MalformedURLException {
        super(context);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    @iOSXCUITFindBy(accessibility = "Close")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Close']")
    private WebElement badgeCertModalCloseBtn;

    @iOSXCUITFindBy(accessibility = "Recommended Learning")
    @AndroidFindBy(xpath = "//android.view.View[@text='Recommended Learning']")
    private WebElement recommendedLearningText;

    @iOSXCUITFindBy(accessibility = "Based on Your Viewing History")
    @AndroidFindBy(xpath = "//android.view.View[contains(@text,'Viewing History')]")
    private WebElement basedOnViewHistoryText;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'View All'`][2]")
    @AndroidFindBy(xpath = "//android.view.View[contains(@text,'Viewing History')]/following-sibling::android.view.View/android.view.View")
    private WebElement viewAllBasedOnViewHistory;

    @AndroidFindBy(xpath = "//android.view.View[@text='You May Also Like']")
    private WebElement youMayAlsoLikeText;

    @AndroidFindBy(xpath = "(//android.view.View[@text='You May Also Like']/following-sibling::android.view.View/android.view.View)[1]")
    private WebElement viewAllyouMayAlsoLike;

    @iOSXCUITFindBy(accessibility = "Trending Now")
    @AndroidFindBy(xpath = "//android.view.View[@text='Trending Now']")
    private WebElement trendingNowText;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'View All'`][3]")
    @AndroidFindBy(xpath = "//android.view.View[@text='Trending Now']/following-sibling::android.view.View/android.view.View")
    private WebElement viewAllTrendingNow;

    /**
     * This method clicks on the Close button in the Badge Certification Modal
     */
    public void click_closeBtn_badgeCertification_modal() {
        if (isElementDisplayed(badgeCertModalCloseBtn, "Close Badge Certification"))
            click(badgeCertModalCloseBtn, "Close Badge Certification");
    }

    /**
     * This method verifies if Trending now is displayed
     */
    public void verify_RecommendedLearning_displayed() {
        Assert.assertTrue(isElementDisplayed(recommendedLearningText, "Recommended Learning"));
        Assert.assertEquals(getText(recommendedLearningText, "Recommended Learning"), "Recommended Learning");
    }

    /**
     * This method verifies if Trending now is displayed
     */
    public void verify_TrendingNow_displayed() throws MalformedURLException {
        scrollToElement(trendingNowText, ScrollDirection.DOWN);
        Assert.assertTrue(isElementDisplayed(trendingNowText, "Trending Now"));
        Assert.assertEquals(getText(trendingNowText, "Trending Now"), "Trending Now");
        Assert.assertTrue(isElementDisplayed(viewAllTrendingNow, "View All - Trending Now"));
//        Assert.assertTrue(getAttribute(viewAllTrendingNow, "View All - Trending Now","content-desc").contains("View All"), "View All Trending text is not as expected");
    }

    /**
     * This method verifies if You May Also Like text is displayed
     */
    public void verify_youMayAlsoLike_displayed() throws MalformedURLException {
        scrollToElement(youMayAlsoLikeText, ScrollDirection.DOWN);
        Assert.assertTrue(isElementDisplayed(youMayAlsoLikeText, "You May Also Like"));
        Assert.assertEquals(getText(youMayAlsoLikeText, "You May Also Like"), "You May Also Like");
        Assert.assertTrue(isElementDisplayed(viewAllyouMayAlsoLike, "View All - You May Also Like"));
//        Assert.assertTrue(getAttribute(viewAllyouMayAlsoLike, "View All - You May Also Like","content-desc").contains("View All"), "View All - You may also like text is not as expected");

    }

    /**
     * This method verifies if Based on Your Viewing History text is displayed
     */
    public void verify_viewingHistory_displayed() throws MalformedURLException {
        scrollToElement(basedOnViewHistoryText, ScrollDirection.DOWN);
        Assert.assertTrue(isElementDisplayed(basedOnViewHistoryText, "Based on Your Viewing History"));
        Assert.assertEquals(getText(basedOnViewHistoryText, "Based on Your Viewing History"), "Based on Your Viewing History");
        Assert.assertTrue(isElementDisplayed(viewAllBasedOnViewHistory, "View All - Based on Your Viewing History"));
//        System.out.println(getAttribute(viewAllBasedOnViewHistory, "View All - Based on Your Viewing History","content-desc"));
//        Assert.assertTrue(getAttribute(viewAllBasedOnViewHistory, "View All - Based on Your Viewing History","content-desc").contains("View All"), "View All - Based on Your Viewing History text is not as expected");

    }

}
