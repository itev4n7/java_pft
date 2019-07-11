package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactsData {
   private int id = Integer.MAX_VALUE;
   private String email;
   private String name;
   private String lastName;
   private String group;


   public ContactsData setId(int id) {
      this.id = id;
      return this;
   }

   public ContactsData setEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactsData setName(String name) {
      this.name = name;
      return this;
   }

   public ContactsData setLastName(String lastName) {
      this.lastName = lastName;
      return this;
   }

   public ContactsData setGroup(String group) {
      this.group = group;
      return this;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactsData that = (ContactsData) o;
      return id == that.id &&
              Objects.equals(email, that.email) &&
              Objects.equals(name, that.name) &&
              Objects.equals(lastName, that.lastName);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, email, name, lastName);
   }

   @Override
   public String toString() {
      return "ContactsData{" +
              "id=" + id +
              ", email='" + email + '\'' +
              ", name='" + name + '\'' +
              ", lastName='" + lastName + '\'' +
              ", group='" + group + '\'' +
              '}';
   }

   public String getGroup() {
      return group;
   }

   public int getId() {
      return id;
   }
}
