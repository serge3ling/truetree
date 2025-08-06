package tk.d4097.truetree.text;

public class LstRootLevelStep extends ParseStep {
  public LstRootLevelStep(LstParseBundle bundle) {
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
      return this;
    }

    String line = lines.next();

    if (line.equals("items:")) {
      return new LstItemsParseStep(bundle);
    }

    String nameName = bundle.lst().nameName();
    String nameLineStart = nameName + ": ";

    if (line.startsWith(nameLineStart)) {
      String name = line.substring(nameLineStart.length()).trim();

      if (name.isEmpty()) {
        throw new Exception("The value of name cannot be empty.");
      }

      bundle.lst().putProp(nameName, name);
    }

    return this;
  }
}
