package runners;

import com.domain.app.dto.MobileParams;
import com.domain.app.frameworkConfig.AppiumServerManager;
import com.domain.app.helpers.ReportHelper;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

//@CucumberOptions(features = {"src/test/resources/features"},
//        glue = {"com.domain.app.stepDefinitions"},
//        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
//        tags = "@Mobile_Home_TC01",
//        monochrome = true
//)
public class RunnerBase {

        private TestNGCucumberRunner testNGCucumberRunner;

    @Parameters({"mobilePlatformName", "udid", "deviceName", "systemPort",
            "chromeDriverPort", "wdaLocalPort", "webkitDebugProxyPort"})
    @BeforeClass(alwaysRun = true)
    public void setupClass(String mobilePlatformName, String udid, String deviceName, @Optional("Android") String systemPort,
                           @Optional("Android") String chromeDriverPort,
                           @Optional("iOS") String wdaLocalPort,
                           @Optional("iOS") String webkitDebugProxyPort) {
        MobileParams mobileParams = new MobileParams();
        mobileParams.setPlatformName(mobilePlatformName);
        mobileParams.setUDID(udid);
        mobileParams.setDeviceName(deviceName);

        switch (mobilePlatformName) {
            case "Android":
                mobileParams.setSystemPort(systemPort);
                mobileParams.setChromeDriverPort(chromeDriverPort);
                break;
            case "iOS":
                mobileParams.setWdaLocalPort(wdaLocalPort);
                mobileParams.setWebkitDebugProxyPort(webkitDebugProxyPort);
                break;
        }
        AppiumServerManager.startAppiumServer();
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runScenario(pickle.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        AppiumServerManager.stopAppiumServer();
        testNGCucumberRunner.finish();
    }

    @AfterSuite(alwaysRun = true)
    public void generateReports() {
        ReportHelper.generateCucumberReport();
    }

}
