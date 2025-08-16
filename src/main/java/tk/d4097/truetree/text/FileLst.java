package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileLst extends FileTxt {
  private final String path;
  private TxtToLst txtToLst;

  public FileLst(String filename) throws Exception {
    super(filename);
    this.path = filename;
  }

  public FileLst(InputStream inputStream, String path) {
    super(inputStream);
    this.path = path;
  }

  @Override
  public void afterRead() {
    txtToLst = new TxtToLst(txt, path);
  }

  public Lst lst() throws Exception {
    read();
    return txtToLst.getBundle().lst();
  }
}
