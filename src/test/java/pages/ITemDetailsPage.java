package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ITemDetailsPage extends BasePage {

	public ITemDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='ux-textspans'][text()='Screen Size']//ancestor::div[@class='ux-labels-values__labels']//following-sibling::div//span[@class='ux-textspans']")
	WebElement getScreenSize;
	@FindBy(xpath = "//span[@itemprop='price']")
	WebElement getPrice;
	@FindBy(xpath = "//div[@role='tab']//span[text()='Screen Size']")
	WebElement screenSizeFilter;;

	// screen validations
	public Double getScreenSize() {
		return Double.parseDouble(getScreenSize.getText().split(" in")[0]);
	}

	// price validations
	public Double getPrice() {
		return Double.parseDouble(getPrice.getAttribute("content"));
	}

}