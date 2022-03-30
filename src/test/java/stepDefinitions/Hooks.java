package stepDefinitions;

import com.domain.app.frameworkConfig.MobilePageRegistry;
import com.domain.app.frameworkConfig.PageRegistry;
import com.domain.app.frameworkConfig.TestContext;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks{

    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before("@Web")
    public void BeforeSteps(Scenario scenario) {
        System.out.println("IN WEB BEFORE HOOK");
        testContext.pageRegistry = new PageRegistry(testContext);
        testContext.getDriverRegistry().setScenario(scenario);
    }

    @Before("@Mobile")
    public void BeforeScenarioMobile(Scenario scenario) {
        System.out.println(" IN MOBILE BEFORE HOOK");
        testContext.mobilePageRegistry = new MobilePageRegistry(testContext);
        testContext.getDriverRegistry().setScenario(scenario);
    }

    @After("@Web")
    public void teardown(Scenario scenario) throws MalformedURLException {
        System.out.println("IN TEAR DOWN");
        if (scenario.isFailed()) {
            takeScreenshot(scenario);
        }
        testContext.getDriverRegistry().closeDriver();
    }

    public void takeScreenshot(Scenario scenario) throws MalformedURLException {
            WebDriver driver = testContext.getDriverRegistry().getDriver();
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/jpeg", "");
    }

    @After("@Mobile")
    public void teardownMobile(Scenario scenario) throws MalformedURLException {
        System.out.println("IN TEAR DOWN");
        if (scenario.isFailed()) {
            takeScreenshot(scenario);
        }
        testContext.getDriverRegistry().closeDriver();
    }

    @AfterStep("@ScreenShot")
    public void attachScreenshot(Scenario scenario) throws MalformedURLException {
        takeScreenshot(scenario);
    }

}
