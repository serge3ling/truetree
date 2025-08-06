package tk.d4097.truetree.text;

public class LstItemsParseStep extends ParseStep {
  public LstItemsParseStep(LstParseBundle bundle) {
    super(bundle);
  }

  @Override
  public boolean hasNext() {
    return bundle.lines().hasNext();
  }

  @Override
  public LineParse next() throws Exception {
    Txt lines = bundle.lines();

    if (!lines.hasNext()) {
      bundle.closeRec();
      return this;
    }

    String line = lines.next();

    if (bundle.getStringAcceptors().itemStartWithFieldIsIn(line)) {
      bundle.closeRec();
      bundle.openNewRec();
      lines.backAtBeforeThisLine();
      return new LstItemFieldParseStep(bundle);
    }

    if (bundle.getStringAcceptors().itemStartIsIn(line)) {
      bundle.closeRec();
      bundle.openNewRec();
      return new LstItemFieldParseStep(bundle);
    }

    if (!line.startsWith(" ")) {
      bundle.closeRec();
      lines.backAtBeforeThisLine();
      return new LstRootLevelStep(bundle);
    }

    throw new Exception("Cannot parse line \"" + line + "\", maybe wrong indentation.");
  }
}
