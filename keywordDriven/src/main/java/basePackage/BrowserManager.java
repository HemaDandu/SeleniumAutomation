package basePackage;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.PropertyReader;

public class BrowserManager {
	protected static String CURRENTDIR = System.getProperty("user.dir");
	protected static Logger log = Logger.getLogger(BrowserManager.class);
	public static WebDriver driver = null;

	@BeforeClass
	public static void init() {
		File logFolder = new File(CURRENTDIR + "\\logs");
		if (!logFolder.exists()) {
			logFolder.mkdirs();
			log.info("Execution Started");
		}
	}

	@Parameters("Browser")
	@BeforeMethod
	public static void setUp(@Optional("CHROME") String browserName) {
		PropertyConfigurator.configure(CURRENTDIR + "\\Properties\\log4j.properties");
		startBrowser("CHROME");
		driver.manage().window().maximize();
		browserUrl(PropertyReader.getProperty("BaseUrl"));

	}

	public static String browserUrl(String url) {
		driver.get(url);
		log.info("Browser Url: " + url);
		return url;
	}

	public static String startBrowser(String browserName) {
		switch (browserName) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", CURRENTDIR + "\\Drivers\\chromedriver.exe");
			log.info("Chrome Browser Started");
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", CURRENTDIR + "\\Drivers\\geckodriver.exe");
			log.info("Firefox Browser Started");
			driver = new FirefoxDriver();
			break;
		default:
			log.info("Invalid Browser");
			break;
		}
		return browserName;
	}

}
