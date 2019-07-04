package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
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

   public void selectGroup(int index) {
      driver.findElements(By.name("selected[]")).get(index).click();
   }

   public void initGroupModification() {
      click(By.name("edit"));
   }

   public void submitGroupModification() {
      click(By.name("update"));
   }

   public void createGroup(GroupData groupData) {
      initGroupCreation();
      fillCroupForm(groupData);
      submitGroupCreation();
      returnToGroupPage();
   }

   public boolean isThereGroup() {
      return isElementPresent(By.name("selected[]"));
   }

   public int getGroupCount() {
      return driver.findElements(By.name("selected[]")).size();
   }

   public List<GroupData> getGroupList() {
      List<GroupData> groups = new ArrayList<>();
      List<WebElement> elements = driver.findElements(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Groups'])[1]/following::form[1]"));
      String[] arrNameGroups = elements.get(0).getText().split("\n");
      elements = driver.findElements(By.cssSelector("input"));
      String[] arrId = new String[arrNameGroups.length];
      for (int i = 0, j = 0; i < elements.size(); i++) {
         if (isInteger(elements.get(i).getAttribute("value"))) {
            arrId[j++] = elements.get(i).getAttribute("value");
         }
      }
      for (int i = 0; i < arrNameGroups.length; i++) {
         GroupData group = new GroupData(Integer.parseInt(arrId[i]), arrNameGroups[i], null, null);
         groups.add(group);
      }
      return groups;
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

