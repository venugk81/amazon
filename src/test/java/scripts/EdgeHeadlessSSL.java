package scripts;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;

public class EdgeHeadlessSSL {
	WebDriver driver = null;

	@Test
	public void f() {

		try {

			// WebDriverManager downloads Edge browser executables or binaries.
			WebDriverManager.edgedriver().setup();

			// Create an object of Edge Options class
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setAcceptInsecureCerts(true);


			// pass the argument –headless to Edge Options class.
			edgeOptions.addArguments("--headless");

			// Create an object of WebDriver class and pass the Edge Options object as
			// an argument
			driver = new EdgeDriver(edgeOptions);

			System.out.println("Executing Edge Driver in Headless mode..");

			driver.get("https://www.bing.com/");
			System.out.println("Title of Page :" + driver.getTitle());
			System.out.println("Page URL : " + driver.getCurrentUrl());

		} catch (Exception oExp) {
			Assert.fail("Exception: " + oExp.getMessage());
		}
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
		// Close the driver
		driver.close();
		driver.quit();
	}

}
