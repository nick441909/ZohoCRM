package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.LeadDetailsPage;
import com.crm.zohocrm.pom.LeadsListPage;

public class TC010 extends BaseTest {
	
	String cloneLastName;

	@Test(description = "To clone a lead and verifying in clone page")
	public void cloneLead() {

		String navTablink = ExcelLibrary.getStringData1("L001", 1, 0);
		String companyName = ExcelLibrary.getStringData1("L001", 1, 1);
//		String editCompanyName = ExcelLibrary.getStringData1("L001", 1, 2);
		String lastName = ExcelLibrary.getStringData1("L001", 1, 3);
		String cloneLastName = ExcelLibrary.getStringData1("L001", 1, 4);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		leadsListPage = (LeadsListPage) homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(leadsListPage.verifyAllLeadTitle(), true, "Title page os not correct");

		leadDetailsPage = (LeadDetailsPage) leadsListPage.creatingLead(companyName, lastName);

		leadDetailsPage = new LeadDetailsPage(driver, webActionUtil);

		Assert.assertEquals(leadDetailsPage.cloneLead(cloneLastName), true, "Lead name is not displayed");

		homePage.clickOnNavTabLink(navTablink);

		Assert.assertEquals(leadsListPage.verifyLeads(lastName), true, "Lead Name is not displayed");

	}

	@AfterMethod
	public void deleteLead() {

		Assert.assertEquals(leadsListPage.delLeadname(cloneLastName), true);
	}

}
