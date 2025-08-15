package tk.d4097.truetree;

import tk.d4097.truetree.keep.Keep;

import java.io.File;

public class Main {
  private Cfg cfg;
  private String cfgFilename;
  private boolean cfgMissing = true;
  private Keep keep;

  public Main() {}

  public Main(String cfgFilename) {
    this.cfgFilename = cfgFilename;
  }

  public Main(Cfg cfg) {
    this.cfg = cfg;
    cfgMissing = false;
  }

  public void go() throws Exception {
    if (cfgMissing) {
      cfgFilename = defaultCfgFilename();
      cfg = new Cfg(cfgFilename);
      cfgMissing = false;
    }

    keep = new Keep(cfg);
    keep.go();
  }

  private String defaultCfgFilename() throws Exception {
    String outerFilename = "../truetree-more/cfg.yml";
    File outerCfg = new File(outerFilename);

    if (outerCfg.exists() && outerCfg.isFile()) {
      return outerFilename;
    }

    String inPlaceFilename = "cfg.yml";
    File inPlaceCfg = new File(inPlaceFilename);

    if (inPlaceCfg.exists() && inPlaceCfg.isFile()) {
      return inPlaceFilename;
    }

    throw new Exception("Default cfg filenames cannot be used.");
  }

  public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");
    Main main = new Main();
    main.go();
  }
}