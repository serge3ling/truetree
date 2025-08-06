package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkipIteratorTest {
  SkipIterator init(String[] stringArray) {
    List<String> list = Arrays.stream(stringArray).toList();
    return new SkipIterator(list);
  }

  @Test
  void hasNext_whenEmptyTxt_thenFalse() {
    SkipIterator iterator = init(new String[]{});
    assert !iterator.hasNext();
  }

  @Test
  void hasNext_when1EmptyStr_thenFalse() {
    SkipIterator iterator = init(new String[]{""});
    assert !iterator.hasNext();
  }

  @Test
  void hasNext_when1SkippedStr_thenFalse() {
    SkipIterator iterator = init(new String[]{"    # skipped"});
    assert !iterator.hasNext();
  }

  @Test
  void hasNext_when2SkippedStr_thenFalse() {
    SkipIterator iterator = init(new String[]{
        "    # skipped 1",
        "    # skipped 2",
    });
    assert !iterator.hasNext();
  }

  @Test
  void hasNext_when1SkippedStr1Meaning_thenTrue() {
    SkipIterator iterator = init(new String[]{
        "    # skipped",
        "    key: val",
    });
    assert iterator.hasNext();
  }

  @Test
  void next_whenEmptyTxt_thenException() {
    SkipIterator iterator = init(new String[]{});
    try {
      iterator.next();
      assert false;
    } catch (Exception ex) {
      assert true;
    }
  }

  @Test
  void next_when1EmptyStr_thenException() {
    SkipIterator iterator = init(new String[]{""});
    try {
      iterator.next();
      assert false;
    } catch (Exception ex) {
      assert true;
    }
  }

  @Test
  void next_when1SkippedStr_thenException() {
    SkipIterator iterator = init(new String[]{"# skipped"});
    try {
      iterator.next();
      assert false;
    } catch (Exception ex) {
      assert true;
    }
  }

  @Test
  void next_when2SkippedStr1Meaning_thenMeaning() {
    String meaning = "prop: val";
    SkipIterator iterator = init(new String[]{
        "",
        "# skipped 2",
        meaning,
    });
    assert iterator.next().equals(meaning);
  }

  @Test
  void next_when1Meaning2SkippedStr1Meaning_thenMeaningRight() {
    String meaning1 = "prop1: val1";
    String meaning2 = "prop2: val2";
    SkipIterator iterator = init(new String[]{
        meaning1,
        "",
        "# skipped 2",
        meaning2,
    });
    String str1 = iterator.next();
    String str2 = iterator.next();
    assert str1.equals(meaning1) && str2.equals(meaning2);
  }

  @Test
  void next_when2Meaning2SkippedStr_thenMeaningRightAndNoNext() {
    String meaning1 = "prop1: val1";
    String meaning2 = "prop2: val2";
    SkipIterator iterator = init(new String[]{
        meaning1,
        meaning2,
        "",
        "# skipped 2",
    });
    String str1 = iterator.next();
    String str2 = iterator.next();
    boolean noNextAfter2 = !iterator.hasNext();
    assert str1.equals(meaning1) && str2.equals(meaning2) && noNextAfter2;
  }

  @Test
  void next_when2MeaningStr_thenMeaningRightAndNoNext() {
    String meaning1 = "prop1: val1";
    String meaning2 = "prop2: val2";
    SkipIterator iterator = init(new String[]{
        meaning1,
        meaning2,
    });
    String str1 = iterator.next();
    String str2 = iterator.next();
    boolean noNextAfter2 = !iterator.hasNext();
    assert str1.equals(meaning1) && str2.equals(meaning2) && noNextAfter2;
  }
}