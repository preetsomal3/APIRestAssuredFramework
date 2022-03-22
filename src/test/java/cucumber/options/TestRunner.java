package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		plugin="json:target/jsonReports/cucumber-report.json",
		features="src/test/java/features",
		glue="stepdefinations",
		dryRun=false
//		tags="@DeletePlace"
		
		
		)
public class TestRunner {

}
