package tk.d4097.truetree;

import tk.d4097.truetree.text.FileTxt;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Cfg extends FileTxt {
  private final Map<String, String> props = new HashMap<>();

  public Cfg(String filename) throws Exception {
    super(filename);
  }

  public Cfg(InputStream inputStream) {
    super(inputStream);
  }

  @Override
  public void afterRead() throws Exception {
    while (txt.hasNext()) {
      String trimmed = txt.next().trim();

      if (!trimmed.isEmpty() && !trimmed.startsWith("# ")) {
        int ix = trimmed.indexOf(this.separator());
        int separatorLen = this.separator().length();

        if ((ix < 1) || (ix + separatorLen > trimmed.length())) {
          throw new Exception("Wrong cfg line: \"" + trimmed + "\".");
        }

        String key = trimmed.substring(0, ix);
        String val = trimmed.substring(ix + separatorLen);
        props.put(key, val);
      }
    }
  }

  public String separator() {
    return ": ";
  }

  public String topDir() throws Exception {
    return prop("top-dir");
  }

  public String prop(String prop) throws Exception {
    read();

    if (!props.containsKey(prop)) {
      throw new Exception("Property \"" + prop + "\" not found.");
    }

    return props.get(prop);
  }
}
