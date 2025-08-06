package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinesTest {

  @Test
  void hasNext_whenEmpty_thenFalse() {
    Lines lines = new Lines(new ArrayList<>());
    assert !lines.hasNext();
  }

  @Test
  void hasNext_whenHas_thenTrue() {
    List<String> lineList = new ArrayList<>();
    lineList.add("String.");
    Lines lines = new Lines(lineList);
    assert lines.hasNext();
  }

  @Test
  void next_when2Lines_thenRightLine2() throws Exception {
    List<String> lineList = new ArrayList<>();
    lineList.add("Line 1.");
    String line2 = "Line 2.";
    lineList.add(line2);
    Lines lines = new Lines(lineList);
    lines.next();
    String s = lines.next();
    assert line2.equals(s);
  }

  @Test
  void backAtBeforeThisLineAndNext_when3LinesAndBackAtBeforeLine2_thenRightLine2() throws Exception {
    List<String> lineList = new ArrayList<>();
    lineList.add("Line 1.");
    String line2 = "Line 2.";
    lineList.add(line2);
    lineList.add("Line 3.");
    Lines lines = new Lines(lineList);
    lines.next();
    lines.next();
    lines.backAtBeforeThisLine();
    String s = lines.next();
    assert line2.equals(s);
  }

  @Test
  void backAtBeforeThisLine_whenNoLineRead_thenException() {
    List<String> lineList = new ArrayList<>();
    lineList.add("");
    Lines lines = new Lines(lineList);
    try {
      lines.backAtBeforeThisLine();
      assert false : "lines.backAtBeforeThisLine() must raise an exception when lines was never read before.";
    } catch (Exception ex) {
    }
  }
}