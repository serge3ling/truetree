package tk.d4097.truetree.keep;

import tk.d4097.truetree.text.FileLst;

import java.io.File;
import java.util.function.Consumer;

public class TreeWalkLstConsumer implements Consumer<File> {
  private final Keep keep;

  public TreeWalkLstConsumer(Keep keep) {
    this.keep = keep;
  }

  @Override
  public void accept(File file) {
    try {
      FileLst fileLst = new FileLst(file.getPath());
      keep.addLst(fileLst.lst());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
