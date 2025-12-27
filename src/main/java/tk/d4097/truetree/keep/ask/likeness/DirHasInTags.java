package tk.d4097.truetree.keep.ask.likeness;

import tk.d4097.truetree.keep.Lst;

public class DirHasInTags<T extends Lst> implements Likeness<T> {
  private final String tagSnip;
  private final DirTagsProp dirTagsProp = new DirTagsProp();

  public DirHasInTags(String tagSnip) {
    this.tagSnip = tagSnip.toLowerCase();
  }

  @Override
  public boolean isGood(T lst) {
    String tagsPropName = dirTagsProp.name();

    if (lst.hasProp(tagsPropName)) {
      try {
        return lst.getProp(tagsPropName).toLowerCase().contains(tagSnip);
      } catch (Exception ignored) {
      }
    }

    return false;
  }
}
