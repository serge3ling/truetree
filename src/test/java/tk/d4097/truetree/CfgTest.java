package tk.d4097.truetree;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CfgTest {

  @Test
  void topDir_whenInRes_thenAsInRes() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    try (InputStream inputStream = classLoader.getResourceAsStream("cfg/cfg.yml")) {
      Cfg cfg = new Cfg(inputStream);
      assert cfg.topDir().equals("top-dir-0");
    }
  }
}