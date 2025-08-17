package tk.d4097.truetree.text;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StraightYmlTxt extends FileTxt {
  private final Map<String, String> props = new HashMap<>();

  public StraightYmlTxt(String filename) throws Exception {
    super(filename);
  }

  public StraightYmlTxt(InputStream inputStream) {
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
          throw new Exception("Wrong key-value line: \"" + trimmed + "\".");
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

  public String prop(String prop) throws Exception {
    read();

    if (!props.containsKey(prop)) {
      throw new Exception("Property \"" + prop + "\" not found.");
    }

    return props.get(prop);
  }
}
