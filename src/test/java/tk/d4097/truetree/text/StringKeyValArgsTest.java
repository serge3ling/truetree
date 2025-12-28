package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringKeyValArgsTest {
  @Test
  void split_whenThereIs_thenThereIs() {
    String strWrappedWQuotes = "dir-name=\"payments\"";
    String strFlat = "key1=val1";
    String strWrappedWApostrophes = "key2='val2'";
    String strWQuotesInside = "has-tag=\"money \\\"for nothing\\\"\"";

    String input = " "
        + " " + strWrappedWQuotes
        + " " + strFlat
        + " " + strWrappedWApostrophes
        + " " + strWQuotesInside;

    StringKeyValArgs stringKeyValArgs = new StringKeyValArgs(input);
    assert stringKeyValArgs.fits();
    List<String> split = stringKeyValArgs.split();
    assert split.size() == 4;
    assert split.contains(strWrappedWQuotes);
    assert split.contains(strFlat);
    assert split.contains(strWrappedWApostrophes);
    assert split.contains(strWQuotesInside);
  }
}