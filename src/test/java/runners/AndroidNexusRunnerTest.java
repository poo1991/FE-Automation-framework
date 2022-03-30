package runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"com.domain.app.stepDefinitions"},
        plugin = {"pretty", "html:target/Nexus/cucumber", "json:target/Nexus/cucumber.json"},
        tags = "",
        monochrome = true
)
public class AndroidNexusRunnerTest extends RunnerBase {
}
