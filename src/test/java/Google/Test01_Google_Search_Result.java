package Google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import reusable_Object.ReusableMethod;

import java.util.concurrent.TimeUnit;

public class Test01_Google_Search_Result {
    //global or shared varaibles across methods need to be declared
    //before calling annotations
    WebDriver driver;

    @BeforeSuite
    public void openBrowser() {
        //define the chrome path
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        //define the chrome options
        ChromeOptions options = new ChromeOptions();
        //define the arguments for options
        options.addArguments("start-maximized", "incognito");
        //define the web driver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void closeBrowser() {
        // driver.quit();
    }

    @Test
    public void Testcase1() {
        System.out.println("Navigating to google home page");
        driver.navigate().to("https://www.google.com");
        //enter a keyword in google search
        ReusableMethod.sendKeysMethod(driver, "//*[@name='q']", "Brooklyn", "Search Input");
        //submit on google search
        ReusableMethod.submitMethod(driver, "//*[@value='Google Search']", "Google Search");
    }

    @Test(dependsOnMethods = "Testcase1")
    public void TestCase2() {

        try {
            String searchResult = driver.findElement(By.xpath("//*[@id='resultStats']")).getText();
            String[] searchNumber = searchResult.split(" ");
            System.out.println("my search number is " + searchNumber[1]);
        } catch (Exception err) {
            System.out.println("Unable to capture text for search Result");
        }
    }


}// end of parent class
