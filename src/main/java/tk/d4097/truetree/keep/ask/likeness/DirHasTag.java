package tk.d4097.truetree.keep.ask.likeness;

import tk.d4097.truetree.keep.Lst;

public class DirHasTag<T extends Lst> implements Likeness<T> {
  private final String tag;
  private final DirTagsProp dirTagsProp = new DirTagsProp();
  private final DirTagsSeparator dirTagsSeparator = new DirTagsSeparator();

  public DirHasTag(String tag) {
    this.tag = tag.toLowerCase();
  }

  @Override
  public boolean isGood(T lst) {
    String tagsPropName = dirTagsProp.name();

    if (lst.hasProp(tagsPropName)) {
      String tags = "";

      try {
        tags = lst.getProp(tagsPropName).toLowerCase();
      } catch (Exception ignored) {
      }

      String[] split = tags.split(dirTagsSeparator.value());

      for (String s : split) {
        if (s.trim().toLowerCase().equals(this.tag)) {
          return true;
        }
      }
    }

    return false;
  }
}
