package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignsListPage;

public class TC002 extends BaseTest {
	CampaignsListPage campaignsListPage;

	@Test(description = "Edit campaign page and newly created campaign detail should display")
	public void editCampaign() {
		String navTablink = ExcelLibrary.getStringData("TC001", 2, 0);
		String campaignName = ExcelLibrary.getStringData("TC001", 2, 1);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		campaignsListPage = (CampaignsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(), true, "All Campaigns Title is not displayed");

		campaignsListPage.createCampaign(campaignName);

		campaignsListPage.editCampaigndetails("M");

		homePage.clickOnNavTabLink("Campaigns");

		Assert.assertEquals(campaignsListPage.verifyCampaign(campaignName), true, "Created campaign is not displayed");

	}

	@AfterMethod
	public void deleteCampaign() {
		Assert.assertEquals(campaignsListPage.deleteCampaign("M"), true);
	}

}
