package Utilites;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class {

    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;

    @BeforeSuite
    public static void openBrowser() {
        //define the driver path
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito","--disable-infobars");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //define the report path
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID()+".html",true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html",true);

        //define the javascript
        jse = (JavascriptExecutor)driver;

    }//end of beforesuite

    @BeforeMethod()
   public void loggerSession(Method methodName){
        logger = report.startTest(methodName.getName());
        logger.log(LogStatus.INFO,"Automation Test Scenario srated....");
   }//end of before method

    @AfterMethod
    public void endLogger() {
        //end the test of the report
        report.endTest(logger);
        logger.log(LogStatus.INFO, "Automation Test Scenario end...");
    }//end of after method


        @AfterSuite
        public void closeBrowser () {
            //end the test of the report

            //flush the report
            report.flush();
            //close the report
            report.close();

            //driver.quit()
        }//end of aftersuite



    } //end of parent method
