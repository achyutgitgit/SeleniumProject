package cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@SuppressWarnings({"removal"}) // Hides the striking lines in your IDE
@CucumberOptions(
    features = "src/test/java/cucumber/SubmitOrder.feature", 
    glue = "cucumber",                  
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestngTestRunner extends AbstractTestNGCucumberTests {
    // This will work with your testng.xml, but it is slated for permanent removal later
}

