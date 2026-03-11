package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;
import tk.d4097.truetree.keep.ask.likeness.*;

import java.util.List;
import java.util.function.Function;

public class StringKeyValArgDirLikenessMap {
  private final StringKeyValArgLikenessMapBag<Lst> mapBag;

  public StringKeyValArgDirLikenessMap() {
    mapBag = new StringKeyValArgLikenessMapBag<>("dir");
    mapBag.put("dir-is", DirIs::new, "=<dir>, where <dir> can be either name (\"list\" param) or path");
    mapBag.put("dir-has-tag", DirHasTag::new, "=<tag>, where <tag> is listed in \"tags\" param");
    mapBag.put("dir-has-in-tags", DirHasTag::new, "=<snip>, where <snip> is in \"tags\" param");
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
