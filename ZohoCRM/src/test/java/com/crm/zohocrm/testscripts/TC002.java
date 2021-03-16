package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.generic.Utility;
import com.crm.zohocrm.pom.CampaignDetailsPage;
import com.crm.zohocrm.pom.CampaignsListPage;

public class TC002 extends BaseTest {
	CampaignDetailsPage campaignDetailsPage;
	String editcampaignName;

	@Test(description = "Edit campaign page and newly created campaign detail should display")
	public void editCampaign() {
		
		String navTablink = ExcelLibrary.getStringData("TC001", 2, 0);
		String campaignName = ExcelLibrary.getStringData("TC001", 2, 1);
		String editcampaignName = ExcelLibrary.getStringData("TC001", 2, 2);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		campaignsListPage = (CampaignsListPage) homePage.clickOnNavTabLink(navTablink);
		
		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(), true, "All Campaigns Title is not displayed");

		campaignDetailsPage = (CampaignDetailsPage) campaignsListPage.createCampaign(campaignName);

		campaignDetailsPage = new CampaignDetailsPage(driver, webActionUtil);

		campaignDetailsPage.editCampaigndetails(editcampaignName);

		Assert.assertEquals(campaignDetailsPage.checkCampaignInEdit(editcampaignName), true,
				"Campaign is not displayed");

		homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(), true,
				"Campaigns Details Title is not correct");

	}

	@AfterMethod
	public void deleteCampaign() {
		Assert.assertEquals(campaignsListPage.deleteTaskCampaign(editcampaignName), true);
	}

}
