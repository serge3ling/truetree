package tk.d4097.truetree.keep;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.Cfg;

import java.io.InputStream;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class KeepTest {

  @Test
  void go() throws Exception {
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
}