package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"@target/rerun.txt"},
        glue = {"com.domain.app.stepDefinitions"},
        plugin = {"pretty", "json:target/cucumberRerun.json"},
        monochrome = true
)
public class TestRunnerRerun extends AbstractTestNGCucumberTests {

//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }

}
