package scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CookieWrite {
	WebDriver driver;
	

	@SuppressWarnings("deprecation")
	@Test
	public void login() {
		try {
			String chromePath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "chromedriver.exe";
			String cookiefile = System.getProperty("user.dir") + File.separator + "cookie" + File.separator + "cookie.data";
			File file1 = new File(chromePath);
	        System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
			driver = new ChromeDriver();
			File file = new File(cookiefile);
			FileReader fileReader = new FileReader(file);
			try (BufferedReader Buffreader = new BufferedReader(fileReader)) {
				String strline;
				while ((strline = Buffreader.readLine()) != null) {
					StringTokenizer token = new StringTokenizer(strline, ";");
					while (token.hasMoreTokens()) {
						String name = token.nextToken();
						String value = token.nextToken();
						String domain = token.nextToken();
						String path = token.nextToken();
						Date expiry = null;

						String val;
						if (!(val = token.nextToken()).equals("null")) {
							expiry = new Date(val);
						}
						Boolean isSecure = new Boolean(token.nextToken()).booleanValue();
						Cookie ck = new Cookie(name, value, domain, path, expiry, isSecure);
						System.out.println(ck);
						driver.manage().addCookie(ck); // This will add the stored cookie to your current session
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		driver.get("https://amazon.in");
		System.out.println("done..");
		

	}

}
