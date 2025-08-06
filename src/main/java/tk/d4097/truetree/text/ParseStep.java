package tk.d4097.truetree.text;

abstract public class ParseStep implements LineParse {
  protected final LstParseBundle bundle;

  public ParseStep(LstParseBundle bundle) {
    this.bundle = bundle;
  }
}
