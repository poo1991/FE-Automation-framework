package com.domain.app.frameworkConfig;

import com.domain.app.utility.ConfigFileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class Base {
    public WebDriver driver;
    public WebDriverWait wait;
    protected TestContext testContext;
    public final ConfigFileReader reader = new ConfigFileReader();
    public static final Logger LOG = LogManager.getLogger();
    public ScenarioContext scenarioContext;

    public Base(TestContext context) throws MalformedURLException {
        this.testContext = context;
        this.scenarioContext = testContext.getScenarioContext();
        this.driver = testContext.getDriverRegistry().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}
