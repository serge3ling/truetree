package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class StringKeyValArgLikenessMapBagTest {

  @Test
  void hints_whenAdd_thenGet() {
    Function<String, Likeness<String>> fun = new Function<String, Likeness<String>>() {
      @Override
      public Likeness<String> apply(String s) {
        return new Likeness<String>() {
          @Override
          public boolean isGood(String s) {
            return false;
          }
        };
      }
    };

    String commonHint = "=<val>, ";
    String k1 = "z1";
    String k2 = "m2";
    String k3 = "z3";
    String v1 = commonHint + k1;
    String v2 = commonHint + k2;
    String v3 = commonHint + k3;
    StringKeyValArgLikenessMapBag<String> bag = new StringKeyValArgLikenessMapBag<>("kind");
    bag.put(k1, fun, v1);
    bag.put(k2, fun, v2);
    bag.put(k3, fun, v3);

    List<String> hints = bag.hints();

    assert (hints.size() == 3);
    assert hints.contains(k1 + v1);
    assert hints.contains(k2 + v2);
    assert hints.contains(k3 + v3);
  }
}