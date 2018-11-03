package Yahoo;

import Utilites.Abstract_Class_Parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import reusable_Object.ReusableMethod_loggers;

import javax.swing.text.Utilities;
import java.io.IOException;
import java.util.List;

public class ActionItem_yahoo extends Abstract_Class_Parallel {



        @Test()

        public void yahooActionItemTest () throws IOException, InterruptedException {

            //Navigate to https://www.yahoo.com
            ReusableMethod_loggers.navigate(logger, driver, "https://www.yahoo.com");

            //Step 2. Assert that we are on the correct page by checking the title = 'Yahoo‘

            String pageTitle = driver.getTitle();
            if(pageTitle.equals("Yahoo")) {
                logger.log(LogStatus.PASS, "The page title matches ");
            }else {
                logger.log(LogStatus.FAIL, "The page title doesn't match" + pageTitle);
            }// end of conditional statement

            //Display the count of options on the left side panel ('Mail', 'News', 'Sports‘ & ‘More Yahoo Sites’)

            List<WebElement> linkCount = driver.findElements(By.xpath("//*[contains(@class, 'Mstart(')]"));
            logger.log(LogStatus.INFO, "The link count is " + linkCount.size());

            //Enter 'Nutrition' on the search bar on the top

            ReusableMethod_loggers.sendKeysMethod(logger, driver, "//*[@name='p']", "Nutrition", "Search Field");

            // Click on ‘Search’ button

            ReusableMethod_loggers.clickMethod(logger, driver, "//*[@id='uh-search-button']", "Search icon");

            //Scroll down to the page
            Thread.sleep(1500);
            jse.executeScript("scroll(0,5000)");

            //Display the search result Number
            ReusableMethod_loggers.searchResultbySplit(logger, driver, "//*[@class='compPagination']", "Search Result");

            // display the seatch result number without creating a new reuable method
           //String searchResult = ReusableMethod_loggers.captureText(logger,driver, "//*[@class='comppagination']", 0, "Search Result")
           //String [] arraySearch = searchResult.split("next")
            //logger.log(LogStatus.INFO,"search count is " + arraySearch[1].trim());

            Thread.sleep(2500);

            //  Click on Sign In button
            jse.executeScript("scroll(0,-5000)");

            Thread.sleep(1500);

            ReusableMethod_loggers.clickMethod(logger, driver, "//*[@id='yucs-login_signIn']", "Sign in button");

            Thread.sleep(1500);

            //Verify the Boolean state of checkbox is selected as default //use boolen to check if check box is selected true/false

      /*  Boolean elementState = driver.findElements(By.xpath("//*for='persistent']"));


        if (elementState == true) {
            logger.log(LogStatus.PASS, "Element is checked by default");
        }else{
            logger.log(LogStatus.FAIL, "Element is not checked by default");

            ReusableMethods_Loggers.getScreenshot(driver, logger, "check mark");

            }
*/
        //verify by default the checkbox is selected
            Thread.sleep(2000);
            boolean checkboxstats = driver.findElement(By.xpath("//*[id='persistent']")).isSelected();

            if (checkboxstats==true) {
                logger.log(LogStatus.PASS, "Check box is selected");

            } else {
                logger.log(LogStatus.FAIL,"Check box is not selected");
                ReusableMethod_loggers.getScreenshot(driver,logger,"check mark");
            }


            //Enter invalid user name
            ReusableMethod_loggers.sendKeysMethod(logger, driver, "//*[@name='username']", "testabc@yahoo.com", "User Name Field");
            Thread.sleep(1500);

            //click on ‘Next’ button
            ReusableMethod_loggers.clickMethod(logger, driver, "//*[@type='submit']", "Next Button");

            //Capture the error message and verify that if message matches the following string

            // String errMsg = "Sorry, we don't recognize this email.";

            ReusableMethod_loggers.compareMessages(logger, driver, "//*[@class ='row error']", 0, "Sorry, we don't recognize this email.", "Error message");



        }// end of test annotation



    }// end of parent class

