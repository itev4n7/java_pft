package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

   @Test
   public void testGroupCreation() throws Exception {
      app.goTo().groupPage();
      List<GroupData> before = app.group().list();
      GroupData group = new GroupData("test4", null, null);
      app.group().create(group);
      List<GroupData> after = app.group().list();
      Assert.assertEquals(after.size(), before.size() + 1);
      group.setIdMax(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
      before.add(group);
      app.group().comperatorSortGroupData(before, after);
      Assert.assertEquals(before, after);
   }
}
