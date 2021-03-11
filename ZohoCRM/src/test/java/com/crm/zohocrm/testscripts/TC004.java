package com.crm.zohocrm.testscripts;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignsListPage;

public class TC004 extends BaseTest {
	CampaignsListPage campaignsListPage;
	
	
	
	
	@Test(description = "Functional testing of Campaigns module")
      public void campaignFunctional() {
		/*String validName=ExcelLibrary.getStringData2("F001", 1, 0);
		String invalidName=ExcelLibrary.getStringData2("F001", 1, 1);
		String invalidName1=ExcelLibrary.getStringData2("F001", 2, 1);
		String invalidName2=ExcelLibrary.getStringData2("F001", 3, 1);*/
		
		Assert.assertEquals(homePage.verifyTitle(),true,"Home Page Title is Not Correct");
		campaignsListPage=(CampaignsListPage) homePage.clickOnNavTabLink("Campaigns");
		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(),true, "All Campaigns Title is not displayed");
				
		//campaignsListPage.isDisplayed();
		
		campaignsListPage.getNewCampaignButton().click();
		
		
		
		
		campaignsListPage.enterValueInCampaignField("F001");
		
		
		

		

		
		
		 
	
}
	

}
