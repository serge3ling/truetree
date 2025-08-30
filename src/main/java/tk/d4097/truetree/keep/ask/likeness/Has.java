package tk.d4097.truetree.keep.ask.likeness;

public class Has extends FieldLikeness {
  public Has(String fld, String val) {
    super(fld, val);
  }

  @Override
  public boolean isGoodValue(String fldGot) {
    return fldGot.contains(val);
  }
}
