package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CellPhonesPage;
import pages.EbayHomePage;
import pages.ITemDetailsPage;
import pages.PF;

public class EbayTestScript {
	private static final Logger logger = LoggerFactory.getLogger(EbayTestScript.class);
	SoftAssert sa;
	WebDriver driver;
	private PF pf;
	
	@BeforeMethod
	public void setup() {
		sa = new SoftAssert();
		WebDriverManager.edgedriver().setup();

		// Create a new instance of EdgeDriver
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		pf = new PF(driver);

	}

	
	@Test(description = "Access a Product via category after applying multiple filters")
	public void scenarios1() {
		EbayHomePage ebayHomePage = pf.createHomePage();
		ebayHomePage.navigateToHomePage();
		ebayHomePage.selectCategory("Cell phones & accessories");

		CellPhonesPage cellPhonesPage = pf.createCellPhonesPage();
		cellPhonesPage.selectCellPhonesSmartphones();
		cellPhonesPage.clickSeeAll();

		String screenSize = "6 in or More";
		String minPrice = "20";
		String maxPrice = "100";
		String location = "Worldwide";

		cellPhonesPage.applyFilters(screenSize, minPrice, maxPrice, location);
		ITemDetailsPage iTemDetailsPage = cellPhonesPage.selectFirstResultNameFromFilteredItems();

		// screen validations
		sa.assertTrue(iTemDetailsPage.getScreenSize() >= 6, "screen size is not as expected");

		// price validations
		Double actPrice = iTemDetailsPage.getPrice();
		sa.assertTrue(actPrice >= Double.parseDouble(minPrice) || actPrice < Double.parseDouble(maxPrice),
				"price is not as expected");
		// location validations -- could not find the way to verify locations.

	}

	@Test(description = "Access a Product via Search ")
	public void scenarios2() {
		EbayHomePage ebayHomePage = pf.createHomePage();
		ebayHomePage.navigateToHomePage();
		String searchItem = "MacBook";
		ebayHomePage.searchItem(searchItem);
		CellPhonesPage cellPhonesPage = pf.createCellPhonesPage();
		logger.info("Search Results: " + cellPhonesPage.getSearchResultsCount());

		// Verify the first result name matches the search string String
		sa.assertTrue(cellPhonesPage.getFirstSearchedITemName().contains(searchItem),
				"the first result name does not matche the search string String");
	}


	@AfterMethod
	public void tearDown() {
		driver.quit();
		sa.assertAll();
	}

}
