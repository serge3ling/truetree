package tk.d4097.truetree.keep.ask.likeness;

public interface CanHaveField {
  boolean hasField(String fld);
  String get(String fld) throws Exception;
}
