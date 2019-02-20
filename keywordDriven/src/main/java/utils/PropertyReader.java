package utils;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import basePackage.BrowserManager;

public class PropertyReader extends BrowserManager {
	private static Properties props;

	public static void readProperty() {
		try {
			FileReader reader = new FileReader(new File(CURRENTDIR + "\\Properties\\config.properties"));
			props = new Properties();
			props.load(reader);
			reader.close();

		} catch (Exception e) {
			System.out.println("File not found");
		}
	}

	public static String getProperty(String property) {
		readProperty();
		// System.out.println(props.getProperty(property));
		return props.getProperty(property);
	}

	// public static void main(String[] args) {
	// getProperty("BaseUrl");
	// }
}
