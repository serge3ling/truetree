package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LstItemFieldParseStepTest {
  LstParseBundle initBundle(String[] stringArray) {
    List<String> list = Arrays.stream(stringArray).toList();
    Txt lines = new Txt.Stub(list);
    return new LstParseBundle(lines, "test-path");
  }

  @Test
  void next_whenLine1SpacesHyphen_thenValIsPut() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "  - id: id1",
        "    k1: value1"
    });
    LstItemFieldParseStep step = new LstItemFieldParseStep(bundle);
    step.next();

    assert bundle.rec().id().equals("id1");
  }

  @Test
  void next_whenLine1Spaces_thenValIsPut() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "    k1: value1",
        "    k2: value2"
    });
    LstItemFieldParseStep step = new LstItemFieldParseStep(bundle);
    step.next();

    assert bundle.rec().get("k1").equals("value1");
  }

  @Test
  void next_when2Lines2NextSteps_thenVal2IsPut() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "    k1: value1",
        "    k2: value2"
    });
    LineParse step = new LstItemFieldParseStep(bundle);
    step = step.next();
    step.next();

    assert bundle.rec().get("k2").equals("value2");
  }

  @Test
  void hasNext_when1Line_thenNoNext() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "    key: value"
    });
    LineParse step = new LstItemFieldParseStep(bundle);
    step.next();

    assert !step.hasNext();
  }

  @Test
  void next_whenNextIsItemStartWithField_thenNextStepIsLstItemFieldParseStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "    k1: value1",
        "  - id: id2"
    });
    LineParse step = new LstItemFieldParseStep(bundle);
    step = step.next();

    assert (step instanceof LstItemFieldParseStep);
  }

  @Test
  void next_whenNextIsItemStart_thenNextStepIsLstItemFieldParseStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "    k1: value1",
        "  -"
    });
    LineParse step = new LstItemFieldParseStep(bundle);
    step = step.next();

    assert (step instanceof LstItemFieldParseStep);
  }

  @Test
  void next_whenNextIsRootField_thenNextStepIsLstRootLevelStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "    k1: value1",
        "rootKey2: rootValue2"
    });
    LineParse step = new LstItemFieldParseStep(bundle);
    step = step.next();

    assert (step instanceof LstRootLevelStep);
  }

  @Test
  void next_whenNextIsItemField_thenNextStepIsLstItemFieldParseStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "    k1: value1",
        "    k2: value2"
    });
    LineParse step = new LstItemFieldParseStep(bundle);
    step = step.next();

    assert (step instanceof LstItemFieldParseStep);
  }

  @Test
  void next_when2RecsInText_then2RecsInLst() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "  - id: id1",
        "    fld: value1",
        "  - id: id2",
        "    fld: value2"
    });
    bundle.openNewRec();
    bundle.lst().putProp(bundle.lst().nameName(), "tst1");
    LineParse step = new LstItemFieldParseStep(bundle);
    step = step.next();
    step = step.next();
    step = step.next();
    step = step.next();

    assert (bundle.lst().size() == 2);
  }
}