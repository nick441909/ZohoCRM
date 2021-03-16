package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignDetailsPage;
import com.crm.zohocrm.pom.CampaignsListPage;
import com.crm.zohocrm.pom.HomePage;

public class TC005 extends BaseTest {

	CampaignDetailsPage campaignDetailsPage;
	String cloneCampaignName;

	@Test(description = "To clone a campaign and verify whether the campaign name is displayed or not")
	public void cloneCampaignVerify() {
		String navTablink = ExcelLibrary.getStringData("TC001", 1, 0);
		String campaignName = ExcelLibrary.getStringData("TC001", 1, 1);
		String cloneCampaignName = ExcelLibrary.getStringData("TC001", 1, 2);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		campaignsListPage = (CampaignsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(), true, "All Campaigns Title is not displayed");

		campaignDetailsPage = (CampaignDetailsPage) campaignsListPage.createCampaign(campaignName);
		
		campaignDetailsPage.verifyclickCloneCampaign(campaignName);
		
		Assert.assertEquals(campaignsListPage.verifyCampaign(cloneCampaignName), false);

	}

	@AfterMethod
	public void deleteClonedCampaign() {

		Assert.assertEquals(campaignsListPage.deleteTaskCampaign(cloneCampaignName), true);

	}

}

//}
