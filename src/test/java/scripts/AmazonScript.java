package scripts;

import org.example.base.BaseClass;
import org.example.pages.AmazonPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class AmazonScript extends BaseClass {
    AmazonPage amazonPage;
    @BeforeMethod
    public void beforeMethod(){

    }

    @Test
    public void purchaseItems() throws IOException {
        amazonPage = new AmazonPage(getDriverInstance()).login();
        amazonPage.placeOrder();


    }

    @AfterMethod
    public void afterMethod(){
        closeDriver();
    }
}
