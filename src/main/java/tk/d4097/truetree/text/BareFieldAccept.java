package tk.d4097.truetree.text;

public class BareFieldAccept implements StringAccept {
  @Override
  public boolean accept(String s) {
    return !s.startsWith(" ") && s.trim().contains(splitter());
  }

  public String splitter() {
    return ": ";
  }

  @Override
  public String remake(String s) {
    return s.trim();
  }
}
