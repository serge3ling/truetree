package tk.d4097.truetree.keep;

import tk.d4097.truetree.text.StraightYmlTxt;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;

public class TreeWalkDirPropConsumer implements Consumer<File> {
  private final Keep keep;
  private final String subDirListProp = "sub-dir-list";

  public TreeWalkDirPropConsumer(Keep keep) {
    this.keep = keep;
  }

  @Override
  public void accept(File file) {
    try {
      String path = file.getPath();
      StraightYmlTxt straightYml = new StraightYmlTxt(path);
      Set<Map.Entry<String, String>> propEntries = straightYml.propEntries();
      Rec rec = new Rec();
      rec.put(rec.idName(), path);

      for (Map.Entry<String, String> entry : propEntries) {
        rec.put(entry.getKey(), entry.getValue());
      }

      keep.addDirPropRec(rec);

      if (rec.hasField(subDirListProp)) {
        makeSubDirLst(file, rec);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void makeSubDirLst(File file, Rec rec) throws Exception {
    String listId = rec.get(subDirListProp);
    String subDirsNotInListProp = "sub-dirs-not-in-list";

    List<String> subDirsNotInList = rec.hasField(subDirsNotInListProp)
        ? Arrays.asList(rec.get(subDirsNotInListProp).split(":"))
        : new ArrayList<>();

    var files = file.getParentFile().listFiles(f ->
        f.isDirectory() && !subDirsNotInList.contains(f.getName()));

    if ((files == null) || (files.length == 0)) {
      return;
    }

    Lst lst = new Lst(file.getPath(), listId);

    for (File f : files) {
      Rec subDirLstRec = new Rec();
      subDirLstRec.put(subDirLstRec.idName(), f.getName());
      lst.addRec(subDirLstRec);
    }

    keep.addLst(lst);
  }
}
