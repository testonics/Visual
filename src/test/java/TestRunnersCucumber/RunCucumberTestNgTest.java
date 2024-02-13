//This test class executes cucumber scenarios in parallel
package TestRunnersCucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue={"StepDef"},
        plugin = {"pretty"},
        monochrome = true,
        tags = {"@Appium"}
)

public class RunCucumberTestNgTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
