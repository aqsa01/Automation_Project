package Utilites;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class_Parallel {

    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;



    @BeforeSuite
    public void openBrowser(){

        //define the report path
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID()+".html",true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html",true);

    }//end of beforesuite



    @Parameters("browser")
    @BeforeMethod()
   public void loggerSession(String browser,Method methodName) throws InterruptedException {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
            //define the driver path
            System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized", "--incognito", "--disable-infobars");
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
                driver.navigate().to("https://www.google.com");
                Thread.sleep(800);
                driver.manage().window().maximize();
                } else if (browser.equalsIgnoreCase("ie")) {
                // this is where your driver would go
            } else if (browser.equalsIgnoreCase("safari")) {
                ;
                //this is where your safari driver would go
            } //end of else if

            //timeout using implicit wait
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            //define the javascript
            jse =(JavascriptExecutor)driver;
            logger = report.startTest(methodName.getName());
            logger.log(LogStatus.INFO, "Automation Test Scenario srated....");
        }//end of before method


    @AfterMethod()
    public void endLogger(){
        //end the test of the report
        report.endTest(logger);
        logger.log(LogStatus.INFO,"Automation Test Scenario end...");
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


