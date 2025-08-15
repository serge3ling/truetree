package tk.d4097.truetree.text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

abstract class FileTxt {
  protected final InputStream inputStream;
  private boolean wasRead;
  protected Txt txt;

  public FileTxt(String filename) throws Exception {
    this(new FileInputStream(filename));
  }

  public FileTxt(InputStream inputStream) {
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

    txt = new Lines(list);
    afterRead();
  }

  abstract void afterRead();
}
