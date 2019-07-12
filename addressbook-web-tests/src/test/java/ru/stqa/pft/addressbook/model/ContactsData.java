package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactsData {
   private int id = Integer.MAX_VALUE;
   private String email;
   private String name;
   private String lastName;
   private String group;
   private String homePhone;
   private String mobilePhone;
   private String workPhone;

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


   public ContactsData setMobilePhone(String mobilePhone) {
      this.mobilePhone = mobilePhone;
      return this;
   }

   public ContactsData setHomePhone(String homePhone) {
      this.homePhone = homePhone;
      return this;
   }

   public ContactsData setWorkPhone(String workPhone) {
      this.workPhone = workPhone;
      return this;
   }

   public ContactsData setGroup(String group) {
      this.group = group;
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactsData that = (ContactsData) o;
      return id == that.id &&
              Objects.equals(email, that.email) &&
              Objects.equals(name, that.name) &&
              Objects.equals(lastName, that.lastName) &&
              Objects.equals(homePhone, that.homePhone) &&
              Objects.equals(mobilePhone, that.mobilePhone) &&
              Objects.equals(workPhone, that.workPhone);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, email, name, lastName, homePhone, mobilePhone, workPhone);
   }

   @Override
   public String toString() {
      return "ContactsData{" +
              "id=" + id +
              ", email='" + email + '\'' +
              ", name='" + name + '\'' +
              ", lastName='" + lastName + '\'' +
              ", group='" + group + '\'' +
              ", homePhone='" + homePhone + '\'' +
              ", mobilePhone='" + mobilePhone + '\'' +
              ", workPhone='" + workPhone + '\'' +
              '}';
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

   public int getId() {
      return id;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getWorkPhone() {
      return workPhone;
   }
}
