package tk.d4097.truetree.text;

public interface StringAccept {
  boolean accept(String s);
  String remake(String s);

  class Stub implements StringAccept {
    private final String toStartWith;
    private final int lengthAtLeast;
    private final boolean toAccept;

    public Stub(String toStartWith) {
      this(toStartWith, 0, true);
    }

    public Stub(String toStartWith, int lengthAtLeast) {
      this(toStartWith, lengthAtLeast, true);
    }

    public Stub(String toStartWith, int lengthAtLeast, boolean toAccept) {
      this.toStartWith = toStartWith;
      this.lengthAtLeast = lengthAtLeast;
      this.toAccept = toAccept;
    }

    @Override
    public boolean accept(String s) {
      boolean straight = s.startsWith(toStartWith) && (s.length() > lengthAtLeast);
      return (toAccept == straight);
    }

    @Override
    public String remake(String s) {
      return s.substring(toStartWith.length());
    }
  }
}
