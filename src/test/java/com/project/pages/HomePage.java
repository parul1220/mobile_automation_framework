package com.project.pages;

import org.openqa.selenium.By;

import com.project.utilities.DriverActions;

public class HomePage extends DriverActions {
	
	public String homeLogo = "com.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo";
	public String searchArea ="com.amazon.mShop.android.shopping:id/rs_search_src_text";
	public String selectItem ="com.amazon.mShop.android.shopping:id/list_product_linear_layout";
	public String itemTitle ="com.amazon.mShop.android.shopping:id/item_title";
	public String itemPrice = "com.amazon.mShop.android.shopping:id/rs_results_price";


	public void searchItem(String searchItem) {
		clickElement(By.id(searchArea));
		enterText(locateElement(By.id(searchArea)), searchItem);
		pressEnter();
	}
	
	// give title of the element which we clicked on search result page
	public String getTitle(int index) {
		String text=getElementTextByIndex(By.id(itemTitle), index);
		if(text.length()>64)
		{
			text=text.substring(0, 64);
		}
		return text;
	}
	
	// give price of the element which we clicked on search result page
	public String getPrice(int index) {
		String price=getElementAttribute(By.id(itemPrice), index).split(" ")[0];
		return price.substring(1,price.length());	

	}

}
