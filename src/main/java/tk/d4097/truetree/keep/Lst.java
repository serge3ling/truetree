package tk.d4097.truetree.keep;

import java.util.HashMap;
import java.util.Map;

public class Lst {
  private final Map<String, String> props = new HashMap<>();
  private final Map<String, Rec> recs = new HashMap<>();
  private final String path;
  private boolean closed;
  private final LoadLog recLoadLog = new LoadLog();
  private final LoadLog lstLoadLog = new LoadLog();

  public Lst(String path, String name) {
    this(path);
    putProp(nameName(), name);
  }

  public Lst(String path) {
    this.path = path;
  }

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

  public Map<String, Rec> getRecs() {
    return recs;
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

  public void close() throws Exception {
    if (closed) {
      return;
    }

    closed = true;

    this.log();
  }

  public boolean isOpen() {
    return !closed;
  }

  public void lay(Lst that) throws Exception {
    String nameSpot = "${name}";
    String errMsg = "When calling to lay(lst), Lst " + nameSpot + " is not closed yet.";

    if (that.isOpen()) {
      throw new Exception(errMsg.replace(nameSpot, that.props.get(nameName())));
    }

    if (this.isOpen()) {
      throw new Exception(errMsg.replace(nameSpot, this.props.get(nameName())));
    }

    for (String key : that.props.keySet()) {
      this.putProp(key, that.getProp(key));
    }

    for (LoadLogLayerIdWField key : that.recLoadLog.idWField2PathKeys()) {
      this.recLoadLog.putIdWFieldMany(key, that.recLoadLog.getPathByIdWField(key));
    }

    for (String key : that.recLoadLog.id2PathKeys()) {
      this.recLoadLog.putIdToPaths(key, that.recLoadLog.getPathById(key));
    }

    for (String key : that.recLoadLog.path2IdKeys()) {
      this.recLoadLog.putPathToIds(that.recLoadLog.getIdByPath(key), key);
    }
  }

  public void addRec(Rec rec) throws Exception {
    this.validateRec(rec);
    this.log(rec);
    this.lay(rec);
  }

  void lay(Rec rec) throws Exception {
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

  void log(Rec rec) throws Exception {
    for (String key : rec.fieldNames()) {
      if (rec.idName().equals(key)) {
        recLoadLog.putIdAndPath(rec.id(), this.path);
      } else {
        recLoadLog.put(rec.id(), key, this.path);
      }
    }
  }

  private void log() throws Exception {
    for (String key : props.keySet()) {
      if (this.nameName().equals(key)) {
        lstLoadLog.putIdAndPath(this.name(), this.path);
      } else {
        lstLoadLog.put(this.name(), key, this.path);
      }
    }
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

  public LoadLog getRecLoadLog() {
    return recLoadLog;
  }

  @Override
  public int hashCode() {
    int pathChunk = path.hashCode();
    int nameChunk = 0;

    if (hasName()) {
      try {
        nameChunk = name().hashCode();
      } catch (Exception ignored) {}
    }

    return pathChunk * 37 + nameChunk;
  }

  @Override
  public boolean equals(Object thatObj) {
    if (!(thatObj instanceof Lst that)) {
      return false;
    }

    boolean bothNamesAreThere = this.hasName() && that.hasName();
    boolean namesSame = bothNamesAreThere || (!this.hasName() && !that.hasName());

    if (bothNamesAreThere) {
      try {
        namesSame = this.name().equals(that.name());
      } catch (Exception ignored) {
      }
    }

    return namesSame && this.path.equals(that.path);
  }

  @Override
  public String toString() {
    String nameStr = "no-name";

    try {
      nameStr = "name=\"" + name() + "\"";
    } catch (Exception ignored) {}

    return "path=\"" + path + "\", " + nameStr + ", records are " + recs.size();
  }
}
