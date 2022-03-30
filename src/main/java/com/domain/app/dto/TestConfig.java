package com.domain.app.dto;

public class TestConfig {
    private String testPlatform;
    private String environment;
    private String applicationUrl;
    private String browser;
    private WebDriverPathConfig driverPaths;
    private String mobileDeviceType;
    private String appiumServerUrl;
    private PlexusParams plexusParams;
    private SauceLabsParams sauceLabsParams;

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getTestPlatform() {
        return testPlatform;
    }

    public void setTestPlatform(String testPlatform) {
        this.testPlatform = testPlatform;
    }

    public WebDriverPathConfig getDriverPaths() {
        return driverPaths;
    }

    public void setDriverPaths(WebDriverPathConfig driverPaths) {
        this.driverPaths = driverPaths;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getMobileDeviceType() {
        return mobileDeviceType;
    }

    public void setMobileDeviceType(String mobileDeviceType) {
        this.mobileDeviceType = mobileDeviceType;
    }

    public String getAppiumServerUrl() {
        return appiumServerUrl;
    }

    public void setAppiumServerURL(String appiumUrl) {
        this.appiumServerUrl = appiumUrl;
    }

    public PlexusParams getPlexusParams() {
        return plexusParams;
    }

    public void setPlexusParams(PlexusParams plexusParams) {
        this.plexusParams = plexusParams;
    }

    public SauceLabsParams getSauceLabsParams() {
        return sauceLabsParams;
    }

    public void setSauceLabsParams(SauceLabsParams sauceLabsParams) {
        this.sauceLabsParams = sauceLabsParams;
    }
}
