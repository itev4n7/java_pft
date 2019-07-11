package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactsHelper extends HelperBase {

   public ContactsHelper(WebDriver driver) {
      super(driver);
   }

   public void returnToHomePage() {
      click(By.linkText("home"));
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
      click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='email'])[1]/following::img[2]"));
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
      contactsCache = null;
      returnToHomePage();
   }

   public void modification(ContactsData contacts, boolean creation) {
      initModification();
      fillForm(contacts, creation);
      submitModification();
      contactsCache = null;
      returnToHomePage();
   }

   public void create(ContactsData contactsData, boolean creation) {
      initContactCreation();
      next();
      fillForm(contactsData, creation);
      submitContactCreation();
      contactsCache = null;
      returnToHomePage();
   }

   public boolean isThereAContacts() {
      return isElementPresent(By.name("selected[]"));
   }

   private Contacts contactsCache = null;

   public Contacts all() {
      if (contactsCache != null) {
         return new Contacts(contactsCache);
      }
      contactsCache = new Contacts();
      List<WebElement> elements = driver.findElements(By.name("entry"));
      if (elements.size() != 0) {
         String[] arrElements = new String[elements.size()];
         String[] arrId = new String[elements.size()];
         for (int i = 0; i < arrElements.length; i++) {
            arrElements[i] = elements.get(i).getText();
            arrId[i] = elements.get(i).findElement(By.cssSelector("input")).getAttribute("value");
         }
         for (int i = 0; i < arrElements.length; i++) {
            String[] temp = arrElements[i].split(" ");
            contactsCache.add(new ContactsData().setId(Integer.parseInt(arrId[i])).setEmail(temp[2]).setName(temp[1]).setLastName(temp[0]));
         }
      }
      return new Contacts(contactsCache);
   }

   public int count() {
      return driver.findElements(By.name("selected[]")).size();
   }
}
