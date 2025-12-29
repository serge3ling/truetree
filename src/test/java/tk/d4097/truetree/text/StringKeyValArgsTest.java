package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringKeyValArgsTest {
  @Test
  void split_whenThereIs_thenFound() {
    String strWrappedWQuotes = "dir-name=\"payments\"";
    String strFlat = "key1=val1";
    String strWrappedWApostrophes = "key2='val2'";
    String strWQuotesInside = "has-tag=\"money \\\"for nothing\\\"\"";

    String input =
        " " + strWrappedWQuotes
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

  @Test
  void split_whenInnerQuoteKeyValArgIsLast_thenFound() {
    String strW2HyphenKey = "full-name--has=Broadcast";
    String strWQuotesInside = "has-tag=\"money \\\"for nothing\\\"\"";
    String input = " " + strW2HyphenKey + " " + strWQuotesInside;
    StringKeyValArgs stringKeyValArgs = new StringKeyValArgs(input);
    assert stringKeyValArgs.fits();
    List<String> split = stringKeyValArgs.split();
    assert split.contains(strW2HyphenKey);
    assert split.contains(strWQuotesInside);
  }

  @Test
  void split_whenInnerQuoteKeyValArgIsNotLast_thenFound() {
    String strWQuotesInside = "has-tag=\"money \\\"for nothing\\\"\"";
    String strW2HyphenKey = "full-name--has=Broadcast";
    String input = " " + strWQuotesInside + " " + strW2HyphenKey;
    StringKeyValArgs stringKeyValArgs = new StringKeyValArgs(input);
    assert stringKeyValArgs.fits();
    List<String> split = stringKeyValArgs.split();
    assert split.contains(strWQuotesInside);
    assert split.contains(strW2HyphenKey);
  }

  @Test
  void split_whenTrimmedInputEmpty_thenNoneFound() {
    String input = "  ";
    StringKeyValArgs stringKeyValArgs = new StringKeyValArgs(input);
    assert stringKeyValArgs.fits();
    assert stringKeyValArgs.split().isEmpty();
  }
}