package com.project.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Screenshot extends DriverActions implements ITestListener {
	// This method will execute before starting of Test suite.
	public void onStart(ITestContext tr) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");

	}

	// This method will execute, Once the Test suite is finished.
	public void onFinish(ITestContext tr) {

	}

	// This method will execute only when the test is pass.
	public void onTestSuccess(ITestResult tr) {
		captureScreenShot(tr, "pass");
	}

	// This method will execute only on the event of fail test.
	public void onTestFailure(ITestResult tr) {
		captureScreenShot(tr, "fail");
	}

	// This method will execute before the main test start (@Test)
	public void onTestStart(ITestResult tr) {

	}

	// This method will execute only if any of the main test(@Test) get skipped
	public void onTestSkipped(ITestResult tr) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
	}

	// Function to capture screenshot.
	public void captureScreenShot(ITestResult result, String status) {
		String destDir = "";
		String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "."
				+ result.getMethod().getMethodName();
		// To capture screenshot.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// If status = fail then set folder name "screenshots/Failures"
		if (status.equalsIgnoreCase("fail")) {
			destDir = "./screenshots/failure";
		}
		// If status = pass then set folder name "screenshots/Success"
		else if (status.equalsIgnoreCase("pass")) {
			destDir = "./screenshots/success";
		}

		// To create folder to store screenshots
		new File(destDir).mkdir();

		//String destFile = passfailMethod.png";
		String destFile = passfailMethod+".png";
		File imageFile = new File(destDir + "/" + destFile);

		try {
			// Store file at destination folder location
			FileUtils.copyFile(scrFile, imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
