package basePackage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import page_Objects.MyRequestsPage;

public class Helper extends BrowserManager {
	public static int EXPLICIT_TIMEOUT = 30;
	public static boolean flag = false;

	public static WebElement getElement(By locator) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		element = driver.findElement(locator);
		return element;
	}

	public static List<WebElement> getElements(By locator) {
		List<WebElement> elements = null;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		elements = driver.findElements(locator);
		return elements;
	}

	public static void click(By locator) {
		getElement(locator).click();
		waitForPageLoaded();
	}

	public static void enterData(By locator, String value) {
		getElement(locator).sendKeys(value);
		waitForPageLoaded();
	}

	public static ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			try {
				return (Boolean) ((JavascriptExecutor) driver)
						.executeScript("return window.jQuery != undefined || jQuery.active == 0");
			} catch (Exception e) {
				return true;
			}
		}
	};

	public static ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {

			return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete");
		}
	};
	public static ExpectedCondition<Boolean> xhrLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return ((JavascriptExecutor) driver).executeScript("return XMLHttpRequest.DONE").toString().equals("4");
		}
	};

	static HttpURLConnection con = null;

	public static boolean browserResponse() {
		try {
			HttpURLConnection.setFollowRedirects(true);

			con = (HttpURLConnection) new URL(driver.getCurrentUrl()).openConnection();
			con.setRequestMethod("HEAD");

			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			// e.printStackTrace();
			return false;
		}
	}

	public static void waitForPageLoaded() {
		try {
			// 0.5 second delay beyond which we could say slow performance
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
			flag = wait.until(jQueryLoad) && wait.until(jsLoad) && wait.until(xhrLoad);
			browserResponse();

		} catch (Exception e) {
			// System.out.println("wait for page to load exception");
			browserResponse();
		}
	}

	public static void selectbyValue(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
		waitForPageLoaded();
	}

	public static void selectbyText(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByVisibleText(value);
		waitForPageLoaded();
	}

	public static void select_DatePicker(WebElement element, String month, String day) {
		selectbyText(element, month);
		MyRequestsPage.datepicker_Enabled_Dates(day);

	}

}
