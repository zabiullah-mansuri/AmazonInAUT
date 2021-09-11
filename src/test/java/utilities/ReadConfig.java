package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {
		File src = new File("./configurations/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getAppUrl() {
		return pro.getProperty("appUrl");
	}

	public String getName() {
		return pro.getProperty("name");

	}

	public String getUserId() {
		return pro.getProperty("userId");
	}

	public String getPassword() {
		return pro.getProperty("password");
	}

	public String getWrongUserId() {
		return pro.getProperty("wrongUserId");
	}

	public String getWrongPassword() {
		return pro.getProperty("wrongPassword");
	}

	public String getChromeDriverPath() {
		return pro.getProperty("chromedriver");
	}

	public String getFireFoxDriverPath() {
		return pro.getProperty("firefoxdriver");
	}

	public String getIEDriverPath() {
		return pro.getProperty("iedriver");
	}

	public String getEdgeDriverPath() {
		return pro.getProperty("edgedriver");
	}

}
