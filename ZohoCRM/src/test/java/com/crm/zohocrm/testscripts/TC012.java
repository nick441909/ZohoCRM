package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignDetailsPage;
import com.crm.zohocrm.pom.CampaignsListPage;

public class TC012 extends BaseTest {

	CampaignDetailsPage campaignDetailsPage;
	String campaignName;

	@Test(description = "To delete a campaign and verify in campaign list page")
	public void deleteCampaignCheck() {
		String navTablink = ExcelLibrary.getStringData("TC001", 1, 0);
		String campaignName = ExcelLibrary.getStringData("TC001", 1, 1);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		campaignsListPage = (CampaignsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(), true, "All Campaigns Title is not displayed");

		campaignDetailsPage = (CampaignDetailsPage) campaignsListPage.createCampaign(campaignName);

		Assert.assertEquals(campaignsListPage.verifyCampaignDetailsTitle(), true,
				"Campaigns Details Title is not correct");

		campaignDetailsPage.deleteCampaignInDetails(campaignName);

		homePage.clickOnNavTabLink(navTablink);

	}

	@AfterMethod
	public void verifyDeletedCampaign() {

		Assert.assertEquals(campaignsListPage.verifyCampaign(campaignName), false);
	}

}
