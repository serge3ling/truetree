package tk.d4097.truetree.keep;

import java.io.File;
import java.util.function.Consumer;

public class TreeWalkConsumer implements Consumer<File> {
  private final Keep keep;

  public TreeWalkConsumer(Keep keep) {
    this.keep = keep;
  }

  @Override
  public void accept(File file) {
    Lst lst = new Lst(file.getPath());
    keep.addLst(lst);
  }
}
