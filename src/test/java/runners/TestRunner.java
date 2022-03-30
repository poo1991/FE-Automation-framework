package runners;

import com.domain.app.helpers.ReportHelper;
import com.domain.app.utility.ConfigFileReader;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"com.domain.app.stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json","rerun:target/rerun.txt"},
        tags = "",
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
       ConfigFileReader reader = new ConfigFileReader();

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite(alwaysRun = true)
    public void generateReports() {
        ReportHelper.generateCucumberReport();
    }

}
