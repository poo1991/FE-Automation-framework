package runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"com.domain.app.stepDefinitions"},
        plugin = {"pretty", "html:target/Pixel/cucumber", "json:target/Pixel/cucumber.json"},
        tags = "",
        monochrome = true
)
public class AndroidPixelRunnerTest extends RunnerBase {
}
