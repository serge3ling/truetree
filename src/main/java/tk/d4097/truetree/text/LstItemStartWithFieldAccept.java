package tk.d4097.truetree.text;

public class LstItemStartWithFieldAccept implements StringAccept {
  @Override
  public boolean accept(String s) {
    return s.startsWith(start()) && (s.trim().length() > start().length());
  }

  @Override
  public String remake(String s) {
    return s.substring(start().length()).trim();
  }

  public String start() {
    return "  - ";
  }
}
