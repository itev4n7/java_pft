package ru.stqa.pft.addressbook;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class GroupCreationTests {
   WebDriver driver;
   String baseUrl;
   boolean acceptNextAlert = true;
   StringBuffer verificationErrors = new StringBuffer();

   @BeforeClass(alwaysRun = true)
   public void setUp() throws Exception {
      driver = new ChromeDriver();
      baseUrl = "https://www.katalon.com/";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.get("http://localhost:8080/group.php");
      login("admin", "admin");
   }

   private void login(String userName, String password) {
      driver.findElement(By.name("user")).clear();
      driver.findElement(By.name("user")).sendKeys(userName);
      driver.findElement(By.name("pass")).clear();
      driver.findElement(By.name("pass")).sendKeys(password);
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
   }

   @Test
   public void testGroupCreation() throws Exception {
      goToGroupPage();
      initGroupCreation();
      fillCroupForm(new GroupData("test1", "test2", "test3"));
      submitGroupCreation();
      returnToGroupPage();
   }

   private void returnToGroupPage() {
      driver.findElement(By.linkText("group page")).click();
   }

   private void submitGroupCreation() {
      driver.findElement(By.name("submit")).click();
   }

   private void fillCroupForm(GroupData groupData) {
      driver.findElement(By.name("group_name")).click();
      driver.findElement(By.name("group_name")).clear();
      driver.findElement(By.name("group_name")).sendKeys(groupData.getName());
      driver.findElement(By.name("group_header")).click();
      driver.findElement(By.name("group_header")).clear();
      driver.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      driver.findElement(By.name("group_footer")).click();
      driver.findElement(By.name("group_footer")).clear();
      driver.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
   }

   private void initGroupCreation() {
      driver.findElement(By.name("new")).click();
   }

   private void goToGroupPage() {
      driver.findElement(By.linkText("groups")).click();
   }

   @AfterClass(alwaysRun = true)
   public void tearDown() throws Exception {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
         fail(verificationErrorString);
      }
   }

   private boolean isElementPresent(By by) {
      try {
         driver.findElement(by);
         return true;
      } catch (NoSuchElementException e) {
         return false;
      }
   }

   private boolean isAlertPresent() {
      try {
         driver.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }

   private String closeAlertAndGetItsText() {
      try {
         Alert alert = driver.switchTo().alert();
         String alertText = alert.getText();
         if (acceptNextAlert) {
            alert.accept();
         } else {
            alert.dismiss();
         }
         return alertText;
      } finally {
         acceptNextAlert = true;
      }
   }
}
