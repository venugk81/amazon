package org.example.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;

    public BasePage(){
    }

    public BasePage(WebDriver driver){
        try{
            this.driver = driver;
        }catch (Exception oExp){
            Assert.fail("Failed. Exception Occurred: "+ oExp.getMessage());
        }
    }

    public void sendText(WebElement element, String text){
        try{
            element.sendKeys(text);
            System.out.println("Successfully sent text: "+ text);
        }catch (Exception oExp){
            Assert.fail("Failed to send text. Exception: "+ oExp.getMessage());
        }
    }

    public void click(WebElement element){
        try{
            element.click();
            System.out.println("Successfully clicked on the element");
        }catch (Exception oExp){
            Assert.fail("Failed to send text. Exception: "+ oExp.getMessage());
        }
    }

    public boolean isElementDisplayed(WebElement element, int iWait){
        boolean flag = false;
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(iWait));
            wait.until(ExpectedConditions.visibilityOf(element));
            flag = true;
        }catch (Exception oExp){
            System.out.println(oExp.getMessage());
        }
        return flag;
    }

    public void performAction(WebElement element, String data, String strAction){
        Actions actions = new Actions(driver);
        try{
            switch (strAction){
                case "click":
                    actions.click(element).click().build().perform();
                    break;
                case "hover":
                    actions.moveToElement(element).build().perform();
                    break;
                case "hoverclick":
                    actions.moveToElement(element).click().build().perform();
                    break;
            }
        }catch (Exception oExp){
            Assert.fail("Failed to perform action on the element: "+ oExp.getMessage());
        }
    }

   public boolean isElementDisplayed(WebElement element, long iWait){
        boolean flag = false;
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(iWait));
            wait.until(ExpectedConditions.visibilityOf(element));
            flag = true;
        }catch (Exception oExp){
            oExp.getMessage();
        }
        return flag;
   }


    public boolean isElementEnabled(WebElement element, int iWait){
        boolean flag = false;
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(iWait));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            flag = true;
        }catch (Exception oExp){
            System.out.println(oExp.getMessage());
        }
        return flag;
    }


}
