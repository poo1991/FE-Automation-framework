package com.domain.app.frameworkConfig;

import com.domain.app.webPages.AuthorizationPage;
import com.domain.app.webPages.HeaderPage;
import com.domain.app.webPages.HomePage;
import com.domain.app.webPages.LibraryPage;

import java.net.MalformedURLException;

/**
 * The purpose of this class is to call a Page Method only once and user it across the step definition files.
 * This will avoid instantiation of Pages in case of Multiple Step Definition files
 */
public class PageRegistry {
    private ScenarioContext scenarioContext;
    private AuthorizationPage authorizationPage;
    private HomePage homePage;
    private HeaderPage headerPage;
    private LibraryPage libraryPage;

    private TestContext testContext;

    public PageRegistry() {
        System.out.println("====PAGE REGISTRY DEFAULT CONST=======");
    }

    public PageRegistry(TestContext context) {
        this.testContext = context;
    }

    public ScenarioContext getScenarioContext() {
        return (scenarioContext == null) ? scenarioContext = new ScenarioContext() : scenarioContext;
    }

    public AuthorizationPage getAuthorizationPage() throws MalformedURLException {
        return (authorizationPage == null) ? authorizationPage = new AuthorizationPage(testContext) : authorizationPage;
    }

    public HomePage getHomePage() throws MalformedURLException {
        return (homePage == null) ? homePage = new HomePage(testContext) : homePage;
    }

    public HeaderPage getHeaderPage() throws MalformedURLException {
        return (headerPage == null) ? headerPage = new HeaderPage(testContext) : headerPage;
    }

    public LibraryPage geLibraryPage() throws MalformedURLException {
        return (libraryPage == null) ? libraryPage = new LibraryPage(testContext) : libraryPage;
    }
}
