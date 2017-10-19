import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GmailSignIn {


static {
        //Launch Chrome
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Shrawan Rauniyar\\Selenium Webdrivers\\chromedriver.exe");
       }
    WebDriver driver=new ChromeDriver();

    @Test
    public void gmailLoginSuccessful()
    {
        //Go to Gmail website
        driver.manage().window().maximize();
        driver.get("https://www.google.com/gmail/about/");
        //click on Sign in button
        driver.findElement(By.xpath("html/body/nav/div/a[2]")).click();
        //Enter your user name and click next
        driver.findElement(By.xpath(".//*[@id='identifierId']")).sendKeys("sumimama90");
        driver.findElement(By.xpath(".//*[@id='identifierNext']/content/span")).click();
        //Wait for 5 secs for next page to open
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        //Enter Password and click next and wait for 5 sec to gmail get opened
        driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).sendKeys("cAreer_098");
        driver.findElement(By.xpath(".//*[@id='passwordNext']/content/span")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Verify that you are signed in
        Assert.assertTrue("Inbox should be there for successful login",driver.findElements(By.partialLinkText("Inbox")).size()>0);
        //Click on profile andthen click on Sign Out button
        driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[5]/div[1]/a/span")).click();
        driver.findElement(By.xpath(".//*[@id='gb_71']")).click();
        //Verify that you are signed out
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue("Sign Out is not successful",driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).isDisplayed());
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }
}
