package tk.d4097.truetree.text;

public interface StringAcceptRemake {
  boolean accept();
  String remadeStr();

  class Stub implements StringAcceptRemake {
    private final boolean acceptRtn;
    private final String remadeStrRtn;

    public Stub(boolean acceptRtn, String remadeStrRtn) {
      this.acceptRtn = acceptRtn;
      this.remadeStrRtn = remadeStrRtn;
    }

    @Override
    public boolean accept() {
      return acceptRtn;
    }

    @Override
    public String remadeStr() {
      return remadeStrRtn;
    }
  }
}
