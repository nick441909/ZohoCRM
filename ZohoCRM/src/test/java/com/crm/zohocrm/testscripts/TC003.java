package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.LeadDetailsPage;
import com.crm.zohocrm.pom.LeadsListPage;
import com.crm.zohocrm.pom.LeadsListPage;

public class TC003 extends BaseTest {

	LeadsListPage leadsListPage;
	String lname;

	@Test(description = "To Create A Lead and Verify in Lead Details Page")
	public void LeadName() {

		String navTabLink = ExcelLibrary.getStringData1("L001", 1, 0);
		String companyName = ExcelLibrary.getStringData1("L001", 1, 1);
		String lname = ExcelLibrary.getStringData1("L001", 1, 3);

		Assert.assertEquals(homePage.verifyTitle(), true, "Home Page Title is Not Correct");

		leadsListPage = (LeadsListPage) homePage.clickOnNavTabLink(navTabLink);

		Assert.assertEquals(leadsListPage.verifyAllLeadTitle(), true, "Lead page Title is not correct");

		leadDetailsPage = (LeadDetailsPage) leadsListPage.creatingLead(companyName, lname);

		homePage.clickOnNavTabLink(navTabLink);

		Assert.assertEquals(leadsListPage.verifyLeads(lname), true, "Lead name is not displayed");

	}

	@AfterMethod
	public void deleteLeadname() {

		Assert.assertEquals(leadsListPage.delLeadname(lname), true);

	}

}
