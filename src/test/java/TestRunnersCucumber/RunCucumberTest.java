package TestRunnersCucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue={"StepDef"},
        plugin = {"pretty"},
        monochrome = true,
        tags = {"@Playwright,@Selenium"}
)
public class RunCucumberTest {
}
