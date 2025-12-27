package tk.d4097.truetree.keep.ask.likeness;

import tk.d4097.truetree.keep.Lst;

import java.io.File;

public class DirIs<T extends Lst> implements Likeness<T> {
  private final String name;

  public DirIs(String name) {
    this.name = name;
  }

  @Override
  public boolean isGood(T lst) {
    String path = lst.getPath();
    int nd = path.lastIndexOf(File.separator);
    String upperPath = (nd < 0) ? path : path.substring(0, nd);

    if (name.equals(upperPath) || upperPath.endsWith(File.separator + name)) {
      return true;
    }

    if (lst.hasName()) {
      try {
        return lst.name().equals(name);
      } catch (Exception ignored) {
      }
    }

    return false;
  }
}
