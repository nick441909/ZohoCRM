package com.crm.zohocrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.zohocrm.generic.WebActionUtil;

public class LoginPage extends BasePage {
	
	@FindBy(id="userName")
	private WebElement userNameTextField;
	
	@FindBy(id="passWord")
	private WebElement passwordTextField;
	
	@FindBy(xpath="//input[@title='Sign In']") 
	private WebElement signInButton;
	
	@FindBy(name="j_remember") 
	private WebElement rememberCheckBox;
	
	public WebElement getUserNameTextField() {
		return userNameTextField;
	}
	
	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public WebElement getRememberCheckBox() {
		return rememberCheckBox;
	}

	public LoginPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	//Action Methods
	public HomePage signIn(String user, String pass) {
		webActionUtil.enterData(userNameTextField, user);
		webActionUtil.enterData(passwordTextField, pass);
		webActionUtil.click(signInButton);
		return new HomePage(driver,webActionUtil);
	}
}
