package com.domain.app.dto;

public class WebDriverPathConfig {
    private String chromeDriverPath;
    private String firefoxDriverPath;

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public void setChromeDriverPath(String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    public String getFirefoxDriverPath() {
        return firefoxDriverPath;
    }

    public void setFirefoxDriverPath(String firefoxDriverPath) {
        this.firefoxDriverPath = firefoxDriverPath;
    }
}
