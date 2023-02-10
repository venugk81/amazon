package org.example.pages;

import org.example.base.BasePage;
import org.example.config.ConfigProp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Properties;

public class AmazonPage extends BasePage {
    WebDriver driver;
    public static Properties prop;
    @FindBy(xpath="//span[text()='Hello, sign in']")
    WebElement spnHelloSignIn;
    @FindBy(xpath="//span[text()='Sign in']")
    WebElement spnSignIn;
    @FindBy(id="ap_email")
    WebElement txtEmail;

    @FindBy(id="continue")
    WebElement btnContinue;

    @FindBy(id="ap_password")
    WebElement txtPassword;

    @FindBy(id="signInSubmit")
    WebElement btnSubmit;

    @FindBy(id="nav-cart")
    WebElement lnkCart;
    @FindBy(xpath="//input[@value='Proceed to checkout']")
    WebElement btnProceedToBuy;

    @FindBy(xpath="//input[@aria-labelledby='orderSummaryPrimaryActionBtn-announce']")
    WebElement btnUseThisAddress;
    @FindBy(xpath="//input[contains(@value,'instrumentId=NetBanking')]")
    WebElement spnNetBanking;

    @FindBy(xpath="//span[text()='Choose an Option']")
    WebElement lstChooseAnOption;

    @FindBy(xpath="//ul[@role='listbox']/li[2]")
    WebElement liFirstBank;

    @FindBy(xpath="//input[@aria-labelledby='orderSummaryPrimaryActionBtn-announce']")
    WebElement btnUseThisPayment;

    public AmazonPage(WebDriver _driver){
        super(_driver);
        prop = ConfigProp.getConfig();
        driver = _driver;
        PageFactory.initElements(driver, this);
    }

    public AmazonPage login(){
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        try{

            Thread.sleep(1000);
            if(spnHelloSignIn.isDisplayed()){
                click(spnHelloSignIn);
                performAction(spnSignIn, "click", "");
                Thread.sleep(3000);
                isElementDisplayed(txtEmail, 10);
                sendText(txtEmail, username);
                click(btnContinue);
                sendText(txtPassword, password);
                click(btnSubmit);
                System.out.println("Testing");
            }


        }catch (Exception oExp){
            Assert.fail(oExp.getMessage());
        }
        return this;
    }

    public void placeOrder(){
        String text = lnkCart.getAttribute("aria-label");
        System.out.println(text);
        String[] arr = text.split(" ");
        System.out.println("Number of items in the cart: "+ arr[0]);
        if(Integer.parseInt(arr[0])>0) {
            click(lnkCart);
            isElementDisplayed(btnProceedToBuy, 2);
            click(btnProceedToBuy);
            isElementDisplayed(btnUseThisAddress, 2);
            click(btnUseThisAddress);
            isElementEnabled(spnNetBanking, 5);
            click(spnNetBanking);
            click(lstChooseAnOption);
            click(liFirstBank);
            System.out.println("testing..");
            click(btnUseThisPayment);
        }
    }


}
