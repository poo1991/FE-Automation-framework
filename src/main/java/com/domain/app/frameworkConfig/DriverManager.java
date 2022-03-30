package com.domain.app.frameworkConfig;

import com.domain.app.dto.MobileParams;
import com.domain.app.enums.Browser;
import com.domain.app.enums.Device;
import com.domain.app.enums.EnvironmentType;
import com.domain.app.utility.ConfigFileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.Scenario;

public class DriverManager {
    private WebDriver driver;
    private AppiumDriver appiumDriver;
    private Browser browser;
    private final EnvironmentType environmentType;
    private final String testPlatform;
    private Device mobileDeviceType;
    private final ConfigFileReader reader = new ConfigFileReader();
    private MobileParams mobileParams;
    private final static String PLEXUS_URL = "http://ondemand.plexus.walmart.com/wd/hub";
    private final static String SAUCE_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
    private static final Logger LOG = LogManager.getLogger();
    private Scenario scenario;



    public DriverManager() {
        testPlatform = reader.getTestPlatform();
        if (testPlatform.equals("Desktop"))
            browser = reader.getBrowser();
        environmentType = reader.getEnvironment();

    }

    /**
     * This methods gets the platform specific driver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            if (testPlatform.equals("Desktop")) {
                driver = setDesktopDriver();
                driver.manage().window().maximize();
                return driver;
            } else if (testPlatform.equals("Mobile")) {
                mobileParams = new MobileParams();
                mobileDeviceType = mobileParams.getMobileDeviceType();
                appiumDriver = setMobileDriver();
                this.driver = appiumDriver;
                return appiumDriver;
            } else {
                driver = setDesktopDriver();
                driver.manage().window().maximize();
                return driver;
            }
        }
        return driver;
    }

    /**
     * This method sets the driver based on the environment
     *
     * @return Webdriver
     */
    private WebDriver setDesktopDriver() throws MalformedURLException {
        switch (environmentType) {
            case LOCAL:
                driver = setLocalDriver();
                break;
            case PLEXUS:
                driver = setRemoteDriver_Plexus();
                break;
            case SAUCELABS:
                driver = setRemoteDriver_SauceLabs();
                break;
            default:
                driver = setLocalDriver();
                break;
        }
        return driver;
    }

    /**
     * This method sets the driver based on the environment
     *
     * @return Webdriver
     */
    private AppiumDriver setMobileDriver() throws MalformedURLException {
        switch (environmentType) {
            case LOCAL:
                return setLocalAppiumDriver();
            case SAUCELABS:
                return setRemoteAppiumDriver();
            default:
                return setLocalAppiumDriver();
        }
    }

    /**
     * This method initializes the driver for local execution
     *
     * @return WebDriver
     */
    private WebDriver setLocalDriver() {
        LOG.info("Set Local Driver for browser" + browser);
        switch (browser) {
            case CHROME:
//                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.driver", reader.getDriverPath());
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-web-security");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-gpu");
                options.setHeadless(true);
                driver = new ChromeDriver(options);
                break;

            case FIREFOX:
//                WebDriverManager.firefoxdriver().setup();
                System.setProperty("webdriver.gecko.driver", reader.getDriverPath());
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("--disable-web-security");
                ffOptions.addArguments("--start-maximized");
                ffOptions.addArguments("--disable-gpu");
                ffOptions.setHeadless(true);
                driver = new FirefoxDriver(ffOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser " + browser.name() + "not supported");
        }
        return driver;
    }

    /**
     * This method initializes the driver for remote execution
     *
     * @return WebDriver
     */
    private WebDriver setRemoteDriver_Plexus() throws MalformedURLException {
        LOG.info("Set Remote Driver for browser" + browser);
        System.out.println("Set Remote Driver for browser" + browser);
        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-web-security");
                options.addArguments("disable-infobars");
                options.addArguments("disable-extensions");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-gpu");
                options.setHeadless(true);
                options.setCapability("plexusUserName", reader.getPlexusParams().getPlexusUserName());
                options.setCapability("plexusUserKey", reader.getPlexusParams().getPlexusUserKey());
                options.setCapability("platformName", reader.getPlexusParams().getPlatformName());
                options.setCapability("browserName", "chrome");
                try {
                    driver = new RemoteWebDriver(new URL(PLEXUS_URL), options);
                } catch (MalformedURLException e) {
                    LOG.error("Remote webDriver exception" + e.getMessage());
                }
                break;

            case FIREFOX:
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.addArguments("disable-infobars");
                ffOptions.addArguments("disable-extensions");
                ffOptions.addArguments("--disable-web-security");
                ffOptions.addArguments("--start-maximized");
                ffOptions.addArguments("--disable-gpu");
                ffOptions.setHeadless(true);
                ffOptions.setCapability("plexusUserName", reader.getPlexusParams().getPlexusUserName());
                ffOptions.setCapability("plexusUserKey", reader.getPlexusParams().getPlexusUserKey());
                ffOptions.setCapability("platformName", reader.getPlexusParams().getPlatformName());
                ffOptions.setCapability("browserName", "firefox");
//                ffOptions.setCapability("browserVersion", "95.0");

                try {
                    driver = new RemoteWebDriver(new URL(PLEXUS_URL), ffOptions);
                } catch (MalformedURLException e) {
                    LOG.error("Remote webDriver exception" + e.getMessage());
                }
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("disable-infobars");
                edgeOptions.addArguments("disable-extensions");
                edgeOptions.addArguments("--disable-web-security");
                edgeOptions.addArguments("--disable-gpu");
                edgeOptions.setCapability("plexusUserName", reader.getPlexusParams().getPlexusUserName());
                edgeOptions.setCapability("plexusUserKey", reader.getPlexusParams().getPlexusUserKey());
                edgeOptions.setCapability("platformName", reader.getPlexusParams().getPlatformName());
                edgeOptions.setCapability("browserName", "microsoftedge");
//                edgeOptions.setCapability("browserVersion", "92.0.878.0");
                try {
                    driver = new RemoteWebDriver(new URL(PLEXUS_URL), edgeOptions);
                } catch (MalformedURLException e) {
                    LOG.error("Remote webDriver exception" + e.getMessage());
                }
                break;

            default:
                throw new IllegalArgumentException("Browser " + browser.name() + "not supported");

        }
        return driver;
    }


    /**
     * This method initializes the driver for remote execution
     *
     * @return WebDriver
     */
    private WebDriver setRemoteDriver_SauceLabs() throws MalformedURLException {
        LOG.info("START REMOTE WEBDRIVER - SAUCE LABS");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", reader.getSauceUserName());
        sauceOptions.put("accessKey", reader.getSauceAccessKey());
        sauceOptions.put("parentTunnel", reader.getParentTunnel());
        sauceOptions.put("tunnelIdentifier", reader.getTunnelIdentifier());
        //                sauceOptions.put("name", method.getName());
        URL url = new URL(SAUCE_URL);
        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.setPlatformName(reader.getSauceOS());
                options.setBrowserVersion(reader.getSauceBrowserVersion());
                options.addArguments("--disable-web-security");
//                options.addArguments("disable-infobars");
                options.addArguments("disable-extensions");
//                options.addArguments("--start-maximized");
                options.addArguments("ignore-certificate-errors");
                options.addArguments("--disable-gpu");
                options.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(url, options);
                break;

            case FIREFOX:
                FirefoxOptions browserOptions = new FirefoxOptions();
                browserOptions.setPlatformName(reader.getSauceOS());
                browserOptions.setBrowserVersion(reader.getSauceBrowserVersion());
                browserOptions.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(url, browserOptions);
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPlatformName(reader.getSauceOS());
                edgeOptions.setBrowserVersion(reader.getSauceBrowserVersion());
                edgeOptions.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(url, edgeOptions);
                break;

            case SAFARI:
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setPlatformName(reader.getSauceOS());
                safariOptions.setBrowserVersion(reader.getSauceBrowserVersion());
                safariOptions.setCapability("sauce:options", sauceOptions);
                driver = new RemoteWebDriver(url, safariOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser " + browser.name() + "not supported");

        }
        return driver;
    }


    /**
     * This method initializes the driver for Mobile device
     *
     * @return Webdriver
     */
    private AppiumDriver setLocalAppiumDriver() throws MalformedURLException {
        LOG.info("Set Appium Driver for mobile" + mobileDeviceType);
        URL appiumServerUrl;
        DesiredCapabilities caps = new DesiredCapabilities();
        switch (mobileDeviceType) {
            case ANDROID:
                String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                        + File.separator + "resources" + File.separator + "app" + File.separator +
                        "app-academy.apk";
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, mobileParams.getDeviceName());
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability(MobileCapabilityType.UDID, mobileParams.getUDID());
//                caps.setCapability(MobileCapabilityType.NO_RESET, false);
                caps.setCapability(MobileCapabilityType.FULL_RESET, true);
//                caps.setCapability("avd", "Pixel_3");
                caps.setCapability("avdLaunchTimeout", 180000);
                caps.setCapability("systemPort", mobileParams.getSystemPort());
                caps.setCapability("chromeDriverPort", mobileParams.getChromeDriverPort());
                caps.setCapability(MobileCapabilityType.APP, androidAppUrl);

                appiumServerUrl = new URL(reader.getAppiumServerUrl());

                appiumDriver = new AndroidDriver(appiumServerUrl, caps);
                break;

            case IOS:
                String iosAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                        + File.separator + "resources" + File.separator + "app" + File.separator +
                        "App2.app";
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, mobileParams.getDeviceName());
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                caps.setCapability(MobileCapabilityType.UDID, mobileParams.getUDID());
                caps.setCapability("wdaLocalPort", mobileParams.getWdaLocalPort());
                caps.setCapability("webkitDebugProxyPort", mobileParams.getWebkitDebugProxyPort());
                caps.setCapability(MobileCapabilityType.APP, iosAppUrl);
                appiumServerUrl = new URL(reader.getAppiumServerUrl());
                appiumDriver = new IOSDriver(appiumServerUrl, caps);
                break;
        }
        return appiumDriver;
    }

    /**
     * This method initializes the driver for Mobile device
     *
     * @return Webdriver
     */
    private AppiumDriver setRemoteAppiumDriver() throws MalformedURLException {
        System.out.println("Sauce - BeforeMethod hook");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", reader.getSauceUserName());
        sauceOptions.setCapability("accessKey", reader.getSauceAccessKey());
        sauceOptions.setCapability("parentTunnel", reader.getParentTunnel());
        sauceOptions.setCapability("tunnelIdentifier", reader.getTunnelIdentifier());
        sauceOptions.setCapability("appiumVersion", reader.getSauceAppiumVersion());
        SimpleDateFormat sdf = new SimpleDateFormat("MMddhhmmss");
        String dateAsString = sdf.format(new Date());
        System.out.println(scenario.getName());
        String jobName = "Test"+dateAsString+'_'+mobileDeviceType+'_'+reader.getSauceDeviceName()+'_'+'_'+reader.getSaucePlatformVersion();
        sauceOptions.setCapability("name", jobName);
        MutableCapabilities caps = new MutableCapabilities();
        URL url = new URL(SAUCE_URL);
        switch (mobileDeviceType) {
            case ANDROID:
                caps.setCapability("platformName", "Android");
                caps.setCapability("appium:app", "storage:"+reader.getSauceAppID());
                caps.setCapability("appium:deviceName", reader.getSauceDeviceName());
                caps.setCapability("appium:platformVersion", reader.getSaucePlatformVersion());
                caps.setCapability("appium:automationName", "UiAutomator2");
                caps.setCapability("sauce:options", sauceOptions);
                // Launch remote browser and set it as the current thread
                appiumDriver = new AndroidDriver(url, caps);
                break;

            case IOS:
                caps.setCapability("platformName", "Android");
                caps.setCapability("appium:app", "storage:0bb45fb6-81d2-4c32-a664-5bef029d3f43");
                caps.setCapability("appium:deviceName", "Google Pixel 4a (5G) GoogleAPI Emulator");
                caps.setCapability("appium:platformVersion", "12.0");
                caps.setCapability("appium:automationName", "UiAutomator2");
                break;
        }
        return appiumDriver;
    }

    /**
     * This method quits the driver
     */
    public void closeDriver() {
        if (testPlatform.equals("Mobile")) {
            appiumDriver.quit();
            System.out.println("MOBILE DRIVER QUIT");
        }
        driver.quit();
        System.out.println("DRIVER QUIT");
    }

    /**
     * This method sets the scenario
     */
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}
