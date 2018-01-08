package com.crm.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtils;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtils testUtils;
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtils = new TestUtils();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtils.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority = 1)
	public void verifyContactsLabel() {
	
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts Label is missing on Contacts Page");;
	}
	
	@Test(priority = 2)
	public void selectSingleContactsText() {
		contactsPage.selectContactsByName("aab nnn");
	}
	
	@Test(priority = 3)
	public void selectMultipleContactsText() {
		contactsPage.selectContactsByName("aab nnn");
		contactsPage.selectContactsByName("Akash Rathor");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][]= testUtils.getTestData();
		return data;
	}
	
	@Test(priority = 4, dataProvider="getCRMTestData")
	public void validateCreateNewContact() {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr", "Test", "Tester", "Google");
		contactsPage.createNewContact("Mr", "Test", "Tester", "Google");
	}

	
	@AfterMethod
	public void tearDown() {
		driver.close();	
	}
}
