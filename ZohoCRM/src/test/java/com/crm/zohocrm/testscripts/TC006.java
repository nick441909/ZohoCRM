package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignDetailsPage;
import com.crm.zohocrm.pom.CampaignsListPage;

public class TC006 extends BaseTest {
	
	String campaignName;

	CampaignDetailsPage campaignDetailsPage;

	@Test(description = "To verify the details of campaign name in print preview page")
	public void printCampaign() {

		String navTablink = ExcelLibrary.getStringData("TC001", 1, 0);
		String campaignName = ExcelLibrary.getStringData("TC001", 1, 1);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		campaignsListPage = (CampaignsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(), true, "All Campaigns Title is not displayed");

		campaignDetailsPage = (CampaignDetailsPage) campaignsListPage.createCampaign(campaignName);
		
		campaignDetailsPage.printPreviewCampaignVerify(campaignName);
		

	}

	@AfterMethod
	public void deleteCampaign() {
		Assert.assertEquals(campaignsListPage.deleteTaskCampaign(campaignName), true,
				"Campaign name is not deleted");
	}
}
