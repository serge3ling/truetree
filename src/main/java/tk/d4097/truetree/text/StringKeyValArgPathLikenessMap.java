package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.ask.likeness.Likeness;
import tk.d4097.truetree.keep.ask.likeness.PathHas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StringKeyValArgPathLikenessMap {
  private final Map<String, Function<String, Likeness<Lst>>> map = new HashMap<>();

  public StringKeyValArgPathLikenessMap() {
    map.put("path-has", PathHas::new);
  }

  public boolean has(String key) {
    return map.containsKey(key);
  }

  public Function<String, Likeness<Lst>> get(String key) {
    if (!this.has(key)) {
      throw new RuntimeException("Map of dir likenesses has no key \"" + key + "\"");
    }

    return map.get(key);
  }
}
