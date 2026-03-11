package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.ask.likeness.Likeness;
import tk.d4097.truetree.keep.ask.likeness.PathHas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class StringKeyValArgPathLikenessMap {
  private final StringKeyValArgLikenessMapBag<Lst> mapBag;

  public StringKeyValArgPathLikenessMap() {
    mapBag = new StringKeyValArgLikenessMapBag<>("path");
    mapBag.put("path-has", PathHas::new, "=<snip>, where <snip> is a snippet of path string");
  }

  public boolean has(String key) {
    return mapBag.has(key);
  }

  public Function<String, Likeness<Lst>> get(String key) {
    return mapBag.get(key);
  }

  public List<String> hints() {
    return mapBag.hints();
  }
}
