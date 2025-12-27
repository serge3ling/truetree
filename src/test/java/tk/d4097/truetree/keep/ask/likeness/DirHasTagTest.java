package tk.d4097.truetree.keep.ask.likeness;

import org.junit.jupiter.api.Test;
import tk.d4097.truetree.keep.Lst;

import static org.junit.jupiter.api.Assertions.*;

class DirHasTagTest {
  private final DirTagsProp dirTagsProp = new DirTagsProp();

  @Test
  void isGood_whenHasSnippetInTagsAnyCase_thenTrue() {
    String tagSnip = "Money";
    Lst lst0 = makeBasicLstWithTags("docs, money, payments");
    DirHasInTags<Lst> dirHasInTags = new DirHasInTags<>(tagSnip);
    assert dirHasInTags.isGood(lst0);
  }

  @Test
  void isGood_whenHasSnippetAnyCaseInTags_thenTrue() {
    String tagSnip = "money";
    Lst lst0 = makeBasicLstWithTags("docs, MONEY, payments");
    DirHasInTags<Lst> dirHasInTags = new DirHasInTags<>(tagSnip);
    assert dirHasInTags.isGood(lst0);
  }

  @Test
  void isGood_whenHasNoSuchSnippetInTags_thenFalse() {
    String tagSnip = "Geld";
    Lst lst0 = makeBasicLstWithTags("docs, money, payments");
    DirHasInTags<Lst> dirHasInTags = new DirHasInTags<>(tagSnip);
    assert !dirHasInTags.isGood(lst0);
  }

  Lst makeBasicLstWithTags(String tags) {
    Lst lst0 = new Lst("test-path-0");
    lst0.putProp(dirTagsProp.name(), tags);
    return lst0;
  }
}