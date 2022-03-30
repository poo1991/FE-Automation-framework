package com.domain.app.webPages;

import com.domain.app.frameworkConfig.TestContext;
import com.domain.app.helpers.BaseWebHelper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class LibraryPage extends BaseWebHelper {

    public LibraryPage(TestContext context) throws MalformedURLException {
        super(context);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@data-testid='userinfo']")
    private WebElement userInfoSection;

    /**
     * This methods returns the current URL of the Page
     * @return String current URL
     */
   public String get_currentUrl(){
       return driver.getCurrentUrl();
   }



}
