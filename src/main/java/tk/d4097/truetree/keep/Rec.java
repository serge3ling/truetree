package tk.d4097.truetree.keep;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Rec implements Comparable {
  private final Map<String, String> fields = new HashMap<>();

  public void put(String key, String val) {
    fields.put(key, val);
  }

  public boolean hasField(String key) {
    return fields.containsKey(key);
  }

  public Set<String> fieldNames() {
    return fields.keySet();
  }

  public String get(String key) throws Exception {
    if (!hasField(key)) {
      throw new Exception("This Rec does not have field \"" + key + "\".");
    }

    return fields.get(key);
  }

  public String idName() {
    return "id";
  }

  public boolean hasId() {
    return hasField(this.idName());
  }

  public boolean noId() {
    return !hasField(this.idName());
  }

  public String id() throws Exception {
    return this.get(idName());
  }

  @Override
  public int hashCode() {
    try {
      return this.id().hashCode();
    } catch (Exception ex) {
      throw new RuntimeException("Cannot count hash code since the record does not have id.", ex);
    }
  }

  @Override
  public boolean equals(Object obj) {
    return (this.compareTo(obj) == 0);
  }

  @Override
  public int compareTo(Object thatObject) {
    if (!(thatObject instanceof Rec that)) {
      return -32768;
    }

    String thisId = "";
    String thatId = "";

    try {
      thisId = this.id();
      thatId = that.id();
    } catch (Exception ex) {
      throw new RuntimeException("Cannot compare since the record does not have id.", ex);
    }

    return thisId.compareTo(thatId);
  }

  @Override
  public String toString() {
    String id = "(no " + this.idName() + ")";

    try {
      id = this.id();
    } catch (Exception ignored) {
    }

    StringBuffer strB = new StringBuffer(this.idName() + "=\"").append(id).append("\"");

    for (String key : this.fieldNames()) {
      try {
        if (!key.equals(this.idName())) {
          strB.append(", ").append(key).append("=\"").append(this.get(key)).append("\"");
        }
      } catch (Exception ignored) {
      }
    }

    return strB.toString();
  }
}
