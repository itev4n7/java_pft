package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() throws Exception {
      app.getNavigationHelper().goToGroupPage();
      List<GroupData> before = app.getGroupHelper().getGroupList();
      GroupData group = new GroupData("test4", null, null);
      app.getGroupHelper().createGroup(group);
      List<GroupData> after = app.getGroupHelper().getGroupList();
      Assert.assertEquals(after.size(), before.size() + 1);
      group.setIdMax(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
      before.add(group);
      app.getGroupHelper().comperatorSortGroupData(before, after);
      Assert.assertEquals(before, after);
   }
}
