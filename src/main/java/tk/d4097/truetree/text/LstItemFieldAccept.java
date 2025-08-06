package tk.d4097.truetree.text;

public class LstItemFieldAccept implements StringAccept {
  private final StringAccept bareField;

  public LstItemFieldAccept() {
    this(new BareFieldAccept());
  }

  public LstItemFieldAccept(StringAccept bareFieldAcceptor) {
    this.bareField = bareFieldAcceptor;
  }

  @Override
  public boolean accept(String s) {
    boolean found = false;
    String cut = s;

    if (isItemStart(s)) {
      found = true;
      cut = trimItemStart(s);
    }

    if (!found && isFieldStart(s)) {
      found = true;
      cut = trimFieldStart(s);
    }

    return found && bareField.accept(cut);
  }

  public String itemStart() {
    return "  - ";
  }

  public boolean isItemStart(String s) {
    return s.startsWith(itemStart());
  }

  public String trimItemStart(String s) {
    return s.substring(itemStart().length());
  }

  public String fieldStart() {
    return "    ";
  }

  public boolean isFieldStart(String s) {
    return s.startsWith(fieldStart());
  }

  public String trimFieldStart(String s) {
    return s.substring(fieldStart().length());
  }

  @Override
  public String remake(String s) {
    if (isItemStart(s)) {
      return trimItemStart(s);
    }

    if (isFieldStart(s)) {
      return trimFieldStart(s);
    }

    return s.trim();
  }
}
