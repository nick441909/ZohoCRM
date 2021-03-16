package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignsListPage;

public class TC004 extends BaseTest {
	CampaignsListPage campaignsListPage;
	String cname;
	

	@Test(description = "Functional testing of Campaigns module")
      public void campaignFunctional() {
		
		String cname = ExcelLibrary.getStringData1("F001", 1, 0);
		
		Assert.assertEquals(homePage.verifyTitle(),true,"Home Page Title is Not Correct");
		campaignsListPage=(CampaignsListPage) homePage.clickOnNavTabLink("Campaigns");
		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(),true, "All Campaigns Title is not displayed");
				
		//campaignsListPage.isDisplayed();
		
		
		campaignsListPage.enterValueInCampaignField("F001");
			
}

	@AfterMethod
	public void deleteCampaign() {

//		Assert.assertEquals(campaignsListPage.deleteTaskCampaign(cname), true);

	}

}
