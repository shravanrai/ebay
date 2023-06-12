package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayHomePage extends BasePage {

	public EbayHomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(text(),'Shop by category')]")
	WebElement categoryMenu;
	@FindBy(xpath = "//input[@type='text'][@class='gh-tb ui-autocomplete-input']")
	WebElement searchInput;
	@FindBy(xpath = "//input[@type='submit']")
	WebElement searchButton;

	public void selectCategory(String category) {
		categoryMenu.click();
		WebElement categoryOption = driver.findElement(By.xpath("//a[contains(text(),'" + category + "')]"));
		categoryOption.click();
	}

	public void searchItem(String item) {
		searchInput.sendKeys(item);
		searchButton.click();
	}
}
