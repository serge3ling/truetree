package tk.d4097.truetree.keep;

public class LoadLogLayerIdWField {
  private final String id;
  private final String fld;

  public LoadLogLayerIdWField(String id, String fld) {
    this.id = id;
    this.fld = fld;
  }

  @Override
  public int hashCode() {
    return 37 * this.id.hashCode() + this.fld.hashCode();
  }

  @Override
  public boolean equals(Object thatObj) {
    if (!(thatObj instanceof LoadLogLayerIdWField that)) {
      return false;
    }

    return this.id.equals(that.id);
  }

  @Override
  public String toString() {
    return "id=\"" + id + "\", fld=\"" + fld + "\"";
  }
}
