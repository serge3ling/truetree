package tk.d4097.truetree.keep;

import tk.d4097.truetree.text.FileLst;
import tk.d4097.truetree.text.Txt;
import tk.d4097.truetree.text.TxtToLst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

public class TreeWalkConsumer implements Consumer<File> {
  private final Keep keep;

  public TreeWalkConsumer(Keep keep) {
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
