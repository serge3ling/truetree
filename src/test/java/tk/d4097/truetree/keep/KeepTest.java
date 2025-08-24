package tk.d4097.truetree.keep;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.Cfg;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

class KeepTest {

  @Test
  void go_when2Lists_then2Lists() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("keep-1/cfg.yml")) {
      Cfg cfg = new Cfg(inputStream);
      Keep keep = new Keep(cfg);
      keep.go();
      Collection<Lst> lsts = keep.lsts();
      Lst lst0 = lsts.stream().findFirst().get();
      assert (lsts.size() == 2) && (lst0.size() == 4) && lst0.getRecs().containsKey("rai");
    }
  }

  @Test
  void go_whenDirProps_thenDirProps() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("keep-2-dir-props/cfg.yml")) {
      Cfg cfg = new Cfg(inputStream);
      Keep keep = new Keep(cfg);
      keep.go();
      Rec rec0 = keep.dirPropRecIterator().next();
      assert rec0.get("name").equals("First Directory (d1)");
      Lst lst0 = keep.lsts().iterator().next();
      assert lst0.name().equals("brdcst");
      Rec lst0Rec0 = lst0.getRecs().values().iterator().next();
      assert lst0Rec0.id().equals("cbs");
    }
  }
}