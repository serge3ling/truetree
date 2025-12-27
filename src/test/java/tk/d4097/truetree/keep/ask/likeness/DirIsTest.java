package tk.d4097.truetree.keep.ask.likeness;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.keep.Lst;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DirIsTest {

  @Test
  void isGood_whenPathSame_thenTrue() {
    String path = "path";
    DirIs<Lst> dirIs = new DirIs<>(path);
    Lst lst = new Lst(path);
    assert dirIs.isGood(lst);
  }

  @Test
  void isGood_whenPathEndsWithSame_thenTrue() {
    String dirName = "path";
    String path = "upper" + File.separator + dirName + File.separator + "~t-dir.yml";
    DirIs<Lst> dirIs = new DirIs<>(dirName);
    Lst lst = new Lst(path);
    assert dirIs.isGood(lst);
  }

  @Test
  void isGood_whenNameSame_thenTrue() {
    String name = "name";
    DirIs<Lst> dirIs = new DirIs<>(name);
    Lst lst = new Lst(name);
    assert dirIs.isGood(lst);
  }

  @Test
  void isGood_whenPathEndsWithoutSeparator_thenFalse() {
    String pathPfx = "pfx-";
    String path = "path";
    DirIs<Lst> dirIs = new DirIs<>(path);
    Lst lst = new Lst(pathPfx + path);
    assert !dirIs.isGood(lst);
  }

  @Test
  void isGood_whenPathNorName_thenFalse() {
    String path = "path";
    String name = "name";
    DirIs<Lst> dirIs = new DirIs<>("neither-path-nor-name");
    Lst lst = new Lst(path, name);
    assert !dirIs.isGood(lst);
  }
}