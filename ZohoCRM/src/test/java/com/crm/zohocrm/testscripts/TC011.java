package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignsListPage;
import com.crm.zohocrm.pom.LeadDetailsPage;
import com.crm.zohocrm.pom.LeadsListPage;

public class TC011 extends BaseTest {
	
	String lastname;

	@Test(description = "To delete a lead and verifying in Lead List page")
	public void deleteLeadCheck() {

		String navTablink = ExcelLibrary.getStringData1("L001", 1, 0);
		String companyName = ExcelLibrary.getStringData1("L001", 1, 1);
		String lastName = ExcelLibrary.getStringData1("L001", 1, 3);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		leadsListPage = (LeadsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(leadsListPage.verifyAllLeadTitle(), true, "Title page os not correct");

		leadDetailsPage = (LeadDetailsPage) leadsListPage.creatingLead(companyName, lastName);

		leadDetailsPage = new LeadDetailsPage(driver, webActionUtil);

		leadDetailsPage.leadDeleteInDetails(lastName);

		homePage.clickOnNavTabLink(navTablink);

	}

	@AfterMethod
	public void verifyDeletedLead() {

		Assert.assertEquals(leadsListPage.verifyLeads(lastname), false);
	}

}
