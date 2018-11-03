package TestSuite;

import Utilites.Abstract_Class;
import Utilites.Abstract_Class_Parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import reusable_Object.ReusableMethod_loggers;

import java.io.IOException;

public class Ecommerce_Test extends Abstract_Class_Parallel {

    @Test()
    public void tShirt() throws IOException, InterruptedException {

        // Navigate to http://automationpractice.com/index.php
        ReusableMethod_loggers.navigate(logger,driver, "http://automationpractice.com/index.php");

        //Verify the page title displays as My-Store //use if else with log.PASS and log.FAIL

        String  pageTitle = driver.getTitle();
        if(pageTitle.equals("My-Store")){
            logger.log(LogStatus.PASS,"The Page title matches ");

        }else{
            logger.log(LogStatus.FAIL, "The Page title Doesn't match" +pageTitle);

        }// end of conditional statement


        //using mouseHover method over Women tab

        ReusableMethod_loggers.mouseHover(logger, driver, "//*[@title='Women']", "Woman tab");
        Thread.sleep(1500);

        //click on T-shirts link from there
        ReusableMethod_loggers.clickMethod(logger, driver, "//*[@title='T-shirts']", "T-shirt link");

        //Scroll to the bottom of the page
        logger.log(LogStatus.INFO, "Scrolling down ");

        Thread.sleep(1500);

        //scrolling down on the page
        jse.executeScript("scroll(0,350)");

        //now hover over the picture with women in it
        ReusableMethod_loggers.mouseHover(logger, driver, "//*[@alt ='Faded Short Sleeve T-shirts']", "women image");

        //click on add to cart button
        ReusableMethod_loggers.clickMethod(logger, driver, "//*[@title='Add to cart']","Add to Cart");

        //on the pop up using if else verify the message appears
        Thread.sleep(2500);

        //ReusableMethods_Loggers.selectByText(logger1, driver, "//*[@class='icon-ok']", "Product successfully added to your shopping cart","text");
        ReusableMethod_loggers.compareMessages(logger, driver, "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2",
                0,"Product successfully added to your shopping cart", "text message");

        //click on proceed to checkout button

        Thread.sleep(3000);
        ReusableMethod_loggers.clickMethod( logger, driver, "//*[@title='Proceed to checkout']", "Checkout button");

        //change the quantity to 3 items

        Thread.sleep(2000);

        ReusableMethod_loggers.clearMethod(logger, driver, "//*[@class='cart_quantity_input form-control grey']","quantity field");

        ReusableMethod_loggers.sendKeysMethod(logger, driver, "//*[@class='cart_quantity_input form-control grey']","3","quantity field");

        //click on proceed to check out

        Thread.sleep(3000);

        ReusableMethod_loggers.clickMethod(logger, driver, "//*[@class='button btn btn-default standard-checkout button-medium']","Proceed to checkout");
        Thread.sleep(3000);

    } // end of test 1

}
