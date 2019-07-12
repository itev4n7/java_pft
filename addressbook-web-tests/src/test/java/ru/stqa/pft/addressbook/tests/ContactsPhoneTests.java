package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactsData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsPhoneTests extends TestBase {

   @Test
   public void testContactsPhone() {
      app.goTo().homePage();
      ContactsData contacts = app.contacts().all().iterator().next();
      ContactsData contactsInfoFromEditForm = app.contacts().infoFromEditForm(contacts);

      assertThat(contacts.getAllPhones(), equalTo(mergePhones(contactsInfoFromEditForm)));
   }

   private String mergePhones(ContactsData contact) {
      return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .stream().filter((s) -> !s.equals(""))
              .map(ContactsPhoneTests::cleaned)
              .collect(Collectors.joining("\n"));
   }

   public static String cleaned(String phone) {
      return phone.replaceAll("\\s|\\(|\\)|\\-", "");
   }
}
