package com.crm.zohocrm.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.crm.zohocrm.generic.Utility;
import com.crm.zohocrm.generic.WebActionUtil;

public class LeadsListPage extends BasePage {

	public WebDriver driver;
	public LeadsListPage leadsListPage;

	String delLeadXpath = "//a[text()='lname']/../..//a[text()='Del']";

	public LeadsListPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}

	@FindBy(xpath = "//input[@value='New Lead']")
	private WebElement newLeadLink;
	

	@FindBy(xpath = "//td[@class='title hline']")
	private WebElement titleLeadText;
	
	@FindBy(xpath = "//td[@class='title hline']")
	private WebElement titleCreateLeadText;
	
	@FindBy(xpath="//input[@name='chk']")
	private WebElement selectCheckBox;
	
	@FindBy(xpath="//input[@value='Delete']")
	private WebElement deleteButton;

	@FindBy(xpath = "//select[@name='property(saltName)']")
	private WebElement FnDropDown;

	@FindBy(name = "property(Company)")
	private WebElement companyNameTextField;

	@FindBy(name = "property(Last Name)")
	private WebElement lastNameTextField;

	@FindBy(xpath = "//td[@class='tableData']/a")
	private List<WebElement> allLeadslist;

	@FindBy(xpath = "//td[contains(text(),'Lead Information')]/ancestor::td//input[@value='Save']")
	private WebElement leadSaveButton;

	public BasePage creatingLead(String companyName, String lastName) {

		webActionUtil.click(newLeadLink);
		leadsListPage=new LeadsListPage(driver, webActionUtil);
		webActionUtil.enterData(companyNameTextField, companyName);
		webActionUtil.enterData(lastNameTextField, lastName);
		webActionUtil.click(leadSaveButton);
		return new LeadDetailsPage(driver, webActionUtil);

	}

	public boolean verifyLeads(String LeadName) {
		for (WebElement ele : allLeadslist) {
			if (ele.getText().equalsIgnoreCase(LeadName)) {
				return true;
			}
		}
		return false;
	}
	

	public boolean delLeadname(String LeadName) {
		webActionUtil.click(selectCheckBox);
		webActionUtil.click(deleteButton);
		return webActionUtil.verifyAlertTextAndAccept("Are you sure ?");

	}

	public boolean verifyAllLeadTitle() {
		return titleLeadText.getText().contains("All Open Leads");

	}
	
	public boolean verifyLeadCreateTitle() {
		return titleCreateLeadText.getText().contains("Create Lead");

	}
	
	

}
