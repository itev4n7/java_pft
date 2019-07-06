package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

public class ContactsCreationTests extends TestBase {
   @Test
   public void testContactSCreation() {
      app.goTo().homePage();
      app.contacts().createContacts(new ContactsData("email", "name", null, "test1"), true);
      app.goTo().homePage();
   }
}
