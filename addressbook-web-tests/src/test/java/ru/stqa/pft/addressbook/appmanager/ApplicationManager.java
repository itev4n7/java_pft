package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
   WebDriver driver;
   private ContactsHelper contactsHelper;
   private SessionHelper sessionHelper;
   private NavigationHelper navigationHelper;
   private GroupHelper groupHelper;
   StringBuffer verificationErrors = new StringBuffer();
   private String browser;

   public ApplicationManager(String browser) {
      this.browser = browser;
   }

   public void init() {
      selectionWebDriver();
      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      driver.get("http://localhost:8080/");
      connectionWebDriver();
      sessionHelper.login("admin", "admin");
   }

   private void connectionWebDriver() {
      contactsHelper = new ContactsHelper(driver);
      groupHelper = new GroupHelper(driver);
      navigationHelper = new NavigationHelper(driver);
      sessionHelper = new SessionHelper(driver);
   }

   private void selectionWebDriver() {
      if (browser.equals(BrowserType.CHROME)) {
         driver = new ChromeDriver();
      } else if (browser.equals(BrowserType.FIREFOX)) {
         driver = new FirefoxDriver();
      } else if (browser.equals(BrowserType.IE)) {
         driver = new InternetExplorerDriver();
      }
   }


   public void stop() {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
         fail(verificationErrorString);
      }
   }

   public GroupHelper getGroupHelper() {
      return groupHelper;
   }

   public NavigationHelper getNavigationHelper() {
      return navigationHelper;
   }

   public ContactsHelper getContactsHelper() {
      return contactsHelper;
   }

}
