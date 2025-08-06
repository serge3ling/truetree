package tk.d4097.truetree;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class AnyTest {
  @Test
  void iteratorNextWhereNoNext() {
    java.util.List<String> emptyList = new java.util.ArrayList<>();
    Iterator<String> iterator = emptyList.iterator();
    try {
      iterator.next();
    } catch (Exception e) {
      System.err.println("Exception message: " + e.getLocalizedMessage());
    }
  }
}
