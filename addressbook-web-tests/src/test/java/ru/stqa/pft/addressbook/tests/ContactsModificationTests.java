package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsModificationTests extends TestBase {
   @Test
   public void testContactsModification() {
      app.getNavigationHelper().goToHomePage();
      app.getContactsHelper().initContactModification();
      app.getContactsHelper().fillContactsForm(new ContactsData("email", "name", "lastName", null), false);
      app.getContactsHelper().submitContactModification();
      app.getContactsHelper().returnToHomePage();

   }
}
