package tk.d4097.truetree;

import tk.d4097.truetree.text.FileTxt;
import tk.d4097.truetree.text.StraightYmlTxt;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cfg {
  private final StraightYmlTxt straightYml;

  public Cfg(String filename) throws Exception {
    straightYml = new StraightYmlTxt(filename);
  }

  public Cfg(InputStream inputStream) throws Exception {
    straightYml = new StraightYmlTxt(inputStream);
  }

  public String topDir() throws Exception {
    return straightYml.prop("top-dir");
  }

  public File topDirFile() throws Exception {
    String resPfx = "$resource/";

    if (topDir().startsWith(resPfx)) {
      String topDirRemade = topDir().substring(resPfx.length()).trim();
      ClassLoader classLoader = getClass().getClassLoader();
      URL resUrl = classLoader.getResource(topDirRemade);
      Path resPath = Paths.get(resUrl.toURI());
      return resPath.toFile();
    }

    return new File(topDir());
  }
}
