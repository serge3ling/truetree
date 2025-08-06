package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

class LstItemStartAcceptTest {
  @Test
  void accept_whenMatchAndSpaces_thenTrue() {
    String s = "  -   ";
    StringAccept acceptor = new LstItemStartAccept();
    assert acceptor.accept(s);
  }

  @Test
  void accept_whenExactMatch_thenTrue() {
    String s = "  -";
    StringAccept acceptor = new LstItemStartAccept();
    assert acceptor.accept(s);
  }

  @Test
  void accept_whenMismatchTailed_thenFalse() {
    String s = "  - key: val";
    StringAccept acceptor = new LstItemStartAccept();
    assert !acceptor.accept(s);
  }

  @Test
  void accept_whenMismatchBefore_thenFalse() {
    String s = " m -";
    StringAccept acceptor = new LstItemStartAccept();
    assert !acceptor.accept(s);
  }

  @Test
  void accept_whenEmpty_thenFalse() {
    String s = "";
    StringAccept acceptor = new LstItemStartAccept();
    assert !acceptor.accept(s);
  }
}