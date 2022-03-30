package com.domain.app.webPages;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseWebHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class HomePage extends BaseWebHelper {

    public HomePage(TestContext context) throws MalformedURLException {
        super(context);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button//span[text()='Close']")
    private WebElement closeBtnBadgeCert;

    @FindBy(xpath = "//*[@data-testid='loadingContent']")
    private WebElement loadingContent;

    @FindBy(xpath = "//a[contains(@class,'MuiTab-root')]//span[text()='Home']")
    private WebElement homeTab;

    @FindBy(xpath = "//div[text()='Trending Now']")
    private WebElement trendingNow;

    public void click_badgeCertification_closeBtn() {
        waitForVisibility(closeBtnBadgeCert);
        if (isElementDisplayed(closeBtnBadgeCert, "Close Btn"))
            clickCustomize(closeBtnBadgeCert, "Close Btn Badge Certification");
    }

    public void wait_for_HomePageLoad() {
        waitForInVisibility(loadingContent);
//        waitForInVisibilityOfAll(loadingContent);
//        waitForVisibility(trendingNow);
        waitForVisibility(closeBtnBadgeCert);

    }


}
