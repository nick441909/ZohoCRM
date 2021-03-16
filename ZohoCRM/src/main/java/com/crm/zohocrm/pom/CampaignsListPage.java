package com.crm.zohocrm.pom;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.generic.WebActionUtil;

public class CampaignsListPage extends BasePage {

	HomePage homePage;
	CampaignsListPage campaignsListPage;

	String delCampXpath = "//a[text()='cName']/../..//a[text()='Del']";

	@FindBy(xpath = "//td[contains(text(),'Campaign List')]/ancestor::form")
	private List<WebElement> allElements;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::div//input[@name='Delete2']")
	private WebElement deletecButton;

	@FindBy(xpath = "//input[@value='New Campaign']")
	private WebElement newCampaignButton;

	@FindBy(xpath = "//img[@id='modNameImg']")
	private WebElement campaignLookupButton;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::div//input[@name='edit2']")
	private WebElement editCampaignButton;

	@FindBy(name = "cancel")
	private WebElement cancelButton;

	@FindBy(name = "property(Campaign Name)")
	private WebElement newCampainNameTextField;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::table[@id='secHead1']/preceding-sibling::table//input[@value='Save']")
	private WebElement saveButton;

	@FindBy(id = "modsel")
	private WebElement taskOptionDropDown;

	@FindBy(xpath = "//a[text()='New Task']")
	private WebElement newTaskButton;

	@FindBy(xpath = "//td[@class='title hline']")
	private WebElement titleText;

	@FindBy(xpath = "//td[@class='tableData']/a")
	private List<WebElement> allCampaignsList;

	@FindBy(xpath = "//a[@id='Campaignstab']")
	private WebElement cmpTab;

	@FindBy(xpath = "//input[@name='chk']")
	private WebElement selectCheckBox;

	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteButton;

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
	public BasePage createCampaign(String campaignName) {
		webActionUtil.click(newCampaignButton);
		webActionUtil.enterData(newCampainNameTextField, campaignName);
		webActionUtil.click(saveButton);
		return new CampaignDetailsPage(driver, webActionUtil);
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

	public void isDisplayed() {

		for (WebElement listEle : allElements) {
			if (listEle.getText().contains("Edit")) {

				String op = listEle.getText();
				listEle.isDisplayed();
				System.out.println(op);

			}
		}

	}

	public BasePage enterValueInCampaignField(String sheet) {
		HomePage homePage = new HomePage(driver, webActionUtil);
		webActionUtil.click(newCampaignButton);
		String[][] srr = ExcelLibrary.getMultipleData(sheet);
		for (int i = 0; i <= srr.length - 1; i++) {
//			System.out.println(srr[i][0]);
			for (int j = 0; j <= srr[i].length - 1; j++) {
				if (srr[i][j] != null && !srr[i][j].isEmpty()) {
					webActionUtil.enterData(newCampainNameTextField, srr[i][j]);
					webActionUtil.click(saveButton);
					webActionUtil.click(deletecButton);
					webActionUtil.verifyAlertTextAndAccept("Are you sure?");
					homePage.clickOnNavTabLink("Campaigns");
					webActionUtil.click(newCampaignButton);
				}
			}
		}
		return new CampaignDetailsPage(driver, webActionUtil);

	}

	public BasePage taskToCheckCampaignLookup(String campaignName) {
		webActionUtil.click(newTaskButton);
		webActionUtil.selectByVisibleText(taskOptionDropDown, "Campaign");
		webActionUtil.click(campaignLookupButton);
		Set<String> windows = driver.getWindowHandles();
		String actualtitle = "Zoho CRM - Campaign Name Lookup";
		String parentWindowId = driver.getWindowHandle();

		outerloop: for (;;) {
			for (String wind : windows) {
				driver.switchTo().window(wind);
				if (actualtitle.equalsIgnoreCase(driver.getTitle())) {
					CampaignsListPage campaignsListPage = new CampaignsListPage(driver, webActionUtil);
					Assert.assertEquals(campaignsListPage.verifyCampaign(campaignName), true);
					driver.close();
					driver.switchTo().window(parentWindowId);
					break outerloop;
				}
			}

		}

		return new CampaignLookupPage(driver, webActionUtil);
	}

	public boolean deleteTaskCampaign(String taskCampaignName) {
		HomePage homePage1 = new HomePage(driver, webActionUtil);
		homePage1.clickOnNavTabLink("Campaigns");
		webActionUtil.click(selectCheckBox);
		webActionUtil.click(deleteButton);
		return webActionUtil.verifyAlertTextAndAccept("Are you sure ?");

	}

}
