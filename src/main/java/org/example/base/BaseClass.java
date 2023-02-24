package org.example.base;

import org.example.config.ConfigProp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public String chromePath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe";
    public String unixChromePath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver";
    public static Properties prop;
    public ChromeOptions options = null;
    public static String cookiefile = System.getProperty("user.dir") + File.separator + "cookie" + File.separator + "cookie.data";

    public BaseClass(){
        try{
            prop = ConfigProp.getConfig();
            prop.getProperty("url");
        }catch (Exception oExp){
            Assert.fail("Base Class Failure");
        }
    }

    public ChromeOptions getChromeOptions() throws IOException{
        File file = new File(chromePath);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--start-maximized");
        options.addArguments("test-type=browser");
        options.addArguments("disable-extensions");
        options.addArguments("--no-sandbox");
//        options.setExperimentalOption("useAutomationExtension", false);
        Map<String, Object> pref = new HashMap<>();
        pref.put("download.default_directory", chromePath);
        options.setExperimentalOption("prefs", pref);
        return options;
    }

    public ChromeOptions getChromeOptionsUnix() throws IOException{
//        File file = new File(unixChromePath);
        File file = new File(chromePath);
        file.setExecutable(true);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        System.setProperty("https.proxyHost", "approxy.jpmchase.net");
        System.setProperty("https.proxyPort", "8443");

        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getenv("CHROME_BIN"));
        options.addArguments("test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-web-security");
        options.addArguments("--start-maximized");
        options.addArguments("test-type=browser");
        options.addArguments("disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--ignore-ssl-errors=true");

        Map<String, Object> pref = new HashMap<>();
        pref.put("download.default_directory", chromePath);
        options.setExperimentalOption("prefs", pref);
        return options;
    }

    public WebDriver getDriverInstance() throws IOException{
        String strEnvironment;
        try{
            strEnvironment = System.getProperty("os.name");
            System.out.println("environment: "+ strEnvironment);
            if(strEnvironment.equalsIgnoreCase("linux")){
                options = getChromeOptionsUnix();
            }else{
                options = getChromeOptions();
            }
            driver = new ChromeDriver(options);

            driver.get(prop.getProperty("url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        }catch (Exception oExp){
            Assert.fail(oExp.getMessage());
        }
        return driver;
    }
    
    public WebDriver getSpecificProfileDriverInstance() throws IOException {
    	String profile = prop.getProperty("profile");		
    	String home = System.getProperty("user.home"); 	///C:\Users\sri
    	
    	String userDir = home + File.separator + "AppData\\Local\\Google\\Chrome\\User Data\\" + File.separator + profile;
    	System.out.println("User Directory: "+ userDir);
    	
		try {
			ChromeDriverManager.chromedriver().setup();			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("--user-data-dir="+ userDir);
			options.addArguments("--profile-directory="+ profile);
		
			driver = new ChromeDriver(options);
			driver.get(prop.getProperty("url"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		} catch (Exception oExp) {
			Assert.fail(oExp.getMessage());
		}
		return driver;
	}

    public void closeDriver(){
        if(driver!=null){
            driver.close();
            driver.quit();
        }
        System.out.println("Driver is closed ang quit..");
    }

    
    

}
