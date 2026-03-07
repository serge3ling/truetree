package tk.d4097.truetree.keep.ask.likeness;

import tk.d4097.truetree.keep.Lst;

public class PathHas<T extends Lst> implements Likeness<T> {
  private final String pathSnip;

  public PathHas(String pathSnip) {
    this.pathSnip = pathSnip;
  }

  @Override
  public boolean isGood(T lst) {
    return lst.getPath().contains(pathSnip);
  }
}
