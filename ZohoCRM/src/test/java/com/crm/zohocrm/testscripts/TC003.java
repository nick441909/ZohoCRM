package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.LeadsPage;

public class TC003 extends BaseTest {

	LeadsPage leadspage;

	@Test(description = "To Create A Lead and Verify in Lead Details Page")
	public void LeadName() {

		String cname = ExcelLibrary.getStringData1("L001", 1, 0);
		String lname = ExcelLibrary.getStringData1("L001", 1, 1);

		leadspage = (LeadsPage) homePage.clickOnNavTabLink("Leads");

		leadspage.creatingLead(cname, lname);

		homePage.clickOnNavTabLink("Leads");

		Assert.assertEquals(leadspage.verifyLeads(lname), true, "Lead name is not displayed");
	
	}
	

}

