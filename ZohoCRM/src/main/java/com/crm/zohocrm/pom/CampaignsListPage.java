package com.crm.zohocrm.pom;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.generic.IAutoConstants;
import com.crm.zohocrm.generic.WebActionUtil;

public class CampaignsListPage extends BasePage {
	
	HomePage homePage;

	String delCampXpath = "//a[text()='cName']/../..//a[text()='Del']";

	@FindBy(xpath = "//td[contains(text(),'Campaign List')]/ancestor::form")
	private List<WebElement> allElements;

	@FindBy(xpath = "//input[@value='New Campaign']")
	private WebElement newCampaignButton;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::div//input[@name='edit2']")
	private WebElement editCampaignButton;

	@FindBy(name = "property(Campaign Name)")
	private WebElement newCampainNameTextField;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::table[@id='secHead1']/preceding-sibling::table//input[@value='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//td[@class='title hline']")
	private WebElement titleText;

	@FindBy(xpath = "//td[@class='tableData']/a")
	private List<WebElement> allCampaignsList;

	public WebElement getNewCampaignButton() {
		return newCampaignButton;
	}

	public WebElement getNewCampainNameTextField() {
		return newCampainNameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getTitleText() {
		return titleText;
	}

	public List<WebElement> getAllCampaignsList() {
		return allCampaignsList;
	}

	public CampaignsListPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}

	/**
	 * creates a campaign with mandatory field Campaign Name
	 */
	public void createCampaign(String campaignName) {
		webActionUtil.click(newCampaignButton);
		webActionUtil.enterData(newCampainNameTextField, campaignName);
		webActionUtil.click(saveButton);
	}

	public boolean verifyCampaign(String campaignName) {
		for (WebElement ele : allCampaignsList) {
			if (ele.getText().equalsIgnoreCase(campaignName)) {
				return true;
			}
		}
		return false;
	}

	public boolean deleteCampaign(String campaignName) {
		delCampXpath = delCampXpath.replace("cName", campaignName);
		webActionUtil.click(driver.findElement(By.xpath(delCampXpath)));
		return webActionUtil.verifyAlertTextAndAccept("Are you sure ?");
	}

	public boolean verifyAllCampaignsTitle() {
		return titleText.getText().contains("All Campaigns");
	}

	public boolean verifyCreateCampaignTitle() {
		return titleText.getText().contains("Create Campaign");
	}

	public boolean verifyCampaignDetailsTitle() {
		return titleText.getText().contains("Campaign Details");
	}

	public BasePage editCampaigndetails(String e)

	{
		webActionUtil.click(editCampaignButton);
		webActionUtil.enterData(newCampainNameTextField, e);
		webActionUtil.click(saveButton);
		return new CampaignEditPage(driver, webActionUtil);
	}

	public void isDisplayed() {

		for (WebElement listEle : allElements) {
			if (listEle.getText().contains("Edit")) {

				String op = listEle.getText();
				listEle.isDisplayed();
				System.out.println(op);

			}
		}

	}
	
	public void enterValueInCampaignField(String sheet)
	{
		String[][] srr = ExcelLibrary.getMultipleData(sheet);
		for(int i=1;i<=srr.length-1;i++) {
			webActionUtil.enterData(newCampainNameTextField, srr[i][0]);
			webActionUtil.click(saveButton);
			
			Web
			webActionUtil.click(newCampaignButton);
		}
	
		
		
	}
	

}
