import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
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
        //Click on profile and then click on Sign Out button
        driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[5]/div[1]/a/span")).click();
        driver.findElement(By.xpath(".//*[@id='gb_71']")).click();
        //Verify that you are signed out
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue("Sign Out is not successful",driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).isDisplayed());
    }
    @Test
    public void gmailSendAndReceiveEmail() {
        //Go to Gmail website
        driver.manage().window().maximize();
        driver.get("https://www.google.com/gmail/about/");
        //click on Sign in button
        driver.findElement(By.xpath("html/body/nav/div/a[2]")).click();
        //Enter your user name and click next
        driver.findElement(By.xpath(".//*[@id='identifierId']")).sendKeys("sumimama90");
        driver.findElement(By.xpath(".//*[@id='identifierNext']/content/span")).click();
        //Wait for 5 secs for next page to open
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Enter Password and click next and wait for 5 sec to gmail get opened
        driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).sendKeys("cAreer_098");
        driver.findElement(By.xpath(".//*[@id='passwordNext']/content/span")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Verify that you are signed in
        Assert.assertTrue("Inbox should be there for successful login", driver.findElements(By.partialLinkText("Inbox")).size() > 0);
        //Click on Compose
        WebElement composeButton = driver.findElement(By.xpath(".//*[@id=':5u']/div/div"));
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.presenceOfElementLocated((By) composeButton));
        composeButton.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Enter Recipient's address
        WebElement recipient=driver.findElement(By.xpath(".//*[@id=':ay']"));
        WebDriverWait wait=new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(recipient));
        recipient.click();
        recipient.sendKeys("sumimama90@gmail.com");
        recipient.sendKeys(Keys.ENTER);
        //Enter Subject
        WebElement subject=driver.findElement(By.xpath("//*[@id=':ah']"));
        subject.click();
        String body="1st test mail";
        subject.sendKeys(body);
        //Enter mail context
        WebElement mailContext=driver.findElement(By.xpath("//*[@id=':bi']"));
        mailContext.click();
        mailContext.sendKeys("This is my 1st test mail");
        //Click on Send and send the mail
        WebElement sendButton=driver.findElement(By.xpath("//*[@id=':a7']"));
        sendButton.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Verify mail has been sent
        //1.Click on Inbox again
        WebElement inbox=driver.findElement(By.xpath("//*[@id=':62']/div/div[2]/span/a"));
        inbox.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //2.Click on New Mail
        WebElement newMail=driver.findElement(By.cssSelector("div[class=\"y6\"] span[id] b"));
        newMail.click();
        //3.Compare Subject
        WebElement subjectArea=driver.findElement(By.cssSelector("div[class='ha'] h2[class='hP']"));
        Assert.assertEquals("Subjects are not matching",body,subjectArea.getText());
        System.out.println("Subjects matched");
        //Click on profile and then click on Sign Out button
        driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[5]/div[1]/a/span")).click();
        WebElement signOut=driver.findElement(By.xpath(".//*[@id='gb_71']"));
        signOut.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
       // signOut.sendKeys(Keys.ENTER);
        //Verify that you are signed out
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue("Sign Out is not successful",driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input")).isDisplayed());
    }
    @After
   public void tearDown()
    {
       driver.quit();
    }
}
