package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;

public class HelperBase {

   protected WebDriver driver;
   protected boolean acceptNextAlert = true;

   public HelperBase(WebDriver driver) {
      this.driver = driver;
   }

   protected void click(By locator) {
      driver.findElement(locator).click();
   }

   protected void type(By locator, String text) {
      if (text != null) {
         String existingText = driver.findElement(locator)
                 .getAttribute("value");
         if (!text.equals(existingText)) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
         }
      }
   }

   public boolean isAlertPresent() {
      try {
         driver.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }

   protected boolean isElementPresent(By locator) {
      try {
         driver.findElement(locator);
         return true;
      } catch (NoSuchElementException e) {
         return false;
      }
   }

   protected String closeAlertAndGetItsText() {
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
