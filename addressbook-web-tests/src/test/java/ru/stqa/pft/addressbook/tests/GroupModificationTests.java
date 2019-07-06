package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.getNavigationHelper().goToGroupPage();
      if (!app.getGroupHelper().isThereGroup()) {
         app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
   }

   @Test
   public void TestGroupModidfication() {
      List<GroupData> before = app.getGroupHelper().getGroupList();
      int index = before.size() - 1;
      GroupData group = new GroupData(before.get(index).getId(), "test5", "test2", "test3");
      app.getGroupHelper().modifyGroup(index, group);
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size());
      before.remove(index);
      before.add(group);
      app.getGroupHelper().comperatorSortGroupData(before, after);
      Assert.assertEquals(before, after);
   }


}
