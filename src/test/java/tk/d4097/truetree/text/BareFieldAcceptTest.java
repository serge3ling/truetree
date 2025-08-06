package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BareFieldAcceptTest {
  @Test
  void accept_whenStartsWithSpace_thenFalse() {
    BareFieldAccept accept = new BareFieldAccept();
    assert !accept.accept(" k: v");
  }

  @Test
  void accept_whenNoSplitter_thenFalse() {
    BareFieldAccept accept = new BareFieldAccept();
    assert !accept.accept("k:v");
  }

  @Test
  void accept_whenHasSplitterStartsWithNoSpace_thenTrue() {
    BareFieldAccept accept = new BareFieldAccept();
    assert accept.accept("k: v");
  }
}