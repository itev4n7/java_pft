package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

   @Test
   public void TestGroupModidfication() {
      app.getNavigationHelper().goToGroupPage();
      if (!app.getGroupHelper().isThereGroup()) {
         app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
      }
      List<GroupData> before = app.getGroupHelper().getGroupList();
      app.getGroupHelper().selectGroup(before.size() - 1);
      app.getGroupHelper().initGroupModification();
      app.getGroupHelper().fillCroupForm(new GroupData("test1", "test2", "test3"));
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size());

   }
}
