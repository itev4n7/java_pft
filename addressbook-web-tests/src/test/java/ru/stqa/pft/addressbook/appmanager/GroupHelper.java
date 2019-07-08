package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;

public class GroupHelper extends HelperBase {

   public GroupHelper(WebDriver driver) {
      super(driver);
   }

   public void returnToGroupPage() {
      click(By.linkText("group page"));
   }

   public void submitGroupCreation() {
      click(By.name("submit"));
   }

   public void fillCroupForm(GroupData groupData) {
      type(By.name("group_name"), groupData.getName());
      type(By.name("group_header"), groupData.getHeader());
      type(By.name("group_footer"), groupData.getFooter());
   }

   public void initGroupCreation() {
      click(By.name("new"));
   }

   public void deleteSelectedGroups() {
      click(By.name("delete"));
   }

   public void initGroupModification() {
      click(By.name("edit"));
   }

   public void submitGroupModification() {
      click(By.name("update"));
   }

   public void create(GroupData groupData) {
      initGroupCreation();
      fillCroupForm(groupData);
      submitGroupCreation();
      groupCache = null;
      returnToGroupPage();
   }

   public void modify(GroupData group) {
      selectGroupById(group.getId());
      initGroupModification();
      fillCroupForm(group);
      submitGroupModification();
      groupCache = null;
      returnToGroupPage();
   }

   public void delete(GroupData group) {
      selectGroupById(group.getId());
      deleteSelectedGroups();
      groupCache = null;
      returnToGroupPage();
   }

   private void selectGroupById(int id) {
      driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
   }

   public void comperatorSortGroupData(List<GroupData> before, List<GroupData> after) {
      Comparator<? super GroupData> byId = Comparator.comparing(GroupData::getId);
      before.sort(byId);
      after.sort(byId);
   }

   public boolean isThereAGroup() {
      return isElementPresent(By.name("selected[]"));
   }

   public int count() {
      return driver.findElements(By.name("selected[]")).size();
   }

   private Groups groupCache = null;

   public Groups all() {
      if (groupCache != null) {
         return new Groups(groupCache);
      }
      groupCache = new Groups();
      List<WebElement> elements = driver.findElements(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Groups'])[1]/following::form[1]"));
      String[] arrNameGroups = elements.get(0).getText().split("\n");
      elements = driver.findElements(By.cssSelector("input"));
      String[] arrId = new String[arrNameGroups.length];
      for (int i = 0, j = 0; i < elements.size(); i++) {
         if (isInteger(elements.get(i).getAttribute("value"))) {
            arrId[j++] = elements.get(i).getAttribute("value");
         }
      }
      if (arrId[0] != (null)) {
         for (int i = 0; i < arrNameGroups.length; i++) {
            groupCache.add(new GroupData().withId(Integer.parseInt(arrId[i])).withName(arrNameGroups[i]));
         }
      }
      return new Groups(groupCache);
   }

   private boolean isInteger(String value) {
      try {
         int temp = Integer.parseInt(value);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

}

