package TestSuite;

import Utilites.Abstract_Class;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import reusable_Object.ReusableMethod;
import reusable_Object.ReusableMethod_loggers;

import java.io.IOException;
import java.util.List;

public class Yahoo_Search extends Abstract_Class {

    @Test
    public void YahooSearchResult() throws IOException, InterruptedException {

        //navigate to yahoo homepage
        ReusableMethod_loggers.navigate(logger, driver,"https://www.yahoo.com");
        //verify the home page title
        String yahooTitle = driver.getTitle();
        if(yahooTitle.equalsIgnoreCase("Yahoo")){
            logger.log(LogStatus.PASS,"The yahoo title matches");

        }else{
            logger.log(LogStatus.FAIL, "The yahoo title doesn't match " + yahooTitle);
        }// end of conditional statement

        //verify the list count for the link
        List<WebElement> linkCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(')]"));
        logger.log(LogStatus.INFO, "The link count is " + linkCount.size());

        //Enter a keyword on a search field
        ReusableMethod_loggers.sendKeysMethod(logger,driver,"//*[@name='p']","Brooklyn","Search field");
        //click on search icon
        ReusableMethod_loggers.clickMethod(logger,driver,"//*[@id='uhaa-search-button']","Search Icon");
        //define javascript executer

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        //scroll to the bottom of the page
        logger.log(LogStatus.INFO, "Scrolling to the bottom of the search result page");

        Thread.sleep(1500);
        jse.executeScript("scroll(0,2000)");

    }//end of test

}//end of main method
