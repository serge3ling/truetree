package tk.d4097.truetree;

import tk.d4097.truetree.keep.DataKeep;

import java.io.File;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TreeWalk {
  private final File topDir;
  private final Predicate<File> predicate;
  private final Consumer<File> consumer;

  public TreeWalk(File topDir, Predicate<File> predicate,
                  Consumer<File> consumer
  ) {
    this.topDir = topDir;
    this.predicate = predicate;
    this.consumer = consumer;
  }

  public void walk() {
    walk(topDir);
  }

  private void walk(File dir) {
    for (File file : dir.listFiles()) {
      if (file.isDirectory()) {
        walk(file);
      } else {
        if (predicate.test(file)) {
          consumer.accept(file);
        }
      }
    }
  }
}
