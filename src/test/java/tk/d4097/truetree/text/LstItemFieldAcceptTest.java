package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

class LstItemFieldAcceptTest {
  @Test
  void accept_whenStartsAsItemAndHasField_thenTrue() {
    String s = "  - key: val";
    StringAccept acceptor = new LstItemFieldAccept();
    assert acceptor.accept(s);
  }

  @Test
  void accept_whenStartsAsFieldAndHasField_thenTrue() {
    String s = "    key: val";
    StringAccept acceptor = new LstItemFieldAccept();
    assert acceptor.accept(s);
  }

  @Test
  void accept_whenStartsAsFieldButHasWrongSpaces_thenFalse() {
    String s = "       key: val";
    StringAccept acceptor = new LstItemFieldAccept();
    assert !acceptor.accept(s);
  }
}