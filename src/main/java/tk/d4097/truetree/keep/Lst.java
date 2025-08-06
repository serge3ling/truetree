package tk.d4097.truetree.keep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lst {
  private final Map<String, String> props = new HashMap<>();
  private final Map<String, Rec> recs = new HashMap<>();

  public void putProp(String key, String value) {
    props.put(key, value);
  }

  public boolean hasProp(String key) {
    return props.containsKey(key);
  }

  public String getProp(String key) throws Exception {
    if (!hasProp(key)) {
      throw new Exception("This Lst does not have key \"" + key + "\".");
    }

    return props.get(key);
  }

  public boolean hasName() {
    return hasProp(nameName());
  }

  public String name() throws Exception {
    return getProp(nameName());
  }

  public String nameName() {
    return "list";
  }

  public void addRec(Rec rec) throws Exception {
    validateRec(rec);
    this.weld(rec);
  }

  void weld(Rec rec) throws Exception {
    String id = rec.id();

    if (!hasRecId(id)) {
      recs.put(id, rec);
    }

    Rec recThere = recs.get(id);

    for (String key : rec.fieldNames()) {
      recThere.put(key, rec.get(key));
    }

    recs.put(id, recThere);
  }

  public void validateRec(Rec rec) throws Exception {
    if (rec.noId()) {
      throw new Exception("This record must have an ID.");
    }
  }

  public String prnStr() throws Exception {
    if (!hasName()) {
      return "Unnamed Lst";
    }

    StringBuilder strBuild = new StringBuilder("list name: " + name());
    strBuild.append("\nitems are ").append(recs.size());

    for (Rec rec : recs.values()) {
      strBuild.append("\n  - ").append(rec.toString());
    }

    return strBuild.toString();
  }

  public int size() {
    return recs.size();
  }

  public boolean hasRecId(String id) {
    return recs.containsKey(id);
  }

  public Rec getById(String id) {
    return recs.get(id);
  }
}
