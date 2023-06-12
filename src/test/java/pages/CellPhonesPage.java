package pages;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CellPhonesPage extends BasePage {

	private PF pf;

	public CellPhonesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		pf=new PF(driver);
	}

	@FindBy(xpath = "//a[contains(text(),\"Cell Phones & Smartphones\")]")
	WebElement cellPhonesSmartphones;
	@FindBy(xpath = "//span[contains(text(),\"See All\")]")
	WebElement seeAllLink;
	@FindBy(xpath = "//div[@role='tab']//span[text()='Screen Size']")
	WebElement screenSizeFilter;;

	@FindBy(xpath = "//button[contains(@aria-label,'Apply')]")
	WebElement applyButton;
	@FindBy(xpath = "//div[@role='tab']//span[text()='Item Location']")
	WebElement itemLocationFilter;
	@FindBy(xpath = "//div[@role='tab']//span[text()='Price']")
	WebElement priceFilter;

	@FindBy(xpath = "(//a[@class='s-item__link'])[1]")
	WebElement firstResult;
	@FindBy(xpath = "//h1[@class='srp-controls__count-heading']")
	WebElement getSearchResultsCount;
	@FindBy(xpath = "(//ul[@class='srp-results srp-list clearfix']/li[contains(@id,'item')])[1]")
	WebElement getFirstSearchedITem;

	public void selectCellPhonesSmartphones() {
		cellPhonesSmartphones.click();
	}

	public void clickSeeAll() {
		seeAllLink.click();
		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='tab']//span[text()='Screen Size']")));
	}

	public void applyFilters(String sizeOption, String minPrice, String maxPrice, String locationOption) {

		screenSizeFilter.click();
		// String sizeOption = "6 in or More";
		WebElement screenSizeOptionFilter = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//span[text()='" + sizeOption + "']/../../span[contains(@class,'checkbox')]")));
		screenSizeOptionFilter.click();

		// price
		priceFilter.click();

		// String minPrice = "20";
		WebElement priceMinOptionFilter = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@aria-label,'Minimum')]")));
		priceMinOptionFilter.click();
		priceMinOptionFilter.clear();
		priceMinOptionFilter.sendKeys(minPrice);

		// String maxPrice = "100";
		WebElement priceMaxOptionFilter = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@aria-label,'Maximum')]")));
		priceMaxOptionFilter.click();
		priceMaxOptionFilter.clear();
		priceMaxOptionFilter.sendKeys(maxPrice);

		// location

		itemLocationFilter.click();
		// String locationOption = "Worldwide";
		WebElement itemLocationOptionFilter = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//input[contains(@aria-label,'" + locationOption + "')]")));
		itemLocationOptionFilter.click();

		applyButton.click();

		// waiting for results to appear.
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//a[@class='s-item__link'])[1]")));
	}

	// select first result and verify the screensize, location, price.
	public ITemDetailsPage selectFirstResultNameFromFilteredItems() {
		actions.moveToElement(firstResult);

		try {
			firstResult.click();
		} catch (Exception e) {
			driver.navigate().to(driver.getCurrentUrl());
			firstResult.click();
			e.printStackTrace();
		}

		// results open in new window.
		String parentwindowHandle = driver.getWindowHandle();
		Iterator<String> iterator = driver.getWindowHandles().iterator();
		while (iterator.hasNext()) {
			String currentWH = iterator.next();
			if (!currentWH.equals(parentwindowHandle)) {
				driver.switchTo().window(currentWH);
			}
		}

		return pf.createITemDetailsPage();
	}

	public String getFirstSearchedITemName() {
		return getFirstSearchedITem.findElement(By.xpath("//div[@class='s-item__title']/span[@role='heading']/span"))
				.getText();
	}

	public String getSearchResultsCount() {
		return getSearchResultsCount.getText();
	}

}
