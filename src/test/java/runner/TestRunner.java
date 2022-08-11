package runner;


import general.BaseTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features", glue = "tests", plugin = {"pretty", "html:target/cucumber-html-report.html"})

public class TestRunner extends BaseTests {
}
