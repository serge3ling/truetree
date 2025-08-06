package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

class LstItemStartWithFieldAcceptTest {
  @Test
  void accept_whenStartOkButWoTail_thenFalse() {
    String s = "  - ";
    StringAccept acceptor = new LstItemStartWithFieldAccept();
    assert !acceptor.accept(s);
  }

  @Test
  void accept_whenMatch_thenTrue() {
    String s = "  - k: v";
    StringAccept acceptor = new LstItemStartWithFieldAccept();
    assert acceptor.accept(s);
  }

  @Test
  void accept_whenMismatchBefore_thenFalse() {
    String s = " m - k2: v2";
    StringAccept acceptor = new LstItemStartWithFieldAccept();
    assert !acceptor.accept(s);
  }

  @Test
  void accept_whenEmpty_thenFalse() {
    String s = "";
    StringAccept acceptor = new LstItemStartWithFieldAccept();
    assert !acceptor.accept(s);
  }
}