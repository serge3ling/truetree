package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LstItemsParseStepTest {
  LstParseBundle initBundle(String[] stringArray) {
    List<String> list = Arrays.stream(stringArray).toList();
    Txt lines = new Txt.Stub(list);
    return new LstParseBundle(lines);
  }

  @Test
  void next_whenLine1SpacesHyphen_thenNextIsLstItemFieldParseStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "  - id: id1",
        "    k1: value1"
    });
    LstItemsParseStep step = new LstItemsParseStep(bundle);

    assert (step.next() instanceof LstItemFieldParseStep);
  }

  @Test
  void next_whenLine1Hyphen_thenNextIsLstItemFieldParseStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "  -",
        "    id: id1"
    });
    LstItemsParseStep step = new LstItemsParseStep(bundle);

    assert (step.next() instanceof LstItemFieldParseStep);
  }

  @Test
  void next_whenLine1IsRootField_thenNextIsLstRootLevelStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "rootKey1: rootValue1",
        "items:"
    });
    LstItemsParseStep step = new LstItemsParseStep(bundle);

    assert (step.next() instanceof LstRootLevelStep);
  }
}