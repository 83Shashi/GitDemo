package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= "C:src//test//java//cucumber",
					glue="SeleniumFramework.stepDefinition",
					monochrome=true,
					tags= "@Regression",
					plugin= {"html:target/cucumber.html"})


public class testNgTestRunner extends AbstractTestNGCucumberTests 
{
	
}
