package com.crm.zohocrm.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.crm.zohocrm.generic.WebActionUtil;

public class LeadDetailsPage extends BasePage {

	LeadDetailsPage leadDetailsPage;

	@FindBy(xpath = "//td[contains(text(),'Lead Information')]/ancestor::div//input[@name='Delete2']")
	private WebElement deleteLeadButton;

	@FindBy(xpath = "//td[contains(text(),'Lead Information')]/ancestor::div//input[@name='edit2']")
	private WebElement editLeadButton;

	@FindBy(xpath = "//td[contains(text(),'Lead Information')]/ancestor::div//input[@value='Clone']")
	private WebElement cloneLeadButton;

	@FindBy(name = "property(Campaign Name)")
	private WebElement newCampainNameTextField;

	@FindBy(name = "property(Company)")
	private WebElement companyNameTextField;

	@FindBy(name = "property(Last Name)")
	private WebElement lastNameTextField;

	@FindBy(xpath = "//table[@id='secContent1']//td")
	private List<WebElement> checkInDetails;

	@FindBy(xpath = "//td[contains(text(),'Lead Information')]/ancestor::table[@id='secHead1']/preceding-sibling::table//input[@value='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//td[contains(text(),'Lead Information')]/ancestor::div//input[@name='Print2']")
	private WebElement printPreviewButton;

	@FindBy(xpath = "//input[@value='Print Page']")
	private WebElement printPageButton;

	public LeadDetailsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
		// TODO Auto-generated constructor stub
	}

	public void editLeaddetails(String companyName)

	{
		webActionUtil.click(editLeadButton);
		webActionUtil.enterData(companyNameTextField, companyName);
		webActionUtil.click(saveButton);

	}

	public boolean checkLeadInDetailsPage(String leadName) {
		for (WebElement leadname : checkInDetails) {
			if (leadname.getText().contains(leadName)) {
				return true;

			}
		}
		return false;
	}

	public boolean cloneLead(String leadName) {
		webActionUtil.click(cloneLeadButton);
		webActionUtil.enterData(lastNameTextField, leadName);
		webActionUtil.click(saveButton);
		return true;
	}
	
	public boolean leadDeleteInDetails(String leadName) {
		webActionUtil.click(deleteLeadButton);
		return webActionUtil.verifyAlertTextAndAccept("Are you sure?");
	}
	
	
	
	
}
