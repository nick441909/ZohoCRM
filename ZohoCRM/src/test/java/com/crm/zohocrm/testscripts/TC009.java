package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.generic.Utility;
import com.crm.zohocrm.pom.CampaignsListPage;
import com.crm.zohocrm.pom.LeadDetailsPage;
import com.crm.zohocrm.pom.LeadsListPage;

public class TC009 extends BaseTest {

	LeadDetailsPage leadDetailsPage;
	CampaignsListPage campaignsListPage;
	String leadname;

	@Test(description = "To verify the created lead details in edit List page")
	public void editLeadCampaign() {

		String navTablink = ExcelLibrary.getStringData1("L001", 1, 0);
		String companyName = ExcelLibrary.getStringData1("L001", 1, 1);
		String editCompanyName = ExcelLibrary.getStringData1("L001", 1, 2);
		String lastName = ExcelLibrary.getStringData1("L001", 1, 3);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		leadsListPage = (LeadsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(leadsListPage.verifyAllLeadTitle(), true, "Title page os not correct");

		leadDetailsPage = (LeadDetailsPage) leadsListPage.creatingLead(companyName, lastName);

		leadDetailsPage = new LeadDetailsPage(driver, webActionUtil);

		leadDetailsPage.editLeaddetails(editCompanyName);

		Assert.assertEquals(leadDetailsPage.checkLeadInDetailsPage(lastName), true, "Lead Name is not present");

		homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(leadsListPage.verifyLeads(lastName), true, "Lead Name is not displayed");

	}

	@AfterMethod
	public void deleteLead() {

		Assert.assertEquals(leadsListPage.delLeadname(leadname), true);
	}

}
