package tk.d4097.truetree.keep;

import java.io.File;
import java.util.function.Predicate;

public class TreeWalkPredicate implements Predicate<File> {
  @Override
  public boolean test(File file) {
    String filename = file.getName();
    return filename.matches(".*\\Q~t-lst-\\E.*\\Q.yml\\E$");
  }
}
