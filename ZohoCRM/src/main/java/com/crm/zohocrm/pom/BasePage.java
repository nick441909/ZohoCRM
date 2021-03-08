package com.crm.zohocrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.crm.zohocrm.generic.WebActionUtil;

public class BasePage {
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	public BasePage(WebDriver driver, WebActionUtil webActionUtil) {
		this.webActionUtil=webActionUtil;
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
