package tk.d4097.truetree.keep;

import java.util.*;

public class LoadLog {
  private final Map<LoadLogLayerIdWField, List<String>> idWField2Path = new HashMap<>();
  private final Map<String, List<String>> id2Path = new HashMap<>();
  private final Map<String, List<String>> path2Id = new HashMap<>();

  public void put(String id, String fieldName, String path) {
    putIdWField(new LoadLogLayerIdWField(id, fieldName), path);
    putIdAndPath(id, path);
  }

  private void putIdWField(LoadLogLayerIdWField key, String path) {
    List<String> list = idWField2Path.containsKey(key) ? idWField2Path.get(key) : new ArrayList<>();

    if (!list.contains(path)) {
      list.add(path);
      idWField2Path.put(key, list);
    }
  }

  public Set<LoadLogLayerIdWField> idWField2PathKeys() {
    return idWField2Path.keySet();
  }

  public void putIdWFieldMany(LoadLogLayerIdWField key, List<String> paths) {
    List<String> list = idWField2Path.containsKey(key) ? idWField2Path.get(key) : new ArrayList<>();
    list.addAll(paths);
    idWField2Path.put(key, list);
  }

  public void putIdAndPath(String id, String path) {
    List<String> pathList = id2Path.containsKey(id) ? id2Path.get(id) : new ArrayList<>();

    if (!pathList.contains(path)) {
      pathList.add(path);
      id2Path.put(id, pathList);
    }

    List<String> idList = path2Id.containsKey(path) ? path2Id.get(path) : new ArrayList<>();

    if (!idList.contains(id)) {
      idList.add(id);
      path2Id.put(path, idList);
    }
  }

  public Set<String> id2PathKeys() {
    return id2Path.keySet();
  }

  public void putIdToPaths(String id, List<String> paths) {
    List<String> pathList = id2Path.containsKey(id) ? id2Path.get(id) : new ArrayList<>();
    pathList.addAll(paths);
    id2Path.put(id, pathList);
  }

  public Set<String> path2IdKeys() {
    return path2Id.keySet();
  }

  public void putPathToIds(List<String> ids, String path) {
    List<String> idList = path2Id.containsKey(path) ? path2Id.get(path) : new ArrayList<>();
    idList.addAll(ids);
    path2Id.put(path, idList);
  }

  public List<String> getPathByIdWField(LoadLogLayerIdWField key) throws Exception {
    if (!hasPathByIdWField(key)) {
      throw new Exception("Not found value for " + key + ".");
    }

    return idWField2Path.get(key);
  }

  public boolean hasPathByIdWField(LoadLogLayerIdWField key) {
    return idWField2Path.containsKey(key);
  }

  public List<String> getPathById(String id) throws Exception {
    if (!hasPathById(id)) {
      throw new Exception("Not found path for id \"" + id + "\".");
    }

    return id2Path.get(id);
  }

  public boolean hasPathById(String id) {
    return id2Path.containsKey(id);
  }

  public List<String> getIdByPath(String path) throws Exception {
    if (!hasIdByPath(path)) {
      throw new Exception("Not found id for path \"" + path + "\".");
    }

    return path2Id.get(path);
  }

  public boolean hasIdByPath(String path) {
    return path2Id.containsKey(path);
  }
}
