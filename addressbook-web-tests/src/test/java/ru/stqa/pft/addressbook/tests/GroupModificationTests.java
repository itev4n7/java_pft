package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

   @Test
   public void TestGroupModidfication() {
      app.getNavigationHelper().goToGroupPage();
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().initGroupModification();
      app.getGroupHelper().fillCroupForm(new GroupData("test1", "test2", "test3"));
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnToGroupPage();
   }
}
