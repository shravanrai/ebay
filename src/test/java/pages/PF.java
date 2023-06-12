package pages;

import org.openqa.selenium.WebDriver;

public class PF {
	private WebDriver driver;

	public PF(WebDriver driver) {
		this.driver = driver;
	}

	public EbayHomePage createHomePage() {
		return new EbayHomePage(driver);
	}

	public CellPhonesPage createCellPhonesPage() {
		return new CellPhonesPage(driver);
	}

	public ITemDetailsPage createITemDetailsPage() {
		return new ITemDetailsPage(driver);
	}

	// Create methods for other pages as needed
}
