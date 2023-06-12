package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public WebDriver driver;
	public Actions actions;
	public WebDriverWait wait;
	String URL="https://www.ebay.com";
	public BasePage(WebDriver driver) {
		this.driver = driver;
		actions= new Actions(driver);
		wait=new WebDriverWait(driver, 3000l);
	}
	
	public void navigateToHomePage() {
		driver.get(URL);
	}

}
