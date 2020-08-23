package com.project.utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.pages.BaseDriver;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;;

public class DriverActions extends BaseDriver{
	WebElement ele=null;

	public WebElement locateElement(By by)
	{
		waitForElement(by);
		ele=driver.findElement(by);
		return ele;
	}

	public void clickElement(By by)
	{
		waitForElement(by);
		driver.findElement(by).click();					 
	}

	public void enterText(WebElement ele,String text)
	{
		ele.sendKeys(text);
	}

	public void pressEnter()
	{
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));

	}

	public boolean isPresent(WebElement ele)
	{
		try{
			return ele.isDisplayed();
		}
		catch(Exception e){
			return false;
		}
	}
    
	// customize wait for given locator
	public void waitForElement(By by)
	{
		try {
			WebDriverWait wait=new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));	
		} catch(Exception e) {
			System.out.println("element is not present in 20 sec ");
			e.printStackTrace();
		}				
	}

	//waituntill expected text get updated for particular element

	public void waitForText(By by, String text)
	{
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));			
	}
	
	// method to scroll untill the expected element is visible
	public void scrollPage() 
	{
		System.out.println("i'm scrolling down the page");
		TouchAction touchAction = new TouchAction(driver);
		Dimension dim = driver.manage().window().getSize();
		int x =dim.getWidth()/2;
		int startY= (int)(dim.getHeight() * 0.9);
		int endY = (int)(dim.getHeight() * 0.2);
		touchAction.press(PointOption.point(x, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(60000)))
		.moveTo(PointOption.point(x, endY)).release().perform();
	}

	public String getElementText(By by)
	{
		waitForElement(by);
		return driver.findElement(by).getText();

	}

	public Boolean elementDisplay(By by)
	{
		try {
			return driver.findElement(by).isDisplayed();
		} catch(Exception e) {
			return false;
		}		
	}

	public void waitForSpecificTime()
	{
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getElementsCount(By by) 
	{
		waitForElement(by);
		return driver.findElements(by).size();
	}


	public WebElement elementByIndex(By by, int index)
	{
		waitForElement(by);
		List<WebElement> li = driver.findElements(by);
		return li.get(index)	;

	}

	public String getElementAttribute(By by, int index)
	{
		return elementByIndex(by, index).getAttribute("content-desc");
	}

	public String getElementTextByIndex(By by, int index)
	{
		return elementByIndex(by, index).getText();
	}
}
