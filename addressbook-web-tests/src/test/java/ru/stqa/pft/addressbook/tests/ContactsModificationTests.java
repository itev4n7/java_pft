package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsModificationTests extends TestBase {
   @Test
   public void testContactsModification() {
      app.goTo().homePage();
      if (!app.contacts().isThereContacts()) {
         app.contacts().createContacts(new ContactsData("email", "name", null, "test1"), true);
      }
      app.contacts().initModification();
      app.contacts().fillForm(new ContactsData("email", "name", "lastName", null), false);
      app.contacts().submitModification();
      app.contacts().returnToHomePage();
   }
}
