package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Rec;
import tk.d4097.truetree.keep.ask.likeness.Has;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class StringKeyValArgLikenessMap {
  private final Map<String, BiFunction<String, String, Likeness<Rec>>> map = new HashMap<>();

  public StringKeyValArgLikenessMap() {
    map.put("has", Has::new);
  }

  public boolean has(String key) {
    return map.containsKey(key);
  }

  public BiFunction<String, String, Likeness<Rec>> get(String key) {
    if (!this.has(key)) {
      throw new RuntimeException("Map of likenesses has no key \"" + key + "\"");
    }

    return map.get(key);
  }
}
