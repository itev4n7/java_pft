package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsCreationTests extends TestBase {
   @Test(enabled = false)
   public void testContactSCreation() {
      app.getNavigationHelper().goToHomePage();
      app.getContactsHelper().createContacts(new ContactsData("email", "name", null, "test1"), true);
   }
}
