package com.domain.app.helpers;

import com.domain.app.frameworkConfig.Base;
import com.domain.app.frameworkConfig.TestContext;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class BaseWebHelper extends Base {

    public BaseWebHelper(TestContext context) throws MalformedURLException {
        super(context);
    }

    /**
     * This method waits for the page load to be complete
     *
     * @return boolean True if page load is completed else false
     */
    public boolean waitForPageLoad() {
        wait.until(driver -> {
            System.out.println("Current Window state :" + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
        });
        return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
    }

    /**
     * Checks if the given element is present else logs the exception
     *
     * @param element     Webelement
     * @param elementDesc Description of the element
     * @return WebElement
     */
    public WebElement returnElementIfPresent(WebElement element, String elementDesc) {
        WebElement webElement = null;
        try {
            scrollToView(element);
            webElement = wait.until(ExpectedConditions.visibilityOf(element));
            LOG.info("Element " + elementDesc + " present");
            return webElement;
        } catch (Exception e) {
            LOG.error("Element " + elementDesc + " not Present --> " + e.getMessage());
        }
        return webElement;
    }

    /**
     * This returns if the WebElement is displayed or not
     *
     * @param element     WebElement
     * @param elementDesc Description of the element
     * @return boolean : True if element is displayed else false
     */
    public boolean isElementDisplayed(WebElement element, String elementDesc) {
        boolean isElementDisplayed = false;
        try {
            scrollToView(element);
            isElementDisplayed = returnElementIfPresent(element, elementDesc).isDisplayed();
            LOG.info(elementDesc + " is displayed");
        } catch (Exception e) {
            LOG.error("Element " + elementDesc + " not displayed --> " + e.getMessage());
        }
        return isElementDisplayed;
    }

    /**
     * Scrolls down the page to get the passed element into view
     *
     * @param element WebElement to be scrolled to
     */
    public void scrollToView(WebElement element) {
        try {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeScript("arguments[0].scrollIntoView(); ", element);
            je.executeScript("window.scrollBy(0,-100)", "");
        } catch (Exception e) {
            LOG.error("Unable to scroll" + e.getMessage());
        }
    }

    /**
     * This method sets the value of a given text box element
     *
     * @param element      Textbox Webelement
     * @param elementValue value to be entered in the text box
     * @param elementDesc  Description of the element
     */
    public void setTextBoxValue(WebElement element, String elementValue, String elementDesc) {
        try {
            scrollToView(element);
            element.clear();
            element.sendKeys(elementValue);
            LOG.info("Text box " + elementDesc + " value set to:" + elementValue);
        } catch (Exception e) {
            LOG.error("Text box" + elementDesc + " not set" + e.getMessage());
        }
    }

    /**
     * This method clicks on the given Web element
     *
     * @param element     Webelement to be clicked on
     * @param elementDesc Description of the element
     */
    public void clickCustomize(WebElement element, String elementDesc) {
        returnElementIfPresent(element, elementDesc).click();
        LOG.info(elementDesc + " clicked");
    }

    /**
     * This method clicks on the given Web element
     *
     * @param element         Webelement to be clicked on
     * @param elementDesc     Description of the element
     * @param timeInMilliSecs number of milliseconds to wait after the click
     */
    public void clickCustomize(WebElement element, String elementDesc, int timeInMilliSecs) {
        try {
            returnElementIfPresent(element, elementDesc).click();
            LOG.info(elementDesc + " clicked and waited for " + timeInMilliSecs + " seconds");
            Thread.sleep(timeInMilliSecs);
        } catch (Exception e) {
            LOG.error("Exception on wait after click --> " + e.getMessage());
        }
    }

    /**
     * This method clicks on the given Web element
     *
     * @param element         Webelement to be clicked on
     * @param elementDesc     Description of the element
     * @param expectedElement Expected element after click
     */
    public void clickCustomize(WebElement element, String elementDesc, WebElement expectedElement) {
        try {
            returnElementIfPresent(element, elementDesc).click();
            waitForVisibility(expectedElement);
        } catch (Exception e) {
            LOG.error("Exception on wait after click --> " + e.getMessage());
        }
    }

    /**
     * @param element   Webelement
     * @param attribute attribute for which value is needed
     * @return String value of the attribute
     */
    public String getAttributeValue(WebElement element, String attribute) {
        String attributeValue = "";
        try {
            return returnElementIfPresent(element, "").getAttribute(attribute);
        } catch (Exception e) {
            LOG.error("Cannot get Attribute " + e.getMessage());
        }
        return attributeValue;
    }


    /**
     * @param element Webelement
     * @return String value of the text
     */
    public String getTextValue(WebElement element) {
        String txtValue = "";
        try {
            return returnElementIfPresent(element, "").getText();
        } catch (Exception e) {
            LOG.error("Cannot get text " + e.getMessage());
        }
        return txtValue ;
    }

    /**
     * This method switches to the newly opened tab
     */
    public void switchToNewTab() {
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            String newWindowHandle = tabs.get(1);
            driver.switchTo().window(newWindowHandle);
            LOG.info("Switched to the newly opened tab" + driver.getTitle());
        } catch (Exception e) {
            LOG.error("Unable to switch to new tab" + e.getMessage());
        }
    }

    /**
     * This method hovers on the element
     *
     * @param element     Webelement to be hovered on
     * @param elementDesc Short Description of the element
     */
    public void hoverOnElement(WebElement element, String elementDesc) {
        try {
            Actions actions = new Actions(this.driver);
            actions.moveToElement(element).build().perform();
            LOG.info("Hovered on element:" + elementDesc);
        } catch (Exception e) {
            LOG.error("Unable to hover on element:" + elementDesc + " -->" + e.getMessage());
        }
    }


    /**
     * This method selects a value from dropdown
     * @param element Webelement to select value for
     * @param elementDesc  Short Description of the element
     * @param value Value to select
     */
    public void selectDropdownByValue(WebElement element, String elementDesc,String value) {
        try {
            Select dropdown = new Select(element);
            dropdown.selectByValue(value);
            LOG.info("Selected Value:"+value+" for dropdown" + elementDesc);
        } catch (Exception e) {
            LOG.error("Unable to select dropdown value:" + elementDesc + " -->" + e.getMessage());
        }
    }

    /**
     * This method selects a value from dropdown
     * @param element Webelement to select value for
     * @param elementDesc  Short Description of the element
     * @param visibleText Visible text Value to select
     */
    public void selectDropdownByText(WebElement element, String elementDesc,String visibleText) {
        try {
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(visibleText);
            LOG.info("Selected Value:"+visibleText+" for dropdown" + elementDesc);
        } catch (Exception e) {
            LOG.error("Unable to select dropdown value:" + elementDesc + " -->" + e.getMessage());
        }
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInVisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
        LOG.info(element+" invisible");
    }

    public void waitForInVisibilityOfAll(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    /**
     * This method adds a cookie to ensure captcha is disabled while login
     */
    public void updateCookiePxwl() {
      //  Cookie cname = new Cookie.Builder("px-wl", "77cc3481-7f84-473a-b1da-4db8135afddc").isSecure(true).sameSite("Lax").build();
        driver.manage().deleteCookieNamed("px-wl");
        Cookie cname = new Cookie("px-wl", "77cc3481-7f84-473a-b1da-4db8135afddc");
        driver.manage().addCookie(cname);
    }


    /**
     *
     * @param expTitle Expected Title
     */
    public void waitForTitle(String expTitle) {
        wait.until(ExpectedConditions.titleIs(expTitle));
    }


}
