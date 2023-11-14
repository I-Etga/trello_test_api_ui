package com.trello.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "target/rerun.txt",
        glue = "com/trello/steps"
)

public class FailedTestRunner {
}
