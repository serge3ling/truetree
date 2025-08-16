package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.keep.Rec;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TxtToLstTest {
  Txt initTxt(String[] stringArray) {
    List<String> list = Arrays.stream(stringArray).toList();
    return new Txt.Stub(list);
  }

  @Test
  void getBundle_whenStraightLst_thenIsRead() throws Exception {
    Txt txt = initTxt(new String[]{
        "list: test",
        "items:",
        "  - id: test1",
        "    v: value1",
        "  - id: test2",
        "    v: value2"
    });

    TxtToLst txtToLst = new TxtToLst(txt, "test-path");

    assert (txtToLst.getBundle().lst().size() == 2);
  }

  @Test
  void next_when2RecsInTextAndEmptyHyphen_then2RecsInLst() throws Exception {
    Txt txt = initTxt(new String[]{
        "list: tst2",
        "items:",
        "  -",
        "    id: id1",
        "    fld: value1",
        "  - id: id2",
        "    fld: value2"
    });

    TxtToLst txtToLst = new TxtToLst(txt, "test-path");

    assert (txtToLst.getBundle().lst().size() == 2);
  }

  @Test
  void go_when2RecsInTextAs3_then2RecsInLst() throws Exception {
    String idName = new Rec().idName();
    String fld = "fld";
    String prop = "prop";
    String country = "country";
    String id1 = "id1";
    String fld1 = "value1b";
    String prop1 = "prop1";
    String country1 = "uk";

    Txt txt = initTxt(new String[]{
        "list: tst2",
        "items:",
        "  -",
        "    " + idName + ": " + id1,
        "    " + fld + ": any-value-1",
        "    " + country + ": " + country1,
        "  - " + idName + ": id2",
        "    fld: value2",
        "  - " + idName + ": " + id1,
        "    " + prop + ": " + prop1,
        "    " + fld + ": " + fld1
    });

    TxtToLst txtToLst = new TxtToLst(txt, "test-path");
    Rec rec1 = txtToLst.getBundle().lst().getById(id1);

    assert (rec1.id().equals(id1) && rec1.get(fld).equals(fld1)
        && rec1.get(prop).equals(prop1) && rec1.get(country).equals(country1));
  }

  @Test
  void go_when2RecsInTextAs3AndSomeSkipped_then2RecsInLst() throws Exception {
    String idName = new Rec().idName();
    String fld = "fld";
    String prop = "prop";
    String country = "country";
    String id1 = "id1";
    String fld1 = "value1b";
    String prop1 = "prop1";
    String country1 = "uk";

    Txt txt = initTxt(new String[]{
        "# Set list name",
        "list: tst2",
        "",
        "# Start items",
        "items:",
        "",
        "  # Start an item",
        "  -",
        "    # Set a field value",
        "    " + idName + ": " + id1,
        "    " + fld + ": any-value-1",
        "    " + country + ": " + country1,
        "  - " + idName + ": id2",
        "    fld: value2",
        "  - " + idName + ": " + id1,
        "    " + prop + ": " + prop1,
        "    " + fld + ": " + fld1,
    });

    TxtToLst txtToLst = new TxtToLst(txt, "test-path");
    Rec rec1 = txtToLst.getBundle().lst().getById(id1);

    assert (rec1.id().equals(id1) && rec1.get(fld).equals(fld1)
        && rec1.get(prop).equals(prop1) && rec1.get(country).equals(country1));
  }

  @Test
  void go_whenEndsWithSkipped_thenNoRecs() throws Exception {
    Txt txt = initTxt(new String[]{
        "list: Broadcast Stations",
        "# no items"
    });

    TxtToLst txtToLst = new TxtToLst(txt, "test-path");
    assert txtToLst.getBundle().lst().size() == 0;
  }

  @Test
  void go_when1ItemAndEndsWithSkipped_then1Rec() throws Exception {
    Txt txt = initTxt(new String[]{
        "list: Broadcast Stations",
        "items:",
        "  - id: rai",
        "    country: it",
        "# ends here"
    });

    TxtToLst txtToLst = new TxtToLst(txt, "test-path");
    assert txtToLst.getBundle().lst().size() == 1;
  }
}