package com.project.test;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.project.pages.HomePage;
import com.project.pages.ItemPage;
import com.project.pages.LoginPage;
import com.project.utilities.DriverActions;
import com.project.utilities.CommonUtilities;
import com.project.utilities.Screenshot;

// this is to take screenshot when test gets pass or fail
@Listeners({Screenshot.class })

public class TestAmazonApp extends DriverActions {

	LoginPage loginPage= new LoginPage();
	HomePage home= new HomePage();
	ItemPage item= new ItemPage();
	CommonUtilities data= new CommonUtilities();
	String title="";
	String price="";
	
	// test to verify user is able to login
	@Test
	public void login(){
		loginPage.login(data.readData("email"), data.readData("password"));
		Assert.assertEquals(isPresent(locateElement(By.id(home.homeLogo))),true,"user not got logged in successfully");
	}
	
	// test to verify search is performed successfully
	@Test(dependsOnMethods = {"login"})
	public void searchItem() {
		home.searchItem(data.readData("searchItem"));
		// generating random number from total search result display using random number generator
		int index=data.randomeNum(getElementsCount(By.id(home.selectItem)));
		// storing title and price of searched item from search screen to verify later
		title= home.getTitle(index);
		price=home.getPrice(index);
		elementByIndex(By.id(home.selectItem), index).click();
		waitForElement(By.xpath(item.review));
		//Scrolling till we see addtocart button
		scrollPage();
		boolean actual = elementDisplay(By.xpath(item.addToCar));
		Assert.assertEquals(actual, true,"Add to cart button not display");
	}

	// test to verify the item on checkout screen is same as what selected from search result
	@Test(dependsOnMethods = {"searchItem"})
	public void purchaseItem() {
		clickElement(By.xpath(item.addToCar));
		waitForText(By.id(item.cartButton), "1");
		clickElement(By.id(item.cartButton));
		// on checkout screen validating the title and price of item is same as what we selected on search result page by text.
		Assert.assertTrue(isPresent(locateElement(By.xpath(item.priceLocator(price)))));
		Assert.assertTrue(isPresent(locateElement(By.xpath(item.titlelocator(title)))));	
	}

}
