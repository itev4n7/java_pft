package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationTests extends TestBase {
   @Test
   public void testContactSCreation() {
      app.goTo().homePage();
      Contacts before = app.contacts().all();
      ContactsData contacts = new ContactsData().setEmail("email").setName("name").setLastName("last")
              .setGroup("test3").setHomePhone("111").setMobilePhone("222").setWorkPhone("333");
      app.contacts().create(contacts, true);
      assertThat(app.contacts().count(), equalTo(before.size() + 1));
      Contacts after = app.contacts().all();
      assertThat(after, equalTo(before.withAdded(contacts.setId(after.stream().mapToInt(ContactsData::getId).max().getAsInt()))));
   }
}
