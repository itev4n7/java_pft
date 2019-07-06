package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsDeletionTests extends TestBase {
   @Test
   public void testContactsDeletion() {
      app.goTo().homePage();
      if (!app.contacts().isThereContacts()) {
         app.contacts().createContacts(new ContactsData("email", "name", null, "test1"), true);
      }
      app.contacts().delete();
      app.goTo().homePage();
   }
}
