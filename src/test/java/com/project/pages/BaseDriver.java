package com.project.pages;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import com.project.utilities.CommonUtilities;

public class BaseDriver {
	public static AndroidDriver driver;
	public AppiumDriverLocalService service;
	public DesiredCapabilities cap=new DesiredCapabilities();
	CommonUtilities read= new CommonUtilities();

	// this will launch appium server before the test starts.
	@BeforeTest
	public void startAppiumServer() throws MalformedURLException, InterruptedException
	{
		try {
			service=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(new File(read.readfromFile("node_path")))
					.withAppiumJS(new File(read.readfromFile("mainJsPath")))
					.usingPort(4723)
					.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
					.withLogFile(new File("ServerLogs/server.log")));
			service.start();
			launchApp();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void stopAppiumServer()
	{
		if (driver != null) {
			driver.quit();
		}
		if (service != null) {
			service.stop();
		}

	}
	
	// this method will launch the app as per the given capabilities 
	public void launchApp() throws MalformedURLException, InterruptedException {
		try {
			cap.setCapability("device", "Android");
			cap.setCapability("device", read.readfromFile("platformName"));
			cap.setCapability("deviceName", read.readfromFile("deviceName"));
			cap.setCapability("noReset",true);
			cap.setCapability(MobileCapabilityType.APP, read.readfromFile("apklocation"));
			cap.setCapability("appPackage",read.readfromFile("appPackage"));
			cap.setCapability("appActivity",read.readfromFile("appActivity"));
			driver =  new AndroidDriver(new URL(read.readfromFile("baseURL")), cap);
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

}
