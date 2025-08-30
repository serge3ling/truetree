package tk.d4097.truetree.keep.ask.likeness;

import tk.d4097.truetree.keep.Rec;

abstract public class FieldLikeness<T extends CanHaveField> implements Likeness<T> {
  protected final String fld;
  protected final String val;

  public FieldLikeness(String fld, String val) {
    this.fld = fld;
    this.val = val;
  }

  @Override
  public boolean isGood(T chf) {
    if (chf.hasField(fld)) {
      try {
        return isGoodValue(chf.get(fld));
      } catch (Exception ignored) {
      }
    }

    return false;
  }

  abstract public boolean isGoodValue(String fldGot);
}
