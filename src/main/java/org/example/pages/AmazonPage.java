package org.example.pages;

import org.example.base.BaseClass;
import org.example.base.BasePage;
import org.example.config.ConfigProp;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Properties;

public class AmazonPage extends BasePage {
	WebDriver driver;
	public static Properties prop;
	@FindBy(xpath = "//span[text()='Hello, sign in']")
	WebElement spnHelloSignIn;
	@FindBy(xpath = "//span[text()='Sign in']")
	WebElement spnSignIn;
	@FindBy(id = "ap_email")
	WebElement txtEmail;

	@FindBy(id = "continue")
	WebElement btnContinue;

	@FindBy(id = "ap_password")
	WebElement txtPassword;

	@FindBy(id = "signInSubmit")
	WebElement btnSubmit;

	@FindBy(id = "nav-cart")
	WebElement lnkCart;
	@FindBy(xpath = "//input[@value='Proceed to checkout']")
	WebElement btnProceedToBuy;

	@FindBy(xpath = "//input[@aria-labelledby='orderSummaryPrimaryActionBtn-announce']")
	WebElement btnUseThisAddress;
	@FindBy(xpath = "//input[contains(@value,'instrumentId=NetBanking')]")
	WebElement spnNetBanking;

	@FindBy(xpath = "//span[text()='Choose an Option']")
	WebElement lstChooseAnOption;

	@FindBy(xpath = "//ul[@role='listbox']/li[2]")
	WebElement liFirstBank;

	@FindBy(xpath = "//input[@aria-labelledby='orderSummaryPrimaryActionBtn-announce']")
	WebElement btnUseThisPayment;
	
	@FindBy(xpath = "//div[@id='subtotals']//span[@id='submitOrderButtonId']")
	WebElement btnPlaceYourOrder;

	@FindBy(id = "nav-cart-count")
	WebElement cartCount;

	public AmazonPage(WebDriver _driver) {
		super(_driver);
		prop = ConfigProp.getConfig();
		driver = _driver;
		PageFactory.initElements(driver, this);
	}

	public AmazonPage login() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		try {

			Thread.sleep(1000);
			if (spnHelloSignIn.isDisplayed()) {
				click(spnHelloSignIn);
				performAction(spnSignIn, "click", "");
				Thread.sleep(3000);
				isElementDisplayed(txtEmail, 10);
				sendText(txtEmail, username);
				click(btnContinue);
				sendText(txtPassword, password);
				click(btnSubmit);
				System.out.println("Testing");

				// create file named Cookies to store Login Information
				File file = new File(BaseClass.cookiefile);
				try {
					// Delete old file if exists
					file.delete();
					file.createNewFile();
					FileWriter fileWrite = new FileWriter(file);
					BufferedWriter Bwrite = new BufferedWriter(fileWrite);
					// loop for getting the cookie information
					for (Cookie ck : driver.manage().getCookies()) {
						Bwrite.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath()
								+ ";" + ck.getExpiry() + ";" + ck.isSecure()));
						Bwrite.newLine();
					}
					Bwrite.close();
					fileWrite.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception oExp) {
			Assert.fail(oExp.getMessage());
		}
		return this;
	}

	public AmazonPage checkCartCountAndOrder() {
		try {
			if (cartCount.isDisplayed()) {
				int count = Integer.parseInt(cartCount.getText());
				if (count > 0) {
					placeOrder();
				}
			}
		} catch (Exception e) {
			if (spnHelloSignIn.isDisplayed()) {
				System.out.println("User is not logged in. Login if login is set to yes in properties file..");
				if(prop.getProperty("login").equalsIgnoreCase("yes"))
					login();
			}
		}
		return this;
	}

	public void placeOrder() {
		try {
			String text = lnkCart.getAttribute("aria-label");
			System.out.println(text);
			String[] arr = text.split(" ");
			System.out.println("Number of items in the cart: " + arr[0]);
			if (Integer.parseInt(arr[0]) > 0) {
				click(lnkCart);
				isElementDisplayed(btnProceedToBuy, 2);
				
				click(btnProceedToBuy, "Proceed to Buy");				
				
				isElementDisplayed(btnUseThisAddress, 5);
				click(btnUseThisAddress, "Use this Address button");
				isElementEnabled(spnNetBanking, 15);
				click(spnNetBanking, "Net Banking Option");				
				
				isElementEnabled(lstChooseAnOption, 10);
				click(lstChooseAnOption, "Choose an Option");
				// Second card selection from net banking drop down
				Thread.sleep(1000);
				click(liFirstBank, "First Bank");				
				isElementDisplayed(btnUseThisPayment, 10);
				isElementEnabled(btnUseThisPayment, 20);
				
				click(btnUseThisPayment, "Use this Option button");	
				Thread.sleep(6000);
				if (isElementEnabled(btnPlaceYourOrder, 20)) {
//					click(btnPlaceYourOrder, "Place your Order");
					performAction(btnPlaceYourOrder, "", "click");
					System.out.println("Clicked on Place Your Oder...............");
					Thread.sleep(20000);
				}
			}
		} catch (Exception e) {
			Assert.fail("Failed to order. Exception: "+ e.getMessage());
		}
	}

}
