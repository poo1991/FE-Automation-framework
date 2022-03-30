package com.domain.app.frameworkConfig;

import com.domain.app.mobilePages.AuthenticationPage;
import com.domain.app.mobilePages.Footer;
import com.domain.app.mobilePages.Header;
import com.domain.app.mobilePages.HomePage;

import java.net.MalformedURLException;

public class MobilePageRegistry {
    private ScenarioContext scenarioContext;
    private AuthenticationPage authenticationPage;
    private Footer footer;
    private Header header;
    private HomePage homePage;

    private TestContext testContext;

    public MobilePageRegistry() {
        System.out.println("=====MOBILE PAGE REGISTRY DEFAULT CONST======");
    }

    public MobilePageRegistry(TestContext context) {
        this.testContext = context;
    }

    public ScenarioContext getScenarioContext() {
        return (scenarioContext == null) ? scenarioContext = new ScenarioContext() : scenarioContext;
    }

    public AuthenticationPage getAuthenticationPage() throws MalformedURLException {
        return (authenticationPage == null) ? authenticationPage = new AuthenticationPage(testContext) : authenticationPage;
    }

    public Footer getFooter() throws MalformedURLException {
        return (footer == null) ? footer = new Footer(testContext) : footer;
    }

    public Header getHeader() throws MalformedURLException {
        return (header == null) ? header = new Header(testContext) : header;
    }

    public HomePage getHomePage() throws MalformedURLException {
        return (homePage == null) ? homePage = new HomePage(testContext) : homePage;
    }
}
