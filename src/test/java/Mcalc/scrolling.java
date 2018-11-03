package Mcalc;

import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

 public class scrolling {

 /*
    WebDriver driver;
    @Test
            public void scrolling() throws InterruptedException {

        //define the driver path
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        //timeout using implicit wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //navigate to the site
        driver.navigate().to("https://www.mortagecalculator.net/");
        //Defining the Javasciptexecutor
        JavascriptExecutor jse =(JavascriptExecutor)driver;
        //scroll into the calculator element
        WebElement calculateBtn = driver.findElement(By.xpath("//*[@value='Calculate Now']"));
        jse.executeScript("arguments[0].scrollIntoView(true);", calculateBtn);
        Thread.sleep(1500);

        //scroll by number of the time going down

        jse.executeScript("scroll(0,700)");

        //scrolling to the up
        Thread.sleep(1500);
        jse.executeScript("scroll(0,-700)");

        //end of scroling

 */

 }//end of main method


