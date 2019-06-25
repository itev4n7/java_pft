package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

   public SessionHelper(WebDriver driver) {
      super(driver);
   }

   public void login(String userName, String password) {
      type(By.name("user"),userName);
      type(By.name("pass"),password);
      click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]"));
   }

}
