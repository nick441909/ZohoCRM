package com.crm.zohocrm.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.crm.zohocrm.generic.WebActionUtil;

public class LeadsPage extends BasePage {

	public WebDriver driver;
	
//	String delXpath="//a[text()='lname']/../..//a[text()='Del']";

	public LeadsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}

	@FindBy(xpath = "//input[@value='New Lead']")
	private WebElement newLeadLink;

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
		
		List<WebElement> delbu = driver.findElements(By.xpath("//a[text()='Salagar']/../..//a[text()='Del']"));
		
		for (WebElement ele : delbu) {
			
			ele.click();
			break;
		}
		return webActionUtil.verifyAlertTextAndAccept("Are you sure ?");
	}

	

	public boolean verifyLeadTitle() {
		return driver.getTitle().contains("Zoho CRM - Lead Details ");

	}

}
