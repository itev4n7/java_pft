package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsDeletionTests extends TestBase {
   @Test
   public void testContactsDeletion() {
      app.getNavigationHelper().goToHomePage();
      if (!app.getContactsHelper().isThereContacts()) {
         app.getContactsHelper().createContacts(new ContactsData("email", "name", null, "test1"), true);
      }
      app.getContactsHelper().selectContacts();
      app.getContactsHelper().deleteSelectedContacts();
      app.getContactsHelper().comfirmDeleteContacts();
      app.getNavigationHelper().goToHomePage();
   }
}
