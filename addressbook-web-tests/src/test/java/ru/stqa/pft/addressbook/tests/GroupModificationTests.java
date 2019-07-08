package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
         app.group().create(new GroupData("test1", null, null));
      }
   }

   @Test
   public void TestGroupModidfication() {
      Set<GroupData> before = app.group().all();
      GroupData modifyGrouop = before.iterator().next();
      GroupData group = new GroupData(modifyGrouop.getId(), "test5", "test2", "test3");
      app.group().modify(group);
      Set<GroupData> after = app.group().all();
      Assert.assertEquals(after.size(), before.size());
      before.remove(modifyGrouop);
      before.add(group);
      Assert.assertEquals(before, after);
   }


}
