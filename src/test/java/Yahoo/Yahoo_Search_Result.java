package Yahoo;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import reusable_Object.ReusableMethod_loggers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Yahoo_Search_Result {
    /*
    1. navigate to yahoo
    2. verify the home page title as 'yahoo'
    3. verify the count of text links exist on home page
    4. enter a keyword on search field
    5. click on search
    6. scroll to bottom for search
    7. capture search result
    8. send i to the extent report

  */

    //declare all the global variables before annotation methods
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;


    @BeforeSuite
    public void openBrowser(){
        //define the driver path
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //define the report path
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID()+".html",true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html",true);

    }//end of before suite

    @AfterSuite
    public void closeBrowser() {
        //end the test of the report
        report.endTest(logger);
        //flush the report
        report.flush();
        //close the report
        report.close();

        //driver.quit()
    }//end of aftersuite

    @Test
    public void YahooSearchResult() throws IOException, InterruptedException {

        //start test
        logger = report.startTest("Yahoo Search Result");
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
