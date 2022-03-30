package runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"com.domain.app.stepDefinitions"},
        plugin = {"pretty", "html:target/Iphone13/cucumber", "json:target/Iphone13/cucumber.json"},
        tags = "",
        monochrome = true
)
public class Iphone13RunnerTest extends RunnerBase{
}
