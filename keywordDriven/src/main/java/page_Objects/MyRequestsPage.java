package page_Objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basePackage.BrowserManager;
import basePackage.Helper;

public class MyRequestsPage extends BrowserManager {
	static By my_Requests_menu = By.xpath("//a[text()='My Requests']");
	static By my_Requests_Leave = By.xpath("//a[text()='Leave / WFH']");
	static By my_Requests_Apply_Leave = By.xpath("//input[@value='Apply Leave']");
	static By My_Requests_Leave_type = By.id("leave_type");
	static By my_Requests_EL_Error = By.xpath("//span[contains(text(),'You Have Casual Leave Balance')]");
	static By select_My_Leave_From_Date = By.id("from_date");
	static By from_Date_Picker = By.xpath("//a[contains(text(),'28')]");
	static By select_My_Leave_To_Date = By.id("to_date");
	static By to_Date_Picker = By.xpath("//a[contains(text(),'4')]");
	static By enter_My_Leave_AltNum = By.xpath("//textarea[@id='contact_info']");
	static By enter_My_Leave_Comments = By.xpath("//textarea[@id='emp_comments']");
	static By click_My_Leave_Apply_Button = By.xpath("//input[@id='apply_button']");
	static By get_My_Leave_Error_Msg = By.xpath("//div[@class='error_msg']");
	static By get_My_Leave_Success_Msg = By.xpath("//div[@class='sucess_msg']");
	static By datePicker_Month_Dropdown = By.xpath("//select[@class='ui-datepicker-month']");
	static By datepicker_Disabled_Weekend_Dates = By
			.xpath("// td[@class=' ui-datepicker-week-end ui-datepicker-unselectable ui-state-disabled ']");
	static By datepicker_Disabled_Holiday_Dates = By
			.xpath("// td[@class='ui-datepicker-unselectable ui-state-disabled undefined']");
	static By datepicker_Disabled_Past_Dates = By
			.xpath("	// td[@class=' ui-datepicker-unselectable ui-state-disabled ']");
	static By datepicker_Enabled_Dates = By.xpath("//td[@class=' ']");

	public static void click_My_Requests_Menu() {
		Helper.click(my_Requests_menu);
	}

	public static void click_My_Requests_Leave() {
		Helper.click(my_Requests_Leave);
	}

	public static void click_My_Requests_Apply_Leave() {
		Helper.click(my_Requests_Apply_Leave);

	}

	public static WebElement select_My_Requests_Leave() {
		return driver.findElement(My_Requests_Leave_type);

	}

	public static void my_Requests_EL_Error() {
		System.out.println(driver.findElement(my_Requests_EL_Error).getText());
	}

	public static void click_From_Date_Field() throws InterruptedException {
		Helper.click(select_My_Leave_From_Date);
		// Helper.click(from_Date_Picker);
		// Helper.selectbyText(datepicker_Month_Dropdown(), "Jan");
		Helper.select_DatePicker(datepicker_Month_Dropdown(), "Feb", "5");

	}

	public static void click_To_Date_Field() throws InterruptedException {
		Helper.waitForPageLoaded();
		Helper.click(select_My_Leave_To_Date);
		// Helper.click(to_Date_Picker);
		// Helper.selectbyText(datepicker_Month_Dropdown(), "Feb");
		// datepicker_Enabled_Dates("1");
		Helper.select_DatePicker(datepicker_Month_Dropdown(), "Feb", "5");
	}

	public static void enter_My_Leave_AltNum() throws InterruptedException {
		Helper.enterData(enter_My_Leave_AltNum, "7487587478");
	}

	public static void enter_My_Leave_Comments() throws InterruptedException {
		Helper.enterData(enter_My_Leave_Comments, "test");
	}

	public static void click_My_Leave_Apply_Button() throws InterruptedException {
		Helper.click(click_My_Leave_Apply_Button);
	}

	public static String get_My_Leave_Error_Msg() throws InterruptedException {
		return driver.findElement(get_My_Leave_Error_Msg).getText();
	}

	public static String get_My_Leave_Success_Msg() throws InterruptedException {
		return driver.findElement(get_My_Leave_Success_Msg).getText();
	}

	public static WebElement datepicker_Month_Dropdown() {
		return driver.findElement(datePicker_Month_Dropdown);
	}

	public static List<WebElement> datepicker_Disabled_Weekend_Dates() {
		List<WebElement> elements = driver.findElements(datepicker_Disabled_Weekend_Dates);
		for (WebElement ele : elements) {
			System.out.println(ele.getText());
		}
		return elements;
	}

	public static List<WebElement> datepicker_Disabled_Holiday_Dates() {
		List<WebElement> elements = driver.findElements(datepicker_Disabled_Holiday_Dates);
		for (WebElement ele : elements) {
			System.out.println(ele.getText());
		}
		return elements;
	}

	public static List<WebElement> datepicker_Disabled_Past_Dates() {
		List<WebElement> elements = driver.findElements(datepicker_Disabled_Past_Dates);
		for (WebElement ele : elements) {
			System.out.println(ele.getText());
		}
		return elements;
	}

	public static void datepicker_Enabled_Dates(String day) {
		List<WebElement> elements = driver.findElements(datepicker_Enabled_Dates);
		for (int i = 0; i <= elements.size() - 1; i++) {
			if (elements.get(i).getText().equals(day)) {
				System.out.println(elements.get(i).getText());
				elements.get(i).click();
				log.info("selected date");
				break;
			}
		}

	}

}
