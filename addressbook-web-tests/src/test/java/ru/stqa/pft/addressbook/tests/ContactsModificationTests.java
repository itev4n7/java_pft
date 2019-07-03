package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsModificationTests extends TestBase {
   @Test
   public void testContactsModification() {
      app.getNavigationHelper().goToHomePage();
      if (!app.getContactsHelper().isThereContacts()) {
         app.getContactsHelper().createContacts(new ContactsData("email", "name", null, "test1"), true);
      }
      app.getContactsHelper().initContactModification();
      app.getContactsHelper().fillContactsForm(new ContactsData("email", "name", "lastName", null), false);
      app.getContactsHelper().submitContactModification();
      app.getContactsHelper().returnToHomePage();

   }
}
