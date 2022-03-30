package com.domain.app.utility;

import com.domain.app.dto.PlexusParams;
import com.domain.app.dto.TestConfig;
import com.domain.app.enums.Browser;
import com.domain.app.enums.EnvironmentType;

/**
 * This class reads the values from the System or from the configuration yaml file
 */
public class ConfigFileReader {
    private final TestConfig testConfiguration;
    private String browserName;

    public ConfigFileReader() {
        testConfiguration = YamlReader.loadFromYamlFile();
    }

    public String getApplicationUrl() {
        return System.getProperty("applicationUrl") == null ? testConfiguration.getApplicationUrl() : System.getProperty("applicationUrl");
    }

    public Browser getBrowser() {
        browserName = System.getProperty("Browser") == null ? testConfiguration.getBrowser() : System.getProperty("Browser");
        System.out.println("BROWSER SELECTED:" + browserName);
        switch (browserName.toLowerCase()) {
            case "chrome":
                return Browser.CHROME;
            case "safari":
                return Browser.SAFARI;
            case "firefox":
                return Browser.FIREFOX;
            case "edge":
                return Browser.EDGE;
            default:
                return Browser.CHROME;
        }
    }

    public String getTestPlatform() {
        return System.getProperty("testPlatform") == null ? testConfiguration.getTestPlatform() : System.getProperty("testPlatform");
    }

    public EnvironmentType getEnvironment() {
        String environment = System.getProperty("environment") == null ? testConfiguration.getEnvironment() : System.getProperty("environment");
        switch (environment.toLowerCase()) {
            case "local":
                return EnvironmentType.LOCAL;
            case "plexus":
                return EnvironmentType.PLEXUS;
            case "saucelabs":
                return EnvironmentType.SAUCELABS;
            default:
                return EnvironmentType.LOCAL;
        }

    }

    public String getDriverPath() {
        switch (getBrowser()) {
            case CHROME:
                return testConfiguration.getDriverPaths().getChromeDriverPath();
            case FIREFOX:
                return testConfiguration.getDriverPaths().getFirefoxDriverPath();
            default:
                return testConfiguration.getDriverPaths().getChromeDriverPath();
        }
    }


    public String getAppiumServerUrl() {
        return testConfiguration.getAppiumServerUrl();
    }

    public PlexusParams getPlexusParams() {
        return testConfiguration.getPlexusParams();
    }

    public String getSauceOS() {
        return System.getProperty("saucePlatformName") == null ? testConfiguration.getSauceLabsParams().getSaucePlatformName() : System.getProperty("saucePlatformName");
    }

    public String getSauceBrowserVersion() {
        return System.getProperty("sauceBrowserVersion") == null ? testConfiguration.getSauceLabsParams().getSauceBrowserVersion() : System.getProperty("sauceBrowserVersion");
    }

    public String getSauceUserName() {
        return System.getProperty("sauceUserName") == null ? testConfiguration.getSauceLabsParams().getSauceUserName() : System.getProperty("sauceUserName");
    }

    public String getSauceAccessKey() {
        return System.getProperty("sauceAccessKey") == null ? testConfiguration.getSauceLabsParams().getSauceAccessKey() : System.getProperty("sauceAccessKey");
    }

    public String getSauceAppiumVersion() {
        return System.getProperty("sauceAppiumVersion") == null ? testConfiguration.getSauceLabsParams().getSauceAppiumVersion() : System.getProperty("sauceAppiumVersion");
    }

    public String getSauceMobilePlatformName() {
        return System.getProperty("sauceMobilePlatformName") == null ? testConfiguration.getSauceLabsParams().getSauceMobilePlatformName() : System.getProperty("sauceMobilePlatformName");
    }

    public String getSauceAppID() {
        return System.getProperty("sauceAppID") == null ? testConfiguration.getSauceLabsParams().getSauceAppID() : System.getProperty("sauceAppID");
    }

    public String getSauceDeviceName() {
        return System.getProperty("sauceDeviceName") == null ? testConfiguration.getSauceLabsParams().getSauceDeviceName() : System.getProperty("sauceDeviceName");
    }

    public String getSaucePlatformVersion() {
        return System.getProperty("saucePlatformVersion") == null ? testConfiguration.getSauceLabsParams().getSaucePlatformVersion() : System.getProperty("saucePlatformVersion");
    }


    public String getParentTunnel() {
        return testConfiguration.getSauceLabsParams().getSauceParentTunnel();
    }

    public String getTunnelIdentifier() {
        return testConfiguration.getSauceLabsParams().getSauceTunnelIdentifier();
    }


}