package com.project.pages;

import org.openqa.selenium.By;

import com.project.utilities.DriverActions;

public class LoginPage extends DriverActions {
	public String signinButton= "com.amazon.mShop.android.shopping:id/sign_in_button";
	public String button= "android.widget.Button";
	
	//email fileds
	String emailField="//android.widget.EditText[@resource-id='ap_email_login']";

	//password fields
	String passwordField= "//android.widget.EditText[@resource-id='ap_password']";

	// method to login in the app
	public void login(String email, String password) {
		clickElement(By.id(signinButton));
		enterText(locateElement(By.xpath(emailField)), email);
		clickElement(By.className(button));
		waitForSpecificTime();
		enterText(locateElement(By.xpath(passwordField)), password);
		clickElement(By.className(button));
	}

}
