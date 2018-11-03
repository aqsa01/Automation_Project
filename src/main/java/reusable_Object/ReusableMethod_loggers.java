package reusable_Object;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

import static jxl.biff.BaseCellFeatures.logger;

public class ReusableMethod_loggers {

    //method ro navigating to a page
    public static void navigate(ExtentTest logger, WebDriver driver, String url) throws IOException {
        try {
            //System.out.print("Navigating to " + url);
            logger.log(LogStatus.INFO, "Navigating to " + url);
            driver.navigate().to(url);
        } catch (Exception err) {
            //System.out.println("Unable to navigate to the url... " + err);
            logger.log(LogStatus.FAIL, "Unable to navigte to the url... " + err);
            getScreenshot(driver, logger, "URL error");
        }
    }//end of navigate method

    //method for clicking on an element
    public static void clickMethod(ExtentTest logger,WebDriver driver, String locator, String elementName) throws IOException {

        try{
            System.out.println("Clicking on element " + elementName);
            logger.log(LogStatus.INFO,"Clicking on element " + elementName);
            //store the locator into WebElement variable
            WebElement clickbtn = driver.findElement(By.xpath(locator));
            clickbtn.click();
            //logger.log(LogStatus.PASS,"Succesfully clicked on the element " + elementName);
        }catch (Exception err){
            //System.out.println("Unable to click on element " + elementName);
            logger.log(LogStatus.FAIL,"Unable to click on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of click method



    //method for clearing on an element
    public static void clearMethod(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException{
        try {
            //System.out.println("Clearing on element " + elementName);
            logger.log(LogStatus.PASS,"Clearing on element " + elementName);
            getScreenshot(driver,logger,elementName);
            //store the locator into WebElement variable
            WebElement clrBtn = driver.findElement(By.xpath(locator));
            clrBtn.clear();
        } catch (Exception err) {
            //System.out.println("Unable to clear on element " + elementName + " " + err);
            logger.log(LogStatus.FAIL,"Unable to clear on element " + elementName + " " + err);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of clear method

    //method for clicking on an element by index
    public static void clickMethodByIndex(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {

        try{
            //System.out.println("Clicking on element " + elementName);
            logger.log(LogStatus.PASS,"Clicking on element " + elementName);
            getScreenshot(driver,logger,elementName);

            //store the locator into WebElement variable
            WebElement clickbtn = driver.findElements(By.xpath(locator)).get(indexNumber);
            clickbtn.click();
        }catch (Exception err){
            //System.out.println("Unable to click on element " + elementName);
            logger.log(LogStatus.FAIL,"Unable to click on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of click by index method

    //method for submitting on an element
    public static void submitMethod(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {
        try {
           // System.out.println("Submitting  on element " + elementName);
            logger.log(LogStatus.PASS,"Submitting on element " + elementName);
            getScreenshot(driver,logger,elementName);

            //store the locator into WebElement variable
            WebElement submitBtn = driver.findElement(By.xpath(locator));
            submitBtn.submit();
        } catch (Exception err) {
            //System.out.println("Unable to Submit on element " + elementName + " " + err);
            logger.log(LogStatus.FAIL,"Unable to Submit on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of submit method

    //method for entering on an element
    public static void sendKeysMethod(ExtentTest logger,WebDriver driver, String locator, String userInput, String elementName) throws IOException {
        try{
            //System.out.println("Entering " + userInput + " in element " + elementName);
            logger.log(LogStatus.INFO,"Entering " + userInput + " in element " + elementName);
            //store the locator into WebElement variable
            WebElement input = driver.findElement(By.xpath(locator));
            input.sendKeys(userInput);
        }catch (Exception err){
            //System.out.println("Unable to send info on element " + elementName);
            logger.log(LogStatus.FAIL,"Unable to send info on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of Send Keys method



    //dropdown method by visible text
    public static void selectByText(ExtentTest logger, WebDriver driver, String locator, String value, String elementName) throws IOException {
        try {
            //System.out.println("Selecting " + value + " from dropdown " + elementName);
            logger.log(LogStatus.PASS,"Selecting " + value + " from dropdown " + elementName);
            getScreenshot(driver,logger,elementName);
            //define the Web Element
            WebElement element = driver.findElement(By.xpath(locator));
            //define the select command
            Select select = new Select(element);
            // select by visible text
            select.selectByVisibleText(value);
        } catch (Exception err) {
            //System.out.println("Unable to select a value from dropdown " + elementName + " " + err);
            logger.log(LogStatus.FAIL,"Unable to select value from dropdown " + elementName);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
    }//end of select by text method

    //method for getText
    public static String captureText( ExtentTest logger,WebDriver driver,String locator,int indexNumber,String elementName) throws IOException {
        String textValue = null;
        try{
            //System.out.println("Capturing text " + elementName);
            logger.log(LogStatus.PASS,"Capturing text " + elementName);
            getScreenshot(driver,logger,elementName);
            textValue = driver.findElements(By.xpath(locator)).get(indexNumber).getText();
        }catch (Exception err){
            //System.out.println("Unable to capture text " + elementName + " " + err);
            logger.log(LogStatus.FAIL,"Unable to capture text " + elementName + " " + err);
            getScreenshot(driver,logger,elementName);
        }//end of try catch
        return textValue;
    }//end of capture text method

    //method for screenshot

    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_Folder\\ScreenShots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);
    }// end of screenshot method

    // Method for Mouse over

    public static void mouseHover (ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {

        Actions mouseHover = new Actions(driver);

        try{
            logger.log(LogStatus.INFO, "using mouse hover on element " +elementName);
            WebElement element= driver.findElement(By.xpath(locator));
            mouseHover.moveToElement(element).perform();

        }catch(Exception error){
            logger.log(LogStatus.FAIL, "Unable to hover over the element "+elementName);
            getScreenshot(driver, logger, elementName);

            }// end try and catch
    }// end of MouseHover method

    // mouse over by index
    public static void mouseOverByIndex(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {
        Actions mouseHoverByIndex = new Actions(driver);
        try {
            logger.log(LogStatus.INFO, "Hovering on element" +elementName);
            getScreenshot(driver,logger, elementName);
            // System.out.println("Clicking on element" + elementName);

            //Store the locator into webelement variable

            WebElement hover = driver.findElements(By.xpath(locator)).get(indexNumber);
            mouseHoverByIndex.moveToElement(hover).perform();
        } catch (Exception error) {
            //System.out.println("Unable to hover on element" + elementName);
            logger.log(LogStatus.FAIL, "unable to hover on element"+ elementName+ " "+error);
            getScreenshot(driver, logger, elementName);
        }// end of try and catch
    }// end of click method

    // Mouseover click by index
    public static void mouseOverClickByIndex(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {
        Actions mouseHoverClickByIndex = new Actions(driver);
        try {
            logger.log(LogStatus.INFO, "Clicking on element" +elementName);
            // System.out.println("Clicking on element" + elementName);

            //Store the locator into webelement variable

            WebElement clickbtn = driver.findElements(By.xpath(locator)).get(indexNumber);
            mouseHoverClickByIndex.moveToElement(clickbtn).click().perform();
        } catch (Exception error) {
            //System.out.println("Unable to click on element" + elementName);
            logger.log(LogStatus.FAIL, "unable to hover on element"+ elementName+ " "+error);
            getScreenshot(driver, logger, elementName);
        }// end of try and catch
    }// end of click method

    // Text selection method

    public static String textSelectionWithif(ExtentTest logger, WebDriver driver, String locator, String matchingText, String elementName ) throws IOException {
        Actions mouseMovement = new Actions(driver);
        String text = null;

        try {
            logger.log(LogStatus.INFO, "Capturing text is" + elementName);
            WebElement txtElement = driver.findElement(By.xpath(locator));
            text = txtElement.getText();
            if (text.equalsIgnoreCase(matchingText)) {
                logger.log(LogStatus.PASS, "Captured text Matched with " + matchingText);
            } else {
                logger.log(LogStatus.FAIL, "Captured text didn't match " + matchingText);
            }
        } catch (Exception error) {
            logger.log(LogStatus.FAIL, "Unable to find the specific text of " + matchingText);
            getScreenshot(driver, logger, elementName);
        }
        return text;
    }

        //method for comparing two data
        public static void compareMessages(ExtentTest logger,WebDriver driver,String locator,int indexNumber,String expectedMessages,String elementName) throws IOException {
          try {
                String actualMessage = driver.findElements(By.xpath(locator)).get(indexNumber).getText();
                if (expectedMessages.equalsIgnoreCase(actualMessage)) {
                    logger.log(LogStatus.PASS, "Expected matches with actual for element " + elementName);
                } else {
                    logger.log(LogStatus.FAIL, "Expected - " + expectedMessages + "doesn't match with actual ");
                    getScreenshot(driver,logger,elementName);
                    }

          }catch (Exception err) {
              logger.log(LogStatus.FAIL,"Unable to locate element " + elementName);
              getScreenshot(driver,logger,elementName);

          }//end of try and catch
        }//end of comparing 2 data

    // Search result by split
    public static String searchResultbySplit(ExtentTest logger, WebDriver driver, String locator, String elementName) {

        String[] arraySearch = null;
        try {
            logger.log(LogStatus.INFO, "Capturing search result" + elementName);
            WebElement txtElement = driver.findElement(By.xpath(locator));
            String text = txtElement.getText();
            arraySearch = text.split("Next");
            logger.log(LogStatus.PASS, "Captured text is " + arraySearch[1] );


        } catch (Exception error) {
            logger.log(LogStatus.FAIL, "Unable to capture the search result " + elementName);

        }// end of try and catch

        return arraySearch[1];

    }// end of the method
}//end of parent class





