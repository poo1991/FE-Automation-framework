package com.domain.app.helpers;

import com.domain.app.dto.MobileParams;
import com.domain.app.enums.Device;
import com.domain.app.enums.ScrollDirection;
import com.domain.app.frameworkConfig.Base;
import com.domain.app.frameworkConfig.TestContext;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;


public class BaseMobileHelper extends Base {

    public MobileParams mobileParams = new MobileParams();
    public BaseMobileHelper(TestContext context) throws MalformedURLException {
        super(context);
    }

    public void waitForVisibility(WebElement element) {
            wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitForInVisibility(WebElement element) {
            wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * This method clears the text from an input box
     *
     * @param element WebElement to clear
     */
    public void clearText(WebElement element, String elementDesc) {
        waitForVisibility(element);
        try {
            element.clear();
            LOG.info("Cleared element " + elementDesc);
        } catch (Exception e) {
            LOG.error("Exception while Clearing element " + elementDesc+"\n"+ e.getMessage());
        }
    }

    /**
     * This method clicks on the element
     *
     * @param element     WebElement
     * @param elementDesc Element Name
     */
    public void click(WebElement element, String elementDesc) {
        waitForVisibility(element);
        System.out.println(mobileParams.getDeviceName());
        LOG.info(mobileParams.getDeviceName());

        try {
            element.click();
            LOG.info("Clicked on " + elementDesc);
        } catch (Exception e) {
            LOG.error("Exception clicking element " + elementDesc+"\n"+ e.getMessage());
        }
    }

    /**
     * This method clicks on the element and waits for the visibility of the expected element
     *
     * @param element         WebElement
     * @param elementDesc     Element Name
     * @param expectedElement expected element after click
     */
    public void click(WebElement element, String elementDesc, WebElement expectedElement) {
        waitForVisibility(element);
        try {
            element.click();
            LOG.info("Clicked on " + elementDesc);
        } catch (Exception e) {
            LOG.error("Exception clicking element " + elementDesc+"\n"+ e.getMessage());
        }
        waitForVisibility(expectedElement);
    }

    /**
     * This method sets the value of an input textbox
     *
     * @param element     Webelement to edit
     * @param elementDesc Element Name
     * @param value       value to set for the element
     */
    public void setValue(WebElement element, String elementDesc, String value) {
        waitForVisibility(element);
        System.out.println(mobileParams.getDeviceName());
        LOG.info(mobileParams.getDeviceName());
        try {
            if(mobileParams.getMobileDeviceType().equals(Device.IOS)){
//                ((IOSDriver) driver).hideKeyboard();
                value =value+"\n";
            }
            element.sendKeys(value);
            LOG.info("Entered value: " + value + " in element:" + elementDesc);
        } catch (Exception e) {
            LOG.error("Exception setting value of element " + elementDesc+"\n"+ e.getMessage());
        }
    }

    /**
     * This method returns the attribute value for the Webelement
     *
     * @param element     Webelement
     * @param elementDesc Element Name
     * @param attribute   attribute whose value has to be fetched
     * @return String value of the attribute
     */
    public String getAttribute(WebElement element, String elementDesc, String attribute) {
        waitForVisibility(element);
        String attrValue = "";
        try {
            attrValue = element.getAttribute(attribute);
            LOG.info("Attribute " + attribute + " value is:" + attrValue);
        } catch (Exception e) {
            LOG.error("Exception getAttribute of element " + elementDesc+"\n"+ e.getMessage());
        }
        return attrValue;
    }

    /**
     * This method returns the text value for the Webelement
     *
     * @param element     Webelement
     * @param elementDesc Element Name/Desc
     * @return String text of the element
     */
    public String getText(WebElement element, String elementDesc) {
        String txt;
        switch (mobileParams.getMobileDeviceType()) {
            case ANDROID:
                txt = getAttribute(element, elementDesc, "text");
                break;
            case IOS:
                txt = getAttribute(element, elementDesc, "label");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mobileParams.getMobileDeviceType());
        }
        LOG.info("Retrieved Text:" + txt + " for element" + elementDesc);
        return txt;
    }

    /**
     * Checks if the given element is present else logs the exception
     *
     * @param element     Webelement
     * @param elementDesc Element Name/Description of the element
     * @return WebElement
     */
    public WebElement returnElementIfPresent(WebElement element, String elementDesc) {
        WebElement webElement = null;
        try {
            webElement = wait.until(ExpectedConditions.visibilityOf(element));
            LOG.info("Element " + elementDesc + " present");
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
            isElementDisplayed = returnElementIfPresent(element, elementDesc).isDisplayed();
            LOG.info(elementDesc + " is displayed");
        } catch (Exception e) {
            LOG.error("Element " + elementDesc + " not displayed --> " + e.getMessage());
        }
        return isElementDisplayed;
    }

//    public void closeApp() {
//        ((InteractsWithApps) driver).closeApp();
//    }
//
//    public void launchApp() {
//        ((InteractsWithApps) driver).launchApp();
//    }

//    public WebElement andScrollToElementUsingUiScrollable(String childLocAttr, String childLocValue) {
//        return (WebElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
//                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
//                        + "new UiSelector()."+ childLocAttr +"(\"" + childLocValue + "\"));");
//    }

//    public WebElement iOSScrollToElementUsingMobileScroll(WebElement element) {
//        RemoteWebElement element = ((RemoteWebElement) e);
//        String elementID = element.getId();
//        HashMap<String, String> scrollObject = new HashMap<String, String>();
//        scrollObject.put("element", elementID);
////	  scrollObject.put("direction", "down");
////	  scrollObject.put("predicateString", "label == 'ADD TO CART'");
////	  scrollObject.put("name", "test-ADD TO CART");
//        scrollObject.put("toVisible", "sdfnjksdnfkld");
//        driver.executeScript("mobile:scroll", scrollObject);
//        return e;
//    }

//    public By iOSScrollToElementUsingMobileScrollParent(WebElement parentE, String predicateString) {
//        RemoteWebElement parent = (RemoteWebElement)parentE;
//        String parentID = parent.getId();
//        HashMap<String, String> scrollObject = new HashMap<String, String>();
//        scrollObject.put("element", parentID);
////	  scrollObject.put("direction", "down");
//        scrollObject.put("predicateString", predicateString);
////	  scrollObject.put("name", "test-ADD TO CART");
////        scrollObject.put("toVisible", "sdfnjksdnfkld");
//        driver.executeScript("mobile:scroll", scrollObject);
//        By m = MobileBy.iOSNsPredicateString(predicateString);
//        System.out.println("Mobilelement is " + m);
//        return m;
//    }

    /**
     * This method is used to scroll to WebElement
     *
     * @param element   Webelement to be scrolled to
     * @param direction Scroll Direction enum
     * @return Webelement
     */
    public WebElement scrollToElement(WebElement element, ScrollDirection direction) throws MalformedURLException {
        ScrollMobileHelper scrollHelper = new ScrollMobileHelper(testContext);
        boolean isFound = false;
        for (int i = 0; i < 3; i++) {
            if (find(element)) {
                isFound = true;
                break;
            } else {
                scrollHelper.scroll(direction);
            }
        }
        if (!isFound) {
            LOG.error("Element Not Found");
        }
        return element;
    }

    public boolean find(WebElement element) {
        try {
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(2));
            return wait1.until((ExpectedCondition<Boolean>) driver -> element.isDisplayed());
        } catch (Exception e) {
            return false;
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

}
