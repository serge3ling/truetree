package tk.d4097.truetree.keep.ask.likeness;

public class Has<T extends CanHaveField> extends FieldLikeness<T> {
  public Has(String fld, String val) {
    super(fld, val);
  }

  @Override
  public boolean isGoodValue(String fldGot) {
    return fldGot.contains(val);
  }
}
