package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsPhoneTests extends TestBase {

   @Test
   public void testContactsPhone() {
      app.goTo().homePage();
      ContactsData contacts = app.contacts().all().iterator().next();
      ContactsData contactsInfoFromEditForm = app.contacts().infoFromEditForm(contacts);
      assertThat(contacts.getHomePhone(), equalTo(cleaned(contactsInfoFromEditForm.getHomePhone())));
      assertThat(contacts.getMobilePhone(), equalTo(cleaned(contactsInfoFromEditForm.getMobilePhone())));
      assertThat(contacts.getWorkPhone(), equalTo(cleaned(contactsInfoFromEditForm.getWorkPhone())));
   }

   private String cleaned(String phone) {
      return phone.replaceAll("\\s|\\(|\\)|\\-", "");
   }
}
