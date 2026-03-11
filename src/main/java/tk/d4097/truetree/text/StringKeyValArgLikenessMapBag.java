package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.ask.likeness.Likeness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StringKeyValArgLikenessMapBag<T> {
  private final String kind;
  private final Map<String, Function<String, Likeness<T>>> map = new HashMap<>();
  private final Map<String, String> hintMap = new HashMap<>();

  public StringKeyValArgLikenessMapBag(String kind) {
    this.kind = kind;
  }

  public void put(String key, Function<String, Likeness<T>> likenessHandle, String hint) {
    map.put(key, likenessHandle);
    hintMap.put(key, hint);
  }

  public boolean has(String key) {
    return map.containsKey(key);
  }

  public Function<String, Likeness<T>> get(String key) {
    if (!this.has(key)) {
      throw new RuntimeException("Map of " + kind + " likenesses has no key \"" + key + "\"");
    }

    return map.get(key);
  }

  public List<String> hints() {
    List<String> hints = new ArrayList<>();

    for (String key : hintMap.keySet()) {
      hints.add(key + hintMap.get(key));
    }

    return hints;
  }
}
