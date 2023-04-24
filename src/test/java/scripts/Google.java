package scripts;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Google {
	
	public WebDriver driver = null;
	public By name = By.name("q");
	public ChromeOptions options = null;
	public String chromePath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe";
	public String url ="https://www.google.com";
	
@BeforeSuite
public void beforeSuite() throws IOException {
	
		System.out.println("I am in before suite");
	    
	    driver = new ChromeDriver(getChromeOptions());
	    System.out.println("Get URL");
	    driver.get(url);
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
}

@Test
public void test() {
	driver.findElement(name).sendKeys("Testing..");
	System.out.println("Sent Keys.. ");
	
}

@AfterSuite
public void afterSuite() {
	driver.quit();
	
}



public ChromeOptions getChromeOptions() throws IOException{
    File file = new File(chromePath);
    System.out.println("Check for chrome driver..");
    if(file.exists()) {
    	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("test-type");
	    options.addArguments("--start-maximized");
	    options.addArguments("test-type=browser");
	    options.addArguments("disable-extensions");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--remote-allow-origins=*");
	    Map<String, Object> pref = new HashMap<>();
	    pref.put("download.default_directory", chromePath);
	    options.setExperimentalOption("prefs", pref);
    }else {
    	System.out.println("Chrome driver doesn't exist..");
    }
   
    return options;
}

}
