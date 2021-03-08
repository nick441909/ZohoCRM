package com.crm.zohocrm.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.zohocrm.generic.WebActionUtil;

public class HomePage extends BasePage {
	
	@FindBy(partialLinkText ="Logout")
	private WebElement logoutLink;
	
	@FindBy(xpath ="//div[@id='tabcontent']//a")
	private List<WebElement> navTabLinksList;
	
	public List<WebElement> getCampainsTabLink() {
		return navTabLinksList;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public HomePage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public void logout() {
		webActionUtil.click(logoutLink);
	}
	
	public BasePage clickOnNavTabLink(String tabLinkName) {
		for(WebElement ele:navTabLinksList) {
			if(ele.getText().equalsIgnoreCase(tabLinkName)) {
				webActionUtil.click(ele);
				break;
			}
		}
		
		switch(tabLinkName) {
			case "Campaigns": return new CampaignsListPage(driver, webActionUtil);
			case "Leads": return new LeadsPage(driver, webActionUtil);
			case "Contacts": return new ContactsPage(driver, webActionUtil);
			case "Accounts": return new AccountsPage(driver, webActionUtil);
			case "Potentials": return new PotentialsPage(driver, webActionUtil);
			case "Products": return new ProductsPage(driver, webActionUtil);
			case "Price Books": return new PriceBooksPage(driver, webActionUtil);
			case "Quotes": return new QuotesPage(driver, webActionUtil);
			case "Purchase Orders": return new PurchaseOrdersPage(driver, webActionUtil);
			case "Sales Orders": return new SalesOrdersPage(driver, webActionUtil);
			case "Invoices": return new InvoicesPage(driver, webActionUtil);
			case "Reports": return new ReportsPage(driver, webActionUtil);
			default:return null;
		}
	}

	public boolean verifyTitle() {
		return driver.getTitle().contains("Zoho CRM - Home Page");		
	}
}
