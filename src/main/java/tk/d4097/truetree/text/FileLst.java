package tk.d4097.truetree.text;

import tk.d4097.truetree.keep.Lst;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileLst {
  private final InputStream inputStream;
  private boolean wasRead;
  private TxtToLst txtToLst;

  public FileLst(String filename) throws Exception {
    this(new FileInputStream(filename));
  }

  public FileLst(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public void read() throws Exception {
    if (wasRead) {
      return;
    }

    wasRead = true;

    List<String> list = new ArrayList<>();

    assert inputStream != null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

    String s = reader.readLine();

    while (s != null) {
      list.add(s);
      s = reader.readLine();
    }

    Txt txt = new Lines(list);
    txtToLst = new TxtToLst(txt);
  }

  public Lst lst() throws Exception {
    read();
    return txtToLst.getBundle().lst();
  }
}
