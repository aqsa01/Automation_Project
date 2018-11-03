package Action_Item;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import reusable_Object.ReusableMethod_loggers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class automation_practice {
    //declare all the global variables before annotation methods
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger1;
    ExtentTest logger2;
    JavascriptExecutor jse;


    @BeforeSuite
    public void openBrowser(){
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

    }//end of before suite

    @AfterSuite
    public void closeBrowser() {
        //end the test of the report
        report.endTest(logger1);
        report.endTest(logger2);
        //flush the report
        report.flush();
        //close the report
        report.close();

        //driver.quit()
    }//end of aftersuite

    @Test
    public void testcase1() throws IOException, InterruptedException {
        logger1 = report.startTest("Proceed to Check out for Tshirts");

        // Navigate to http://automationpractice.com/index.php
        ReusableMethod_loggers.navigate(logger1,driver, "http://automationpractice.com/index.php");

        //Verify the page title displays as My-Store //use if else with log.PASS and log.FAIL

        String  pageTitle = driver.getTitle();
        if(pageTitle.equals("My-Store")){
            logger1.log(LogStatus.PASS,"The Page title matches ");

        }else{
            logger1.log(LogStatus.FAIL, "The Page title Doesn't match" +pageTitle);

        }// end of conditional statement


        //using mouseHover method over Women tab

        ReusableMethod_loggers.mouseHover(logger1, driver, "//*[@title='Women']", "Woman tab");
        Thread.sleep(1500);

        //click on T-shirts link from there
        ReusableMethod_loggers.clickMethod(logger1, driver, "//*[@title='T-shirts']", "T-shirt link");

        //Scroll to the bottom of the page
        logger1.log(LogStatus.INFO, "Scrolling down ");

        Thread.sleep(1500);

        //scrolling down on the page
        jse.executeScript("scroll(0,350)");

        //now hover over the picture with women in it
        ReusableMethod_loggers.mouseHover(logger1, driver, "//*[@alt ='Faded Short Sleeve T-shirts']", "women image");

        //click on add to cart button
        ReusableMethod_loggers.clickMethod(logger1, driver, "//*[@title='Add to cart']","Add to Cart");

        //on the pop up using if else verify the message appears
        Thread.sleep(2500);

        //ReusableMethods_Loggers.selectByText(logger1, driver, "//*[@class='icon-ok']", "Product successfully added to your shopping cart","text");
        ReusableMethod_loggers.compareMessages(logger1, driver, "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2",
                0,"Product successfully added to your shopping cart", "text message");

        //click on proceed to checkout button

        Thread.sleep(3000);
        ReusableMethod_loggers.clickMethod( logger1, driver, "//*[@title='Proceed to checkout']", "Checkout button");

        //change the quantity to 3 items

        Thread.sleep(2000);

        ReusableMethod_loggers.clearMethod(logger1, driver, "//*[@class='cart_quantity_input form-control grey']","quantity field");

        ReusableMethod_loggers.sendKeysMethod(logger1, driver, "//*[@class='cart_quantity_input form-control grey']","3","quantity field");

        //click on proceed to check out

        Thread.sleep(3000);

        ReusableMethod_loggers.clickMethod(logger1, driver, "//*[@class='button btn btn-default standard-checkout button-medium']","Proceed to checkout");
        Thread.sleep(3000);

        } // end of test 1


    @Test (dependsOnMethods = "testcase1")
        //@Test (priority = 2)

    public void testcase2 () throws IOException, InterruptedException {
        logger2 = report.startTest("Procedd to Checkout for Summer Dresses");

        //Hover over Dresses tab
        ReusableMethod_loggers.mouseOverByIndex(logger2, driver, "//*[@class='sf-with-ul']",3, "Dresses");
        Thread.sleep(3000);

        //click on Summer Dresses
        ReusableMethod_loggers.clickMethodByIndex(logger2, driver, "//*[@title='Summer Dresses']", 1,"summer dresses");
        Thread.sleep(3000);

        //scroll down about 250 to 300 times
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        //Scroll to the bottom of the page
        logger2.log(LogStatus.INFO, "Scrolling down ");

        // Thread.sleep(1500);
        jse.executeScript("scroll(0,300)");

        //hover over first picture of the dress
        Thread.sleep(1500);
        ReusableMethod_loggers.mouseOverByIndex(logger2, driver, "//*[@title='Printed Summer Dress']", 2,"First dress");

        Thread.sleep(2000);

        //click on More tab
        ReusableMethod_loggers.clickMethodByIndex(logger2, driver, "//*[@title='View']",0,"more Button");

        Thread.sleep(1500);

        //change the quantity to 4
        ReusableMethod_loggers.clearMethod(logger2, driver, "//*[@name='qty']","quantity filed");

        ReusableMethod_loggers.sendKeysMethod(logger2, driver, "//*[@name='qty']", "4","quantity field");

        //select a size from dropdown(S,M or L)
        ReusableMethod_loggers.selectByText(logger2, driver, "//*[@name='group_1']", "M", "Size");


        //click on 'Add to Cart' button
        ReusableMethod_loggers.clickMethod(logger2, driver, "//*[@class='exclusive']", "Add to Cart");

        Thread.sleep(2000);

        //on pop up verify checkpoint says Product successfully added to your shopping cart using if else condition with logger.pass and fail
        ReusableMethod_loggers.compareMessages(logger2, driver, "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2",
                0,"Product successfully added to your shopping cart", "text message");
        Thread.sleep(2000);

        //click on proceed to checkout
        ReusableMethod_loggers.clickMethodByIndex(logger2, driver, "//*[@title='Proceed to checkout']", 0, "Proceed to checkout");

        Thread.sleep(2500);

        // next page click on delete icon to delete the item
        ReusableMethod_loggers.clickMethodByIndex(logger2, driver, "//*[@class='cart_quantity_delete']", 0,"delete icon");

        Thread.sleep(3000);
        ReusableMethod_loggers.clickMethodByIndex(logger2, driver, "//*[@class='cart_quantity_delete']", 0,"Second delete icon");

        Thread.sleep(3000);

        //on next page verify following message appears using if else
        ReusableMethod_loggers.compareMessages(logger2, driver, "//*[@class='alert alert-warning']", 0,"Your shopping cart is empty.","Message");



    }// End of test case

}// end of main parent method
