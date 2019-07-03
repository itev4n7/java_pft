package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsHelper extends HelperBase {

   public ContactsHelper(WebDriver driver) {
      super(driver);
   }

   public void returnToHomePage() {
      click(By.linkText("home page"));
   }

   public void fillContactsForm(ContactsData contactsData, boolean creation) {
      type(By.name("email"), contactsData.getEmail());
      type(By.name("firstname"), contactsData.getName());
      type(By.name("lastname"), contactsData.getLastName());
      if (creation) {
         new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactsData.getGroup());
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }

   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void next() {
      click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Edit / add address book entry'])[1]/following::input[1]"));
   }

   public void submitContactCreation() {
      click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
   }

   public void initContactModification() {
      click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='test1'])[1]/following::img[2]"));
   }

   public void submitContactModification() {
      click(By.name("update"));
   }
}