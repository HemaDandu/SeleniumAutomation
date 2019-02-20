package page_Objects;

import org.openqa.selenium.By;

import basePackage.BrowserManager;
import basePackage.Helper;

public class LoginPage extends BrowserManager {
	static By mys_userName = By.id("username");
	static By mys_Password = By.id("password");
	static By mys_login_btn = By.id("submit");
	static By mys_Logout = By.xpath("//a[contains(text(),'Logout')]");

	public static void mys_Login() throws InterruptedException {
		Helper.enterData(mys_userName, "hsankrappa");
		Helper.enterData(mys_Password, "test");
		Helper.click(mys_login_btn);
	}

	public static void mys_Logout() throws InterruptedException {
		Helper.click(mys_Logout);
	}
}
