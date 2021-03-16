package com.crm.zohocrm.pom;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.crm.zohocrm.generic.WebActionUtil;

public class CampaignDetailsPage extends BasePage {

	CampaignDetailsPage campaignDetailsPage;

	public CampaignDetailsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::div//input[@name='Delete2']")
	private WebElement deletecButton;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::div//input[@name='edit2']")
	private WebElement editCampaignButton;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::div//input[@value='Clone']")
	private WebElement cloneButton;

	@FindBy(name = "property(Campaign Name)")
	private WebElement newCampainNameTextField;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::table[@id='secHead1']/preceding-sibling::table//input[@value='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//td[contains(text(),'Campaign Information')]/ancestor::div//input[@name='Print2']")
	private WebElement printPreviewButton;

	@FindBy(xpath = "//table[@id='secContent1']//td")
	private List<WebElement> checkCampaignInEdit;

	@FindBy(xpath = "//input[@value='Print Page']")
	private WebElement printPageButton;

	@FindBy(xpath = "//iframe[@src='/crm/jsp/PrintButton.jsp?skintype=contemporary']")
	private WebElement frame1;

	@FindBy(xpath = "//input[@name='Close']")
	private WebElement closeButtonInprint;
	
	@FindBy(xpath = "//table[@id='secContent1']//td")
	private List<WebElement> printPageCampaignVerify;

	public BasePage editCampaigndetails(String edit)

	{
		webActionUtil.click(editCampaignButton);
		webActionUtil.enterData(newCampainNameTextField, edit);
		webActionUtil.click(saveButton);
		return new CampaignDetailsPage(driver, webActionUtil);
	}

	public boolean verifyclickCloneCampaign(String campaignName) {
		webActionUtil.click(cloneButton);
		webActionUtil.enterData(newCampainNameTextField, "Nokia1");
		webActionUtil.click(saveButton);
		homePage = new HomePage(driver, webActionUtil);
		homePage.clickOnNavTabLink("Campaigns");
		return true;
	}

	public boolean checkCampaignInEdit(String cName) {
		for (WebElement leadname : checkCampaignInEdit) {
			if (leadname.getText().contains(cName)) {
				return true;

			}
		}
		return false;
	}

	public boolean deleteCampaignInDetails(String leadName) {
		webActionUtil.click(deletecButton);
		return webActionUtil.verifyAlertTextAndAccept("Are you sure?");
	}

	public boolean printPageCampaignVerify(String campaignName) {
		for (WebElement ele : printPageCampaignVerify) {
			if (ele.getText().contains(campaignName)) {
				return true;
			}

		}
		return false;
	}

	public boolean printPreviewCampaignVerify(String campaignName) {
		String parentWindow = driver.getWindowHandle();
		webActionUtil.click(printPreviewButton);
		Set<String> childWindows = driver.getWindowHandles();
		String actualtitle = "Zoho CRM - Campaign Details";
		childWindows.remove(parentWindow);
		for (String win : childWindows) {
			driver.switchTo().window(win);
			if (actualtitle.contains(driver.getTitle())) {
				campaignDetailsPage = new CampaignDetailsPage(driver, webActionUtil);
				Assert.assertEquals(campaignDetailsPage.printPageCampaignVerify(campaignName),
						true,"Campaign name is not in the list");
				driver.switchTo().frame(frame1);
				webActionUtil.click(closeButtonInprint);
				driver.switchTo().window(parentWindow);
				return true;
			}

		}
		return false;

	}

}
