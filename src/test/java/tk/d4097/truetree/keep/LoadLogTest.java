package tk.d4097.truetree.keep;

import org.junit.jupiter.api.Test;

import java.util.List;

class LoadLogTest {

  @Test
  void put_when2PathsFor1IdWField_then2Found() throws Exception {
    LoadLog loadLog = new LoadLog();
    String id = "id-a";
    String fld = "field-a";
    String pathA1 = "path-a1";
    String pathA2 = "path-a2";
    loadLog.put(id, fld, pathA1);
    loadLog.put(id, fld, pathA2);
    List<String> pathsByIdWField = loadLog.getPathByIdWField(new LoadLogLayerIdWField(id, fld));
    assert (pathsByIdWField.size() == 2)
        && pathsByIdWField.contains(pathA1) && pathsByIdWField.contains(pathA2);
  }

  @Test
  void putIdAndPath_when2PathsFor1Id_then2Found() throws Exception {
    LoadLog loadLog = new LoadLog();
    String id = "id-a";
    String pathA1 = "path-a1";
    String pathA2 = "path-a2";
    loadLog.putIdAndPath(id, pathA1);
    loadLog.putIdAndPath(id, pathA2);
    List<String> pathsById = loadLog.getPathById(id);
    assert (pathsById.size() == 2)
        && pathsById.contains(pathA1) && pathsById.contains(pathA2);
  }

  @Test
  void putIdAndPath_when2IdsFor1Path_then2IdFoundAnd1Path() throws Exception {
    LoadLog loadLog = new LoadLog();
    String idA = "id-a";
    String idB = "id-b";
    String pathA = "path-a";
    loadLog.putIdAndPath(idA, pathA);
    loadLog.putIdAndPath(idB, pathA);
    List<String> idsByPath = loadLog.getIdByPath(pathA);
    List<String> pathByIdA = loadLog.getPathById(idA);
    assert (idsByPath.size() == 2)
        && idsByPath.contains(idA) && idsByPath.contains(idB)
        && (pathByIdA.size() == 1) && pathByIdA.contains(pathA);
  }
}