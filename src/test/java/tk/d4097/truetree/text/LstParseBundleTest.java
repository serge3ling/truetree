package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LstParseBundleTest {
  @Test
  void construct_whenEmptyArgs_thenStringAcceptorsInitialized() {
    Txt txt = new Txt.Stub(new ArrayList<>());
    LstParseBundle bundle = new LstParseBundle(txt, "test-path");
    assert bundle.getStringAcceptors() != null;
  }
}