package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.generic.Utility;
import com.crm.zohocrm.pom.CampaignsListPage;

import bsh.util.Util;

public class TC007 extends BaseTest {

	CampaignsListPage campaignsListPage;
	String campaignName;

	@Test(description = "To check campain name in New Task page")
	public void newTaskCampaign() {

		String navTablink = ExcelLibrary.getStringData("TC001", 1, 0);
		campaignName = ExcelLibrary.getStringData("TC001", 1, 1);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		campaignsListPage = (CampaignsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(), true, "All Campaigns Title is not displayed");

		campaignsListPage.createCampaign(campaignName);

		campaignsListPage.taskToCheckCampaignLookup(campaignName);
	}
	
	@AfterMethod
	public void deleteCampaign() {
		Assert.assertEquals(campaignsListPage.deleteTaskCampaign(campaignName), true,
				"Campaign name is not deleted");
	}

}
