package stepDef;

import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.TestContextSetup;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


public class Hooks {
    TestContextSetup tcs;
    WebDriverWait wait;

    public Hooks(TestContextSetup tcs) {
        this.tcs = tcs;
        wait = new WebDriverWait(tcs.base.driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() throws IOException {
        tcs.base.initializeDriver().quit();
    }

    @AfterStep
    public void takeScreenshot(Scenario scenario) throws IOException {
        WebDriver driver = tcs.base.driver;
        if (scenario.isFailed()) {
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");
        }
    }



}
