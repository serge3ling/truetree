package tk.d4097.truetree.keep;

import org.junit.jupiter.api.Test;

import java.util.Set;

public class LstTest {
  @Test
  void lay_whenThereWereVals_thenUpdated() throws Exception {
    Lst lst0 = new Lst("test-path-0");
    Lst lstB = new Lst("test-path-b");

    lst0.putProp(lst0.nameName(), "lst");
    lstB.putProp(lstB.nameName(), "lst");

    Rec rec0 = new Rec();
    rec0.put(rec0.idName(), "rec-id");
    rec0.put("k", "v-a");
    lst0.addRec(rec0);
    lst0.close();

    Rec recB = new Rec();
    recB.put(recB.idName(), "rec-id");
    recB.put("k", "v-b");
    lstB.addRec(recB);
    lstB.close();

    lst0.lay(lstB);

    Set<LoadLogLayerIdWField> idWField2PathKeys = lst0.getRecLoadLog().idWField2PathKeys();
    for (LoadLogLayerIdWField key : idWField2PathKeys) {
      System.out.println(key + " : " + lst0.getRecLoadLog().getPathByIdWField(key));
    }

    Set<String> id2PathKeys = lst0.getRecLoadLog().id2PathKeys();
    for (String key : id2PathKeys) {
      System.out.println(key + " : " + lst0.getRecLoadLog().getPathById(key));
    }

    Set<String> path2IdKeys = lst0.getRecLoadLog().path2IdKeys();
    for (String key : path2IdKeys) {
      System.out.println(key + " : " + lst0.getRecLoadLog().getIdByPath(key));
    }
  }
}
