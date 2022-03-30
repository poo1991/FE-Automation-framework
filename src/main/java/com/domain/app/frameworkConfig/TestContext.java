package com.domain.app.frameworkConfig;

public class TestContext {
    private DriverManager driverManager;
    public PageRegistry pageRegistry;
    public MobilePageRegistry mobilePageRegistry;
    public ScenarioContext scenarioContext;

    public TestContext() {
        driverManager = new DriverManager();
        pageRegistry = new PageRegistry();
        scenarioContext = new ScenarioContext();
        mobilePageRegistry = new MobilePageRegistry();
    }

    public DriverManager getDriverRegistry() {
        return driverManager;
    }

    public PageRegistry getPageRegistry() {
        return pageRegistry;
    }

    public MobilePageRegistry getMobilePageRegistry() {
        return mobilePageRegistry;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
