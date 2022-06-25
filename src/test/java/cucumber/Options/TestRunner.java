package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "src/test/java/features",
		
		monochrome = true,
		
//		dryRun =true,
		
		tags = "@AddPlaceApi",

		glue = { "stepDefinitions" },
		
		publish = true

)

public class TestRunner {
	
}
