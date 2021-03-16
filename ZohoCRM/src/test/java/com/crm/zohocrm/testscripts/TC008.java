package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignDetailsPage;
import com.crm.zohocrm.pom.CampaignsListPage;
import com.crm.zohocrm.pom.PotentialListPage;
import com.crm.zohocrm.pom.PotentialsPage;

public class TC008 extends BaseTest {

	PotentialListPage potentialListPage;
	String campaignName;

	@Test(description = "To verify campaign name in potential campaign lookup page")

	public void PotentialCampaign() {
		

		String navTablink = ExcelLibrary.getStringData("TC001", 1, 0);
		String navLink = ExcelLibrary.getStringData("TC001", 3, 0);
		String campaignName = ExcelLibrary.getStringData("TC001", 3, 1);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		campaignsListPage = (CampaignsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(), true, "Title is not correct);");

		campaignDetailsPage = (CampaignDetailsPage) campaignsListPage.createCampaign(campaignName);

		homePage.clickOnNavTabLink(navLink);

		potentialListPage = new PotentialListPage(driver, webActionUtil);

		Assert.assertEquals(potentialListPage.verifyAllPotentialsTitle(), true, "Potential List page is not displayed");

		potentialListPage.verifyCampaigninLookup(campaignName);

	}

	@AfterMethod
	public void deleteCampaign() {

		Assert.assertEquals(campaignsListPage.deleteTaskCampaign(campaignName), true, "Campaign name is not deleted");

	}

}
