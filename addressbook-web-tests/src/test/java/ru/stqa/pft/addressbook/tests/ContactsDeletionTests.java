package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.contacts().all().size() == 0) {
         app.contacts().create(new ContactsData().setEmail("email").setName("name").setLastName("last")
                 .setGroup("test3").setHomePhone("111").setMobilePhone("222").setWorkPhone("333"), true);
      }
   }

   @Test
   public void testContactsDeletion() {
      Contacts before = app.contacts().all();
      ContactsData deletedContacts = before.iterator().next();
      app.contacts().delete();
      assertThat(app.contacts().count(), equalTo(before.size() - 1));
      Contacts after = app.contacts().all();
      assertThat(after, equalTo(before.withOut(deletedContacts)));
   }
}
