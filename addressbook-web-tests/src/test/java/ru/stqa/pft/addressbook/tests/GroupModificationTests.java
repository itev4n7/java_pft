package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

   @Test
   public void TestGroupModidfication() {
      app.getNavigationHelper().goToGroupPage();
      if (!app.getGroupHelper().isThereGroup()) {
         app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
      List<GroupData> before = app.getGroupHelper().getGroupList();
      int index = before.size() - 1;
      app.getGroupHelper().selectGroup(index);
      app.getGroupHelper().initGroupModification();
      GroupData group = new GroupData(before.get(index).getId(), "test1", "test2", "test3");
      app.getGroupHelper().fillCroupForm(group);
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size());
      before.remove(index);
      before.add(group);
      Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
   }
}
