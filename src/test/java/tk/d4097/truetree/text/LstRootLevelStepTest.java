package tk.d4097.truetree.text;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LstRootLevelStepTest {
  LstParseBundle initBundle(String[] stringArray) {
    List<String> list = Arrays.stream(stringArray).toList();
    Txt lines = new Txt.Stub(list);
    return new LstParseBundle(lines);
  }

  @Test
  void next_whenLine1SpacesHyphenAndField_thenNextIsLstItemFieldParseStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "items:",
        "  - k1: value1"
    });
    LstRootLevelStep step = new LstRootLevelStep(bundle);

    assert (step.next() instanceof LstItemsParseStep);
  }

  @Test
  void next_whenLine1SpacesHyphen_thenNextIsLstItemFieldParseStep() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "items:",
        "  -"
    });
    LstRootLevelStep step = new LstRootLevelStep(bundle);

    assert (step.next() instanceof LstItemsParseStep);
  }

  @Test
  void next_whenLine1IsName_thenNameIsPut() throws Exception {
    LstParseBundle bundle = initBundle(new String[]{
        "list: name1",
        "items:"
    });
    LstRootLevelStep step = new LstRootLevelStep(bundle);
    step.next();

    assert bundle.lst().name().equals("name1");
  }
}