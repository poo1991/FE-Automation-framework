package runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"com.domain.app.stepDefinitions"},
        plugin = {"pretty", "html:target/Iphone12/cucumber", "json:target/Iphone12/cucumber.json"},
        tags = "",
        monochrome = true
)
public class Iphone12RunnerTest extends RunnerBase {
}
