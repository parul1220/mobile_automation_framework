package com.project.pages;

import org.openqa.selenium.By;

import com.project.utilities.DriverActions;

public class ItemPage extends DriverActions {
	public String addToCar = "//android.widget.Button [@resource-id='add-to-cart-button']";
	public String review = "//android.view.View [@resource-id='averageCustomerReviews_feature_div']";
	public String cartButton = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_count";

	// checkout screen locator for Price of the item added
	public String priceLocator(String text)
	{
		return "//android.view.View[contains(@text,'"+text+"')]";
	}
	
	// checkout screen locator for title of the item added
	public String titlelocator(String text)
	{
		return "//android.widget.TextView[contains(@text,'"+text+"')]";
	}

}
