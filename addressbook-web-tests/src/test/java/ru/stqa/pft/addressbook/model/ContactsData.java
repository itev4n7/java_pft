package ru.stqa.pft.addressbook.model;

public class ContactsData {

   private final String email;
   private final String name;
   private final String lastName;
   private final String group;

   public ContactsData(String email, String name, String lastName, String group) {
      this.email = email;
      this.name = name;
      this.lastName = lastName;
      this.group = group;
   }

   public String getEmail() {
      return email;
   }

   public String getName() {
      return name;
   }

   public String getLastName() {
      return lastName;
   }

   public String getGroup() {
      return group;
   }
}
