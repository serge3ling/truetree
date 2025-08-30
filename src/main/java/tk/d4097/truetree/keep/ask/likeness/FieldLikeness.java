package tk.d4097.truetree.keep.ask.likeness;

import tk.d4097.truetree.keep.Rec;

abstract public class FieldLikeness implements Likeness {
  protected final String fld;
  protected final String val;

  public FieldLikeness(String fld, String val) {
    this.fld = fld;
    this.val = val;
  }

  @Override
  public boolean isGood(Rec rec) {
    if (rec.hasField(fld)) {
      try {
        return isGoodValue(rec.get(fld));
      } catch (Exception ignored) {
      }
    }

    return false;
  }

  abstract public boolean isGoodValue(String fldGot);
}
