package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "src/test/java/features",
				
		plugin = {
				"pretty",
				"html:target/test-output", 
				"json:target/cucumber.json", 
				"junit:target/cucumber.xml"
			},
		
		monochrome = true,
		
//		dryRun =true,
		
		tags = "@AddPlaceApi",

		glue = { "stepDefinitions" }
		
)

public class TestRunner {
	
}
