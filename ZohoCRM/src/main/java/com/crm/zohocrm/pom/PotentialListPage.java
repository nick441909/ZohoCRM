package com.crm.zohocrm.pom;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.crm.zohocrm.generic.WebActionUtil;

public class PotentialListPage extends BasePage {

	PotentialListPage potentialListPage;
	CampaignsListPage campaignsListPage;

	public PotentialListPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}

	@FindBy(xpath = "//td[@class='title hline']")
	private WebElement titleAllPotentialText;

	@FindBy(xpath = "//td[@class='title hline']")
	private WebElement titleCreatePotentialText;

	@FindBy(xpath = "//input[@value='New Potential']")
	private WebElement newPotentialButton;

	@FindBy(xpath = "//input[@name='property(Potential Name)']")
	private WebElement potentialNameTextField;

	@FindBy(xpath = "//input[@name='property(Account Name)']")
	private WebElement accountNameTextField;

	@FindBy(xpath = "//input[@name='property(Closing Date)']")
	private WebElement closingDatefield;

	@FindBy(xpath = "//select[@name='property(Stage)']")
	private WebElement stageDropDown;

	@FindBy(xpath = "//td[contains(text(),'Potential Information')]/ancestor::table[@id='secHead1']/preceding-sibling::table//input[@value='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//img[@title='Campaign Name Lookup']")
	private WebElement campaignLookupButton;

	public boolean verifyAllPotentialsTitle() {
		return titleAllPotentialText.getText().contains("All Potentials");
	}

	public boolean verifyCreatePotentialsTitle() {
		return titleCreatePotentialText.getText().contains("Create Potential");
	}

	public BasePage verifyCampaigninLookup(String campaignName) {
		webActionUtil.click(newPotentialButton);
		potentialListPage = new PotentialListPage(driver, webActionUtil);
		Assert.assertEquals(potentialListPage.verifyCreatePotentialsTitle(), true);
		webActionUtil.click(campaignLookupButton);
		String parentWindow=driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		String actualtitle = "Zoho CRM - Campaign Name Lookup";
		for (String window : windows) {
			driver.switchTo().window(window);
			if (actualtitle.equalsIgnoreCase(driver.getTitle())) {
				campaignsListPage = new CampaignsListPage(driver, webActionUtil);
				Assert.assertEquals(campaignsListPage.verifyCampaign(campaignName), true);
				driver.close();
				driver.switchTo().window(parentWindow);
				break;

			}

		}
		return new CampaignLookupPage(driver, webActionUtil);

	}

}
