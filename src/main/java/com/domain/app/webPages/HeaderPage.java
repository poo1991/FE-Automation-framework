package com.domain.app.webPages;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseWebHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.net.MalformedURLException;

public class HeaderPage extends BaseWebHelper {


    public HeaderPage(TestContext context) throws MalformedURLException {
        super(context);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@class,'MuiTab-root')]//span[text()='Home']")
    private WebElement homeTab;

    @FindBy(xpath = "//a[contains(@class,'MuiTab-root')]//span[text()='Library']")
    private WebElement libraryTab;

    @FindBy(xpath = "//a[contains(@class,'MuiTab-root')]//span[text()='My Learning']")
    private WebElement myLearningTab;

    @FindBy(xpath = "//a[contains(@class,'MuiTab-root')]//span[text()='Teams']")
    private WebElement teamsTab;

    @FindBy(xpath = "//button[@aria-label='account of current user']")
    private WebElement hamburgerMenuIcon;

    @FindBy(xpath = "//div[@data-testid='menuContent']")
    private WebElement hamburgerMenu;

    @FindBy(xpath = "//div[@data-testid='menuContent']//span[text()='Home']")
    private WebElement home_hamburgerMenu;

    @FindBy(xpath = "//div[@data-testid='menuContent']//span[text()='Library']")
    private WebElement library_hamburgerMenu;

    @FindBy(xpath = "//div[@data-testid='menuContent']//span[text()='My Learning']")
    private WebElement myLearning_hamburgerMenu;

    @FindBy(xpath = "//div[@data-testid='menuContent']//span[text()='Teams']")
    private WebElement teams_hamburgerMenu;

    @FindBy(xpath = "//div[@data-testid='menuContent']/div/div[2]/div[1]")
    private WebElement userName_hamburgerMenu;

    @FindBy(xpath = "//div[@data-testid='menuContent']/div/div[2]/div[2]")
    private WebElement storeNum_hamburgerMenu;

    @FindBy(xpath = "//div[contains(@class,'MuiAvatar-circle')]")
    private WebElement userLogo_hamburgerMenu;

    @FindBy(xpath = "//a[text()='View Profile']")
    private WebElement viewProfileLink_hamburgerMenu;

    public void verify_menuTabs_TL() {
        Assert.assertTrue(isElementDisplayed(homeTab, "Home Tab"));
        Assert.assertTrue(isElementDisplayed(libraryTab, "Library Tab"));
        Assert.assertTrue(isElementDisplayed(myLearningTab, "My Learning Tab"));
        Assert.assertTrue(isElementDisplayed(teamsTab, "Teams"));
    }

    public void click_hamburgerMenuIcon() {
        clickCustomize(hamburgerMenuIcon, "Hamburger Menu Icon", hamburgerMenu);
    }

    public void verify_home_hamburgerMenu_displayed() {
        Assert.assertTrue(isElementDisplayed(home_hamburgerMenu, "Home Hamburger"), "Home Link in Hamburger not displayed");
    }

    public void verify_library_hamburgerMenu_displayed() {
        Assert.assertTrue(isElementDisplayed(library_hamburgerMenu, "Library Hamburger"), "Library Link in Hamburger not displayed");
    }

    public void verify_myLearning_hamburgerMenu_displayed() {
        Assert.assertTrue(isElementDisplayed(myLearning_hamburgerMenu, "My Learning Hamburger"), "My Learning Link in Hamburger not displayed");
    }

    public void verify_teams_hamburgerMenu_displayed() {
        Assert.assertTrue(isElementDisplayed(teams_hamburgerMenu, "Teams Hamburger"), "Teams Link in Hamburger not displayed");
    }

    public String get_userName_hamburgerMenu() {
        return getTextValue(userName_hamburgerMenu);
    }

    public String get_StoreNumber_hamburgerMenu() {
        return getTextValue(storeNum_hamburgerMenu).split(".")[1].trim();
    }

    public void verify_userLogo_hamburgerMenu_displayed() {
        Assert.assertTrue(isElementDisplayed(userLogo_hamburgerMenu, "User Logo Hamburger"), "User Logo in Hamburger not displayed");
    }

    public void verify_viewProfile_link_displayed() {
        Assert.assertTrue(isElementDisplayed(viewProfileLink_hamburgerMenu, "View Profile Hamburger"), "View Profile in Hamburger not displayed");
    }

    public void click_viewProfile_link() {
        clickCustomize(viewProfileLink_hamburgerMenu,"View Profile Hamburger");
    }

    public void click_home_hamburgerMenu() {
        clickCustomize(home_hamburgerMenu,"Home Hamburger",2000);
    }

    public void click_library_hamburgerMenu() {
        clickCustomize(library_hamburgerMenu,"Library Hamburger",2000);
    }

    public void click_myLearning_hamburgerMenu() {
        clickCustomize(myLearning_hamburgerMenu,"My Learning Hamburger",2000);
    }

    public void click_teams_hamburgerMenu() {
        clickCustomize(teams_hamburgerMenu,"Teams Hamburger",2000);
    }
}
