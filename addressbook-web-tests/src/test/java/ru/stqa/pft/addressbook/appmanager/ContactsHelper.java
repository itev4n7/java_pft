package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactsData;

import static org.testng.Assert.assertTrue;

public class ContactsHelper extends HelperBase {

   public ContactsHelper(WebDriver driver) {
      super(driver);
   }

   public void returnToHomePage() {
      click(By.linkText("home page"));
   }

   public void fillForm(ContactsData contactsData, boolean creation) {
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

   public void initModification() {
      click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='test1'])[1]/following::img[2]"));
   }

   public void submitModification() {
      click(By.name("update"));
   }

   public void select() {
      click(By.name("selected[]"));
   }

   public void deleteSelected() {
      click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]"));
   }

   public void comfirmDelete() {
      assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
   }

   public void delete() {
      select();
      deleteSelected();
      comfirmDelete();
   }

   public void createContacts(ContactsData contactsData, boolean creation) {
      initContactCreation();
      next();
      fillForm(contactsData, creation);
      submitContactCreation();
      returnToHomePage();
   }

   public boolean isThereContacts() {
      return isElementPresent(By.name("selected[]"));
   }
}
