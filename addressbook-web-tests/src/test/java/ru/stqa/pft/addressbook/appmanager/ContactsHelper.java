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
      type(By.name("home"), contactsData.getHomePhone());
      type(By.name("mobile"), contactsData.getMobilePhone());
      type(By.name("work"), contactsData.getWorkPhone());
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

   public void initContactsModificationById(int id) {
      driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
      initContactsModificationById(contacts.getId());
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
      for (WebElement element : elements) {
         List<WebElement> cells = element.findElements(By.tagName("td"));
         int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
         String[] fullName = cells.get(2).getText().split(" ");
         String email = cells.get(4).getText();
         String[] phones = cells.get(5).getText().split("\n");
         contactsCache.add(new ContactsData().setId(id).setEmail(email).setName(fullName[1]).setLastName(fullName[0])
                 .setHomePhone(phones[0]).setMobilePhone(phones[1]).setWorkPhone(phones[2]));
      }
      return new Contacts(contactsCache);
   }

   public int count() {
      return driver.findElements(By.name("selected[]")).size();
   }

   public ContactsData infoFromEditForm(ContactsData contacts) {
      initContactsModificationById(contacts.getId());
      String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
      String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
      String home = driver.findElement(By.name("home")).getAttribute("value");
      String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
      String work = driver.findElement(By.name("work")).getAttribute("value");
      driver.navigate().back();
      return new ContactsData().setId(contacts.getId()).setName(firstName).setLastName(lastName)
              .setHomePhone(home).setMobilePhone(mobile).setWorkPhone(work);
   }
}
