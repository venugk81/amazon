package scripts.profile;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

class DefaultProfileScript {

	public WebDriver driver;
	public String url = "https://amazon.in";
	
//	ref = https://www.youtube.com/watch?v=JT03oFZS57o



	public WebDriver getDriverInstance() throws IOException {
		String userHome = System.getProperty("user.home");	//
		try {
			ChromeDriverManager.chromedriver().setup();			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("--user-data-dir=C:\\Users\\sri\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 5");
			options.addArguments("--profile-directory=Profile 5");
		
			driver = new ChromeDriver(options);
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		} catch (Exception oExp) {
			Assert.fail(oExp.getMessage());
		}
		return driver;
	}

	
	public void closeDriver() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	@BeforeMethod
	  public void beforeMethod() {		
		try {
			driver = getDriverInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }

	@Test
	public void f() {
		driver.getTitle();
		//		chrome://version in browser
		////Keep a break point here for the first time when u r executing the script. Log on to the amazon web site. 
		System.out.println("testing");
		
	}

	@AfterMethod
	public void afterMethod() {
		closeDriver();
	}

}
