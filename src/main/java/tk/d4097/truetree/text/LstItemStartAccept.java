package tk.d4097.truetree.text;

public class LstItemStartAccept implements StringAccept {
  @Override
  public boolean accept(String s) {
    return s.startsWith(start()) && s.trim().equals("-");
  }

  public String start() {
    return "  -";
  }

  @Override
  public String remake(String s) {
    return "";
  }
}
