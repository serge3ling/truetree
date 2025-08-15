package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileLst extends FileTxt {
  private TxtToLst txtToLst;

  public FileLst(String filename) throws Exception {
    super(filename);
  }

  public FileLst(InputStream inputStream) {
    super(inputStream);
  }

  @Override
  public void afterRead() {
    txtToLst = new TxtToLst(txt);
  }

  public Lst lst() throws Exception {
    read();
    return txtToLst.getBundle().lst();
  }
}
