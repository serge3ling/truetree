package tk.d4097.truetree.keep;

import tk.d4097.truetree.text.StraightYmlTxt;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class TreeWalkDirPropConsumer implements Consumer<File> {
  private final Keep keep;

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
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
