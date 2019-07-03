package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsCreationTests extends TestBase {
   @Test
   public void testContactSCreation() {
      app.getNavigationHelper().goToHomePage();
      app.getContactsHelper().initContactCreation();
      app.getContactsHelper().next();
      app.getContactsHelper().fillContactsForm(new ContactsData("email", "name", null, "test1"), true);
      app.getContactsHelper().submitContactCreation();
      app.getContactsHelper().returnToHomePage();
   }
}
